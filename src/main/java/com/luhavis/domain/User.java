package com.luhavis.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;

@Getter
@NoArgsConstructor
@ToString
@Entity
public class User extends BaseTimeEntity {

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

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    @Column(columnDefinition = "varchar(25) not null default 'BASIC'")
    private String userType;

    @Builder
    public User(String userId, String userPw, String userNm, String corpNm, String telNo, String corpNo, Role role, String userType) {
        this.userId = userId;
        this.userPw = userPw;
        this.userNm = userNm;
        this.corpNm = corpNm;
        this.telNo = telNo;
        this.corpNo = corpNo;
        this.role = role;
        this.userType = userType;
    }

    public String getRoleKey() {
        return this.role.getKey();
    }

}
