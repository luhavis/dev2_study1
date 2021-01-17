package com.luhavis.controller;

import com.luhavis.controller.dto.UserSaveRequestDto;
import com.luhavis.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigInteger;
import java.security.SecureRandom;


@RequiredArgsConstructor
@Controller
public class MainController {

    private final UserService userService;

    @GetMapping("/")
    public String main() { return "main"; }

    @GetMapping("/login")
    public String signIn(HttpServletRequest request, Model model) {
        SecureRandom random = new SecureRandom();
        String state = new BigInteger(130, random).toString(32);
        request.getSession().setAttribute("state", state);
        model.addAttribute("state", state);
        model.addAttribute("authURI", "https://nid.naver.com/oauth2.0/authorize?client_id=UlAHKcKy5rPFZH9Mm_8Z&response_type=code&redirect_uri=http://localhost:8081/auth/callback&state="+state);
        return "login";
    }

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
