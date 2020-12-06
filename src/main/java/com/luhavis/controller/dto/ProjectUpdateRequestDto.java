package com.luhavis.controller.dto;

import com.luhavis.domain.Manager;
import com.luhavis.domain.Project;
import com.luhavis.domain.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ProjectUpdateRequestDto {
    private String projectNm;
    private String upperProjectId;
    private String projectDesc;
    private String projectStatus;
    private int projectAmount;
    private Manager manager;
    private User user;

    @Builder
    public ProjectUpdateRequestDto(String projectNm, String upperProjectId, String projectDesc, String projectStatus, int projectAmount, Manager manager, User user) {
        this.projectNm = projectNm;
        this.upperProjectId = upperProjectId;
        this.projectDesc = projectDesc;
        this.projectStatus = projectStatus;
        this.projectAmount = projectAmount;
        this.manager = manager;
        this.user = user;
    }

}
