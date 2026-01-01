package com.example.activememory.account.presentation.command;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "User Command API")
@RestController
@RequestMapping("/api/v1/users")
public class UserCommandController {
    public void login() {
    }
}
