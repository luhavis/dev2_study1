package com.luhavis.controller.dto;

import com.luhavis.domain.Manager;
import com.luhavis.domain.Project;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ProjectSaveRequestDto {
    private String projectNm;
    private String upperProjectId;
    private String projectDesc;
    private String projectStatus;
    private int projectAmount;
    private Manager manager;

    public Project toEntity() {
        return Project.builder()
                .projectNm(projectNm)
                .upperProjectId(upperProjectId)
                .projectStatus(projectStatus)
                .projectAmount(projectAmount)
                .projectDesc(projectDesc)
                .manager(manager)
                .build();
    }

}
