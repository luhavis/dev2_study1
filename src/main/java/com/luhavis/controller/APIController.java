package com.luhavis.controller;

import com.luhavis.controller.dto.UserSaveRequestDto;
import com.luhavis.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class APIController {

    private final UserService userService;

    @PostMapping("/api/users")
    public String user(@RequestBody UserSaveRequestDto requestDto) {
        return userService.save(requestDto);
    }
}
