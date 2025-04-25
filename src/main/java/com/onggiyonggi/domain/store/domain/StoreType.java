package com.onggiyonggi.domain.store.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum StoreType {

    CAFE("CAFE", "카페"),
    FOOD("FOOD", "음식점");

    private final String key;
    private final String description;

}