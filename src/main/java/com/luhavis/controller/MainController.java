package com.luhavis.controller;

import com.luhavis.controller.dto.UserSaveRequestDto;
import com.luhavis.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@RequiredArgsConstructor
@Controller
public class MainController {

    private final UserService userService;

    @GetMapping("/")
    public String main() { return "main"; }

    @GetMapping("/login")
    public String signIn() { return "login"; }

    @GetMapping("/signup")
    public String signUp() {
        return "signup";
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        new SecurityContextLogoutHandler().logout(request, response, SecurityContextHolder.getContext().getAuthentication());
        return "redirect:/login";
    }



    @GetMapping("/member/mypage")
    public String mypage() { return "mypage"; }

    @PostMapping("/signup")
    public RedirectView signUpAction(UserSaveRequestDto userSaveRequestDto) {
        long id = userService.save(userSaveRequestDto);
        return new RedirectView("/login");
    }
}
