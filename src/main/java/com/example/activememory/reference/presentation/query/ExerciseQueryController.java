package com.example.activememory.reference.presentation.query;

import com.example.activememory.global.api.Api;
import com.example.activememory.global.api.SuccessResDto;
import com.example.activememory.reference.application.query.ExerciseQueryService;
import com.example.activememory.reference.application.query.model.BodyPartReadModel;
import com.example.activememory.reference.application.query.model.ExerciseTypeReadModel;
import com.example.activememory.reference.application.query.model.StandardExerciseReadModel;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name = "Reference Query API")
@RestController
@RequestMapping("/api/v1/references/exercise")
public class ExerciseQueryController {
    private final ExerciseQueryService service;

    public ExerciseQueryController(ExerciseQueryService service) {
        this.service = service;
    }

    @GetMapping("/body-parts")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "근육 대분류 정보를 전달하는 API", description = "최종 수정일: 2026.01.11")
    public ResponseEntity<SuccessResDto<List<BodyPartReadModel>>> getBodyPart() {
        return Api.success(service.findBodyParts(), HttpStatus.OK);
    }

    @GetMapping("/type")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "운동 종류 정보를 전달하는 API", description = "최종 수정일: 2026.01.11")
    public ResponseEntity<SuccessResDto<List<ExerciseTypeReadModel>>> getExerciseType() {
        return Api.success(service.getExerciseType(), HttpStatus.OK);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "운동 정보를 전달하는 API", description = "최종 수정일: 2026.01.11")
    public ResponseEntity<SuccessResDto<List<StandardExerciseReadModel>>> getStandardExercises() {
        return Api.success(service.findStandardExercises(), HttpStatus.OK);
    }

    @GetMapping("/muscles")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "등록된 근육 정보를 전달하는 API", description = "최종 수정일: 2026.03.11")
    public ResponseEntity<SuccessResDto<List<?>>> getStandardMuscles() {
        return Api.success(service.findStandardMuscles(), HttpStatus.OK);
    }
}
