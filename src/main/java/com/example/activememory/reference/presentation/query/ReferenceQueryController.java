package com.example.activememory.reference.presentation.query;

import com.example.activememory.reference.application.query.ReferenceQueryService;
import com.example.activememory.reference.application.query.model.BodyPartReadModel;
import com.example.activememory.reference.application.query.model.StandardExerciseReadModel;
import io.swagger.v3.oas.annotations.security.SecurityRequirements;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@SecurityRequirements
@Tag(name = "Reference Query API")
@RestController
@RequestMapping("/api/v1/references")
public class ReferenceQueryController {
    private final ReferenceQueryService queryService;

    public ReferenceQueryController(ReferenceQueryService queryService) {
        this.queryService = queryService;
    }

    @GetMapping("/body-parts")
    public List<BodyPartReadModel> getBodyPart() {
        return queryService.findBodyParts();
    }

    @GetMapping("/standard-exercises")
    public List<StandardExerciseReadModel> getStandardExercises() {
        return queryService.findStandardExercises();
    }
}
