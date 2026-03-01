package com.example.activememory.inventory.application.command.service;

import com.example.activememory.account.user.domain.vo.UserId;
import com.example.activememory.global.exception.BusinessException;
import com.example.activememory.global.exception.ExceptionCode;
import com.example.activememory.inventory.application.command.dto.RegisterMyGymCommand;
import com.example.activememory.inventory.domain.entity.MyGym;
import com.example.activememory.inventory.domain.repository.MyGymRepository;
import com.example.activememory.reference.application.command.service.GymPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class InventoryCommandService {
    private final MyGymRepository myGymRepository;
    private final GymPort gymPort;

    public Long registerMyGym(Long userId, RegisterMyGymCommand command) {
        GymPort.GetOrCreateGymResponse gym = gymPort.getOrCreateGym(
                new GymPort.GetOrCreateGymCommand(
                        command.providerId(),
                        command.name(),
                        command.address(),
                        command.x(),
                        command.y()
                )
        );

        if (myGymRepository.existsByUserIdAndGymId(UserId.of(userId), gym.gymId())) {
            throw new BusinessException(ExceptionCode.ALREADY_EXIST_MY_GYM);
        }

        MyGym myGym = MyGym.create(UserId.of(userId), gym.gymId());

        myGymRepository.save(myGym);

        return myGym.getMyGymId().value();
    }
}
