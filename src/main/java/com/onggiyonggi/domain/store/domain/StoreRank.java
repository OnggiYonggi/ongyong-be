package com.onggiyonggi.domain.store.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum StoreRank {

    ROOKIE("ROOKIE", "루키", 0),
    BAN("BAN", "다회용기 사용 불가", 0),
    BRONZE("BRONZE", "브론즈", 30),
    SILVER("SILVER", "실버", 99),
    GOLD("GOLD", "골드", Integer.MAX_VALUE);

    private final String key;
    private final String description;
    private final Integer boundaryValue;

}