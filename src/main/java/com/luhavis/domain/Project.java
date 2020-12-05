package com.luhavis.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class Project extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(length = 255, nullable = false)
    private String projectNm;

    @OneToOne
    @JoinColumn(name = "project_id")
    private Project upperProjectId;

    @Column(columnDefinition = "TEXT")
    private String projectDesc;

    @Column
    private String projectStatus;

    @Column
    private int projectAmount;

    @Builder
    public Project(String projectNm, Project upperProjectId, String projectDesc, String projectStatus, int projectAmount, User user) {
        this.projectNm = projectNm;
        this.upperProjectId = upperProjectId;
        this.projectDesc = projectDesc;
        this.projectStatus = projectStatus;
        this.projectAmount = projectAmount;
        this.user = user;
    }
}
