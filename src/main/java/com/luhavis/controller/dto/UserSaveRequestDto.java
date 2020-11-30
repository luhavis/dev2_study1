package com.luhavis.controller.dto;

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

    @Builder
    public UserSaveRequestDto(String userId, String userPw, String userNm, String corpNm, String telNo, String corpNo) {
        this.userId = userId;
        this.userPw = userPw;
        this.userNm = userNm;
        this.corpNm = corpNm;
        this.telNo = telNo;
        this.corpNo = corpNo;
    }

    public User toEntity() {
        return User.builder()
                .userId(userId)
                .userPw(userPw)
                .userNm(userNm)
                .corpNm(corpNm)
                .telNo(telNo)
                .corpNo(corpNo)
                .build();
    }
}
