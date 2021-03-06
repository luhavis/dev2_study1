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

    @Column
    private String upperProjectId;

    @Column(columnDefinition = "TEXT")
    private String projectDesc;

    @Column
    private String projectStatus;

    @Column
    private int projectAmount;

    @Column
    private Long createdUser;

    @Column
    private Long modifiedUser;


    @OneToOne
    @JoinColumn(name = "manager_id")
    private Manager manager;


    @Builder
    public Project(String projectNm, String upperProjectId, String projectDesc, String projectStatus, int projectAmount, User user, Manager manager, Long createdUser, Long modifiedUser) {
        this.projectNm = projectNm;
        this.upperProjectId = upperProjectId;
        this.projectDesc = projectDesc;
        this.projectStatus = projectStatus;
        this.projectAmount = projectAmount;
        this.user = user;
        this.manager = manager;
        this.createdUser = createdUser;
        this.modifiedUser = modifiedUser;
    }

    public void update(String projectNm, String upperProjectId, String projectDesc, String projectStatus, int projectAmount, User user, Manager manager, Long modifiedUser) {
        this.projectNm = projectNm;
        this.upperProjectId = upperProjectId;
        this.projectDesc = projectDesc;
        this.projectStatus = projectStatus;
        this.projectAmount = projectAmount;
        this.user = user;
        this.manager = manager;
        this.modifiedUser = modifiedUser;
    }
}
