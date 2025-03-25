package com.onggiyonggi.domain.review.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum FillLevel {

    UNDER_FILLED("UNDER_FILLED", "여유롭게 담았어요"),
    ADEQUATE("ADEQUATE", "딱 맞게 담았어요"),
    OVER_FILLED("OVER_FILLED", "다 못 담았어요");

    private final String key;
    private final String description;

}
