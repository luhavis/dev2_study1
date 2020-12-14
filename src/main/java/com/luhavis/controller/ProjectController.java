package com.luhavis.controller;

import com.luhavis.controller.dto.ProjectSaveRequestDto;
import com.luhavis.controller.dto.ProjectUpdateRequestDto;
import com.luhavis.service.ManagerService;
import com.luhavis.service.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

@RequiredArgsConstructor
@Controller
public class ProjectController {

    private final ProjectService projectService;
    private final ManagerService managerService;

    @GetMapping("/project")
    public String project(Model model, @RequestParam(value = "searchKeyword", required = false) String searchKeyword
            , @RequestParam(value = "searchKeywordValue", required = false) String searchKeywordValue
            , final Pageable pageable) {
        if ("managerNm".equals(searchKeyword)) {
            model.addAttribute("list", projectService.findByManager_ManagerNmContaining(searchKeywordValue, pageable));
        } else if ("projectNm".equals(searchKeyword)) {
            model.addAttribute("list", projectService.findByProjectNmContaining(searchKeywordValue, pageable));
        } else {
            model.addAttribute("list", projectService.getAll(pageable));
        }


        model.addAttribute("searchKeyword", searchKeyword);
        model.addAttribute("searchKeywordValue", searchKeywordValue);
        return "project_list";
    }

    @GetMapping("/projectReg")
    public String projectReg(Model model) {
        model.addAttribute("managerList", managerService.getAll());
        model.addAttribute("projectList", projectService.getAll());
        return "project_reg";
    }

    @PostMapping("/projectReg")
    public RedirectView projectRegSave(ProjectSaveRequestDto requestDto) {
        long id = projectService.save(requestDto);
        return new RedirectView("/project");
    }


    @GetMapping("/projectEdit")
    public String projectEdit(Model model, long id) {
        model.addAttribute("result", projectService.findById(id));

        model.addAttribute("managerList", managerService.getAll());
        model.addAttribute("projectList", projectService.getAll());
        return "project_edit";
    }

    @PostMapping("/projectEdit")
    public RedirectView projectEdit(Long id, ProjectUpdateRequestDto requestDto) {
        projectService.update(id, requestDto);
        return new RedirectView("/project");
    }
}
