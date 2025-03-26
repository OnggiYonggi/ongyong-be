package com.onggiyonggi.domain.member.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Role {

    CUSTOMER("ROLE_CUSTOMER", "소비자"),
    STORE_OWNER("ROLE_STORE_OWNER", "가게 사장"),
    SUPER("ROLE_SUPER", "슈퍼 관리자");

    private String key;

    private String description;

}
