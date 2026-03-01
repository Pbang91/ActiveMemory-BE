package com.example.activememory.global.converter;

import com.example.activememory.inventory.domain.vo.MyGymId;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class MyGymIdConverter extends BaseIdConverter<MyGymId, Long> {
    public MyGymIdConverter() {
        super(MyGymId::of);
    }
}
