package com.luhavis.controller.dto;

import com.luhavis.domain.Role;
import com.luhavis.domain.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserSaveRequestDto {

    private String userId;
    private String userPw;
    private String userNm;
    private String corpNm;
    private String telNo;
    private String corpNo;
    private String userType;
    private String userEmail;
    private String userNickName;

    @Builder
    public UserSaveRequestDto(String userId, String userPw, String userNm, String corpNm, String telNo, String corpNo, String userType, String userEmail, String userNickName) {
        this.userId = userId;
        this.userPw = userPw;
        this.userNm = userNm;
        this.corpNm = corpNm;
        this.telNo = telNo;
        this.corpNo = corpNo;
        this.userType = userType;
        this.userEmail = userEmail;
        this.userNickName = userNickName;
    }

    public User toEntity() {
        return User.builder()
                .userId(userId)
                .userPw(userPw)
                .userNm(userNm)
                .corpNm(corpNm)
                .telNo(telNo)
                .corpNo(corpNo)
                .role(Role.MEMBER)
                .userType(userType)
                .userEmail(userEmail)
                .userNickName(userNickName)
                .build();
    }
}
