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
    @JoinColumn(name = "id")
    private User user;

    @Column(length = 255, nullable = false)
    private String projectNm;

    @Column
    private Long upperId;

    @Column(columnDefinition = "TEXT")
    private String projectDesc;

    @Column
    private String projectStatus;

    @Column
    private int projectAmount;

    @Builder
    public Project(String projectNm, long upperId, String projectDesc, String projectStatus, int projectAmount, User user) {
        this.projectNm = projectNm;
        this.upperId = upperId;
        this.projectDesc = projectDesc;
        this.projectStatus = projectStatus;
        this.projectAmount = projectAmount;
        this.user = user;
    }
}
