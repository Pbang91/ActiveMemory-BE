package com.example.activememory.reference.application.query;

import com.example.activememory.reference.application.query.model.GymReadModel;
import com.example.activememory.reference.domain.gym.repository.GymRepository;
import com.example.activememory.reference.domain.gym.service.PlaceSearchPort;
import com.example.activememory.reference.domain.gym.service.dto.PlaceSearchResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GymQueryService {
    private final PlaceSearchPort placeSearchPort;

    public GymQueryService(PlaceSearchPort placeSearchPort, GymRepository gymRepo) {
        this.placeSearchPort = placeSearchPort;
    }

    public List<GymReadModel> search(String query) {
        List<PlaceSearchResponse> results = placeSearchPort.searchByKeyword(query);

        return results
                .stream()
                .map(result -> new GymReadModel(
                        result.providerId(),
                        result.name(),
                        result.address(),
                        result.latitude(),
                        result.longitude()
                ))
                .toList();
    }
}
