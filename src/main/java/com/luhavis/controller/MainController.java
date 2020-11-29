package com.luhavis.controller;

import com.luhavis.controller.dto.UserSaveRequestDto;
import com.luhavis.domain.UserRepository;
import com.luhavis.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class MainController {

    private final UserService userService;

    @GetMapping("/")
    public String main() {
        return "main";
    }
}
