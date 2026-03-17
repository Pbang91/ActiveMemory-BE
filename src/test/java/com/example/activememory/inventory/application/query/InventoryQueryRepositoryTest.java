package com.example.activememory.inventory.application.query;

import com.example.activememory.account.user.domain.vo.UserId;
import com.example.activememory.global.utils.QuerydslConfig;
import com.example.activememory.inventory.application.query.model.MyGymMachineReadModel;
import com.example.activememory.inventory.domain.entity.CustomMachine;
import com.example.activememory.inventory.domain.entity.MyGym;
import com.example.activememory.reference.domain.exercise.enums.MuscleRole;
import com.example.activememory.reference.domain.exercise.vo.BodyPartCode;
import com.example.activememory.reference.domain.exercise.vo.MuscleId;
import com.example.activememory.reference.domain.exercise.vo.StandardExerciseId;
import com.example.activememory.reference.domain.gym.vo.GymId;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@ActiveProfiles("local")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Import({QuerydslConfig.class, InventoryQueryRepository.class})
public class InventoryQueryRepositoryTest {
    @Autowired
    private EntityManager em;

    @Autowired
    private InventoryQueryRepository inventoryQueryRepository;

    @Test
    @DisplayName("체육관의 기구 목록을 조회")
    void findMyGymMachineByIdAndUserId_Success() {
        Long targetUserId = 1L;
        Long targetGymId = 100L;

        // 1. MyGym 생성 및 영속화
        MyGym myGym = MyGym.create(UserId.of(targetUserId), GymId.of(targetGymId));
        em.persist(myGym);

        // 2. 기구 1: 근육 매핑이 있는 기구 (숄더 프레스)
        CustomMachine machineWithMuscles = myGym.addMachine(
                "파라마운트 숄더 프레스",
                StandardExerciseId.of(10L),
                BodyPartCode.of("SHOULDER"),
                "의자 높이 3칸"
        );
        machineWithMuscles.addMuscle(MuscleId.of(1L), MuscleRole.PRIMARY);
        machineWithMuscles.addMuscle(MuscleId.of(2L), MuscleRole.SECONDARY);
        em.persist(machineWithMuscles);

        // 3. 기구 2: 근육 매핑이 '없는' 기구 (런닝머신 - 예외 케이스 검증용)
        CustomMachine machineWithoutMuscles = myGym.addMachine(
                "인클라인 런닝머신",
                StandardExerciseId.of(20L),
                BodyPartCode.of("CARDIO"),
                "경사도 10 고정"
        );
        em.persist(machineWithoutMuscles);

        // 영속성 컨텍스트 초기화 (실제 DB에 쿼리가 나가도록 강제)
        em.flush();
        em.clear();

        // when
        List<MyGymMachineReadModel> result = inventoryQueryRepository.findMyGymMachineByIdAndUserId(
                myGym.getMyGymId().value(),
                targetUserId
        );

        // then
        assertThat(result).hasSize(2); // 총 2개의 기구가 조회되어야 함

        // 4. 근육 매핑이 있는 기구 검증
        MyGymMachineReadModel firstMachine = result.stream()
                .filter(m -> m.name().equals("파라마운트 숄더 프레스"))
                .findFirst()
                .orElseThrow();

        assertThat(firstMachine.muscles()).hasSize(2);
        assertThat(firstMachine.muscles().get(0).muscleId().value()).isEqualTo(1L);
        assertThat(firstMachine.muscles().get(0).role()).isEqualTo(MuscleRole.PRIMARY);

        // 5. 근육 매핑이 없는 기구 검증 (빈 배열이어야 함)
        MyGymMachineReadModel secondMachine = result.stream()
                .filter(m -> m.name().equals("인클라인 런닝머신"))
                .findFirst()
                .orElseThrow();

        // NULL이 들어간 껍데기 객체가 아니라, 완벽하게 비어있는 리스트([])여야 테스트 통과
        assertThat(secondMachine.muscles()).isEmpty();
    }
}
