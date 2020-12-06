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


    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Builder
    public Manager(String managerNm, String managerTelNo) {
        this.managerNm = managerNm;
        this.managerTelNo = managerTelNo;
    }

    public void update(String managerNm, String managerTelNo) {
        this.managerNm = managerNm;
        this.managerTelNo = managerTelNo;
    }
}
