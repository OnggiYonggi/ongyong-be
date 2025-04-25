package com.onggiyonggi.domain.store.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum StoreRank {

    ROOKIE("ROOKIE", "루키"),
    BAN("BAN", "다회용기 사용 불가"),
    BRONZE("BRONZE", "브론즈"),
    SILVER("SILVER", "실버"),
    GOLD("GOLD", "골드");

    private final String key;
    private final String description;

}