package com.luhavis.domain;

import com.luhavis.domain.BaseTimeEntity;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@NoArgsConstructor
@Table(name="tb_user")
@ToString
@Entity
public class User extends BaseTimeEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 50, nullable = false)
    private String userId;

    @Column(length = 255, nullable = false)
    private String userPw;

    @Column(length = 25, nullable = false)
    private String userNm;

    @Column(length = 150)
    private String corpNm;

    @Column(length = 13)
    private String telNo;

    @Column(length = 20)
    private String corpNo;

    @Builder
    public User(String userId, String userPw, String userNm, String corpNm, String telNo, String corpNo) {
        this.userId = userId;
        this.userPw = userPw;
        this.userNm = userNm;
        this.corpNm = corpNm;
        this.telNo = telNo;
        this.corpNo = corpNo;
    }

}
