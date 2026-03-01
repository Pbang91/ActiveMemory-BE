package com.example.activememory.reference.application.command.service;

import com.example.activememory.reference.domain.gym.entity.Gym;
import com.example.activememory.reference.domain.gym.repository.GymRepository;

import com.example.activememory.reference.domain.gym.vo.GymId;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class GymPort {
    private final GymRepository gymRepository;

    @Transactional
    public GetOrCreateGymResponse getOrCreateGym(GetOrCreateGymCommand command) {
        return new GetOrCreateGymResponse(
                gymRepository.findByProviderId(command.providerId())
                        .map((Gym::getGymId))
                        .orElseGet(() -> {
                            Gym newGym = Gym.of(
                                    command.providerId(),
                                    command.name(),
                                    command.address(),
                                    command.x(),
                                    command.y()
                            );

                            return gymRepository.save(newGym).getGymId();
                        })
        );
    }

    public record GetOrCreateGymCommand(
            String providerId,
            String name,
            String address,
            String x,
            String y
    ) {
    }

    public record GetOrCreateGymResponse(
            GymId gymId
    ) {

    }
}
