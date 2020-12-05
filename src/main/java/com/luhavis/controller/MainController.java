package com.luhavis.controller;

import com.luhavis.controller.dto.ManagerSaveRequestDto;
import com.luhavis.controller.dto.UserSaveRequestDto;
import com.luhavis.service.ManagerService;
import com.luhavis.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RequiredArgsConstructor
@Controller
public class MainController {

    private final UserService userService;
    private final ManagerService managerService;

    @GetMapping("/")
    public String main() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        return "main";
    }

    @GetMapping("/login")
    public String signIn() {
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

    @GetMapping("/project")
    public String project() { return "project_list"; }

    @GetMapping("/projectReg")
    public String projectReg() { return "project_reg"; }

    @GetMapping("/projectEdit")
    public String projectEdit() { return "project_edit"; }

    @GetMapping("/manager")
    public String managerList(Model model) {
        model.addAttribute("list", managerService.getAll());
        return "manager_list";
    }

    @GetMapping("/managerReg")
    public String managerReg() {
        return "manager_reg";
    }

    @PostMapping("/managerReg")
    public RedirectView manageRegSave(ManagerSaveRequestDto requestDto) {
        long id = managerService.save(requestDto);
        return new RedirectView("/manager");
    }

    @GetMapping("/managerEdit")
    public String managerEdit() { return "manager_edit"; }
    @GetMapping("/member/mypage")
    public String mypage() { return "mypage"; }

    @PostMapping("/signup")
    public RedirectView signUpActoin(UserSaveRequestDto userSaveRequestDto) {
        long id = userService.save(userSaveRequestDto);
        return new RedirectView("/login");
    }
}
