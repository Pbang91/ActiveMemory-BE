package com.example.activememory.record.presentation.query;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/v1/records")
@RestController
@Tag(name = "기록 Query API", description = "기록과 연관된 화면에서 필요한 정보 전달 설명")
public class RecordQueryController {
}
