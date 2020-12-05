package com.luhavis.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Role {
    MEMBER("ROLE_MEMBER", "일반유저"),
    ADMIN("ROLE_ADMIN", "관리자");

    private String key;
    private String title;
}
