package com.example.activememory.user.presentation.command;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/v1/users/my-routine")
@RestController
@Tag(name = "마이루틴 Command API", description = "회원의 마이루틴 생성, 수정, 삭제 등에 대한 설명")
public class MyRoutineCommandController {

}
