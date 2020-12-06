package com.luhavis.controller.dto;

import com.luhavis.domain.Manager;
import com.luhavis.domain.Project;
import com.luhavis.domain.User;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class ProjectRequestDto {
    private Long id;
    private String projectNm;
    private String upperProjectId;
    private String projectDesc;
    private String projectStatus;
    private int projectAmount;
    private Manager manager;
    private User user;
    private LocalDateTime createDate;
    private LocalDateTime modifiedDate;


    public ProjectRequestDto(Project project) {
        this.id = project.getId();
        this.projectNm = project.getProjectNm();
        this.upperProjectId = project.getUpperProjectId();
        this.projectDesc = project.getProjectDesc();
        this.projectStatus = project.getProjectStatus();
        this.projectAmount = project.getProjectAmount();
        this.manager = project.getManager();
        this.user = project.getUser();
        this.createDate = project.getCreateDate();
        this.modifiedDate = project.getModifiedDate();
    }
}
