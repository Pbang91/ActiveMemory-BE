package com.example.activememory.inventory.application.command.service;

import com.example.activememory.global.exception.BusinessException;
import com.example.activememory.global.exception.ExceptionCode;
import com.example.activememory.inventory.application.command.dto.RegisterMyGymCommand;
import com.example.activememory.inventory.application.command.dto.RegisterMyGymMachineCommand;
import com.example.activememory.inventory.application.command.dto.UpdateMyGymMachineCommand;
import com.example.activememory.inventory.domain.entity.CustomMachine;
import com.example.activememory.inventory.domain.entity.MyGym;
import com.example.activememory.inventory.domain.repository.MyGymRepository;
import com.example.activememory.reference.application.command.service.GymPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class InventoryCommandService {
    private final MyGymRepository myGymRepo;
    private final GymPort gymPort;

    @Transactional
    public Long registerMyGym(RegisterMyGymCommand command) {
        GymPort.GetOrCreateGymResponse gym = gymPort.getOrCreateGym(
                new GymPort.GetOrCreateGymCommand(
                        command.providerId(),
                        command.name(),
                        command.address(),
                        command.x(),
                        command.y()
                )
        );

        if (myGymRepo.existsByUserIdAndGymId(command.userId(), gym.gymId())) {
            throw new BusinessException(ExceptionCode.ALREADY_EXIST_MY_GYM);
        }

        MyGym myGym = MyGym.create(command.userId(), gym.gymId());

        return myGym.getMyGymId().value();
    }

    @Transactional
    public Long registerMyGymMachine(RegisterMyGymMachineCommand command) {
        MyGym myGym = myGymRepo.findByIdAndUserId(command.myGymId().value(), command.userId())
                .orElseThrow(() -> new BusinessException(ExceptionCode.INVALID_MY_GYM));

        CustomMachine newMachine = myGym.addMachine(
                command.name(),
                command.standardExerciseId(),
                command.bodyPartCode(),
                command.memo()
        );

        command.muscleMappingDataList().forEach(data -> {
            newMachine.addMuscle(data.muscleId(), data.role());
        });

        return newMachine.getCustomMachineId().value();
    }

    @Transactional
    public Long updateMyGymMachine(UpdateMyGymMachineCommand command) {
        MyGym myGym = myGymRepo.findByIdAndUserId(command.myGymId().value(), command.userId())
                .orElseThrow(() -> new BusinessException(ExceptionCode.INVALID_MY_GYM));

        CustomMachine targetMachine = myGym.getMachines()
                .stream()
                .filter((c) -> c.getCustomMachineId().equals(command.customMachineId()))
                .findFirst()
                .orElseThrow(() -> new BusinessException(ExceptionCode.INVALID_PARAMETER, "등록된 기구가 없습니다"));


        // 3. 기본 정보(이름, 종목, 부위, 메모) 전체 덮어쓰기
        targetMachine.updateInfo(
                command.name(),
                command.standardExerciseId(),
                command.bodyPartCode(),
                command.memo()
        );

        targetMachine.clearMuscles();

        command.muscleMappingDataList().forEach(data ->
                targetMachine.addMuscle(data.muscleId(), data.role())
        );

        return command.customMachineId().value();
    }
}
