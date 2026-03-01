package com.example.activememory.inventory.application.query;

import com.example.activememory.inventory.application.query.model.MyGymReadModel;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InventoryQueryService {
    private final InventoryQueryRepository inventoryRepo;

    public InventoryQueryService(InventoryQueryRepository inventoryRepo) {
        this.inventoryRepo = inventoryRepo;
    }

    public List<MyGymReadModel> getMyGym(Long userId) {
        return inventoryRepo.findMyGymByUserId(userId);
    }
}
