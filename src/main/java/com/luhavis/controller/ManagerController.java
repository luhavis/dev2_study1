package com.luhavis.controller;

import com.luhavis.controller.dto.ManagerSaveRequestDto;
import com.luhavis.controller.dto.ManagerUpdateRequestDto;
import com.luhavis.service.ManagerService;
import lombok.AllArgsConstructor;
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
public class ManagerController {

    private final ManagerService managerService;

    @GetMapping("/manager")
    public String managerList(Model model, @RequestParam(value="searchKeyword", required = false) String searchKeyword,
                              @RequestParam(value="searchKeywordValue", required = false) String searchKeywordValue, final Pageable pageable) {
        if ("managerNm".equals(searchKeyword)) {
            model.addAttribute("list", managerService.findByManagerNmContaining(searchKeywordValue, pageable));
        } else {
            model.addAttribute("list", managerService.getAll(pageable));
        }
        model.addAttribute("searchKeywordValue", searchKeywordValue);
        model.addAttribute("searchKeyword", searchKeyword);
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
    public String managerEdit(Model model, long id) {
        model.addAttribute("result", managerService.findById(id));
        return "manager_edit";
    }

    @PostMapping("/managerEdit")
    public RedirectView managerEdit(Long id, ManagerUpdateRequestDto requestDto) {
        managerService.update(id, requestDto);
        return new RedirectView("/manager");
    }
}
