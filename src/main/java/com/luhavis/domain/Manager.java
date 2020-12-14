package com.luhavis.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class Manager extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 25, nullable = false)
    private String managerNm;

    @Column(length = 13)
    private String managerTelNo;

    @Column(nullable = false)
    private Long createdUser;

    @Column(nullable = false)
    private Long modifiedUser;

    @Builder
    public Manager(String managerNm, String managerTelNo, Long createdUser, Long modifiedUser) {
        this.managerNm = managerNm;
        this.managerTelNo = managerTelNo;
        this.createdUser = createdUser;
        this.modifiedUser = modifiedUser;
    }

    public void update(String managerNm, String managerTelNo, Long modifiedUser) {
        this.managerNm = managerNm;
        this.managerTelNo = managerTelNo;
        this.modifiedUser = modifiedUser;
    }
}
