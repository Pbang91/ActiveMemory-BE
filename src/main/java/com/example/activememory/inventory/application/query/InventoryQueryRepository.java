package com.example.activememory.inventory.application.query;

import com.example.activememory.account.user.domain.vo.UserId;
import com.example.activememory.inventory.application.query.model.MyGymMachineReadModel;
import com.example.activememory.inventory.application.query.model.MyGymReadModel;
import com.example.activememory.inventory.domain.entity.QCustomMachine;
import com.example.activememory.inventory.domain.entity.QCustomMachineMuscle;
import com.example.activememory.inventory.domain.entity.QMyGym;
import com.example.activememory.reference.domain.exercise.entity.QBodyPart;
import com.example.activememory.reference.domain.exercise.entity.QMuscle;
import com.example.activememory.reference.domain.exercise.entity.QStandardExercise;
import com.example.activememory.reference.domain.exercise.enums.MuscleRole;
import com.example.activememory.reference.domain.exercise.vo.BodyPartCode;
import com.example.activememory.reference.domain.exercise.vo.MuscleId;
import com.example.activememory.reference.domain.exercise.vo.StandardExerciseId;
import com.example.activememory.reference.domain.gym.entity.QGym;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

import com.querydsl.core.types.Expression;

import static com.querydsl.core.group.GroupBy.groupBy;
import static com.querydsl.core.group.GroupBy.list;

@Repository
@RequiredArgsConstructor
public class InventoryQueryRepository {
    private final JPAQueryFactory queryFactory;
    private final QMyGym myGym = QMyGym.myGym;
    private final QGym gym = QGym.gym;
    private final QCustomMachine customMachine = QCustomMachine.customMachine;
    private final QCustomMachineMuscle machineMuscle = QCustomMachineMuscle.customMachineMuscle;
    private final QMuscle muscle = QMuscle.muscle;
    private final QBodyPart bodyPart = QBodyPart.bodyPart;
    private final QStandardExercise standardExercise = QStandardExercise.standardExercise;

    private BooleanExpression matchId(Expression<?> voPath, Expression<?> idPath) {
        return Expressions.booleanTemplate("{0} = {1}", voPath, idPath);
    }

    public List<MyGymReadModel> findMyGymByUserId(Long userId) {
        return queryFactory.select(
                        Projections.constructor(
                                MyGymReadModel.class,
                                myGym.id,
                                gym.name,
                                gym.address
                        )
                )
                .from(myGym)
                .join(gym).on(matchId(myGym.id, gym.id))
                .where(
                        myGym.userId.eq(UserId.of(userId)),
                        myGym.isActive.isTrue()
                )
                .fetch();
    }

    public List<MyGymMachineReadModel> findMyGymMachineByIdAndUserId(Long myGymId, Long userId) {
        List<Tuple> tuples = queryFactory
                .select(
                        customMachine.id,
                        customMachine.name,
                        customMachine.memo,
                        customMachine.bodyPartCode,
                        bodyPart.name,
                        customMachine.standardExerciseId,
                        standardExercise.name,
                        machineMuscle.muscleId,
                        muscle.name,
                        machineMuscle.role
                )
                .from(customMachine)
                .join(customMachine.myGym, myGym)
                .leftJoin(standardExercise).on(matchId(customMachine.standardExerciseId, standardExercise.id))
                .leftJoin(bodyPart).on(matchId(customMachine.bodyPartCode, bodyPart.code))
                .leftJoin(customMachine.muscles, machineMuscle)
                .leftJoin(muscle).on(matchId(machineMuscle.muscleId, muscle.id))
                .where(customMachine.myGym.id.eq(myGymId), myGym.userId.eq(UserId.of(userId)))
                .fetch();

        return tuples.stream()
                // 기구 ID를 기준으로 그룹핑
                .collect(Collectors.groupingBy(tuple -> tuple.get(customMachine.id)))
                .values().stream()
                .map(groupedTuples -> {
                    // 첫 번째 튜플에서 기구 기본 정보 추출
                    Tuple first = groupedTuples.get(0);

                    // 근육 리스트 조립 (null 방어 로직 포함)
                    List<MyGymMachineReadModel.Muscle> muscles = groupedTuples.stream()
                            .filter(t -> t.get(machineMuscle.muscleId) != null)
                            .map(t -> new MyGymMachineReadModel.Muscle(
                                    t.get(machineMuscle.muscleId),
                                    t.get(muscle.name), // muscle.name은 String 타입이 맞다면 그대로 사용
                                    t.get(machineMuscle.role)
                            ))
                            .toList();

                    // 최종 완성된 ReadModel 반환
                    return new MyGymMachineReadModel(
                            first.get(customMachine.id),
                            first.get(customMachine.name),
                            first.get(customMachine.memo),
                            new MyGymMachineReadModel.BodyPart(
                                    first.get(customMachine.bodyPartCode),
                                    first.get(bodyPart.name)
                            ),
                            new MyGymMachineReadModel.StandardExercise(
                                    first.get(customMachine.standardExerciseId),
                                    first.get(standardExercise.name)
                            ),
                            muscles
                    );
                })
                .toList();
    }
}
