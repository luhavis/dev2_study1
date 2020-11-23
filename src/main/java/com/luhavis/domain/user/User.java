package com.luhavis.domain.user;

import com.luhavis.domain.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class User extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 50, nullable = false)
    private String userId;

    @Column(length = 255, nullable = false)
    private String userPw;

    @Column(length = 150)
    private String corpNm;

    @Column(length = 13)
    private String telNo;

    @Column(length = 20)
    private String corpNo;

    @Builder
    public User(String userId, String userPw, String corpNm, String telNo, String corpNo) {
        this.userId = userId;
        this.userPw = userPw;
        this.corpNm = corpNm;
        this.telNo = telNo;
        this.corpNo = corpNo;
    }
}
