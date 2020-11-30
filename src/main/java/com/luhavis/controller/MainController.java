package com.luhavis.controller;

import com.luhavis.controller.dto.RequestUserDTO;
import com.luhavis.controller.dto.UserSaveRequestDto;
import com.luhavis.domain.UserRepository;
import com.luhavis.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Controller
public class MainController {

    private final UserService userService;

    @GetMapping("/")
    public String main() {
        return "main";
    }

    @GetMapping("/signin")
    public String signIn() { return "signin"; }

    @GetMapping("/signup")
    public String signUp() {
        return "signup";
    }

    @GetMapping("/project")
    public String project() { return "project_list"; }

    @GetMapping("/projectReg")
    public String projectReg() { return "project_reg"; }

    @GetMapping("/projectEdit")
    public String projectEdit() { return "project_edit"; }

    @GetMapping("/manager")
    public String managerList(Model model) {
        model.addAttribute("list", userService.getAll());
        return "manager_list";
    }

    @GetMapping("/managerReg")
    public String managerReg() {
        return "manager_reg";
    }

    @GetMapping("/managerEdit")
    public String managerEdit() { return "manager_edit"; }

    @PostMapping("/signup")
    public RedirectView signUpActoin(@RequestBody MultiValueMap<String, String> data) {

        UserSaveRequestDto saveRequestDto = UserSaveRequestDto.builder()
                .userId(data.get("userId").get(0))
                .userPw(data.get("userPw").get(0))
                .userNm(data.get("userNm").get(0))
                .corpNm(data.get("corpNm").get(0))
                .corpNo(data.get("corpNo").get(0))
                .telNo(data.get("telNo").get(0))
                .build();

        long id = userService.save(saveRequestDto);
        return new RedirectView("/manager");
    }
}
