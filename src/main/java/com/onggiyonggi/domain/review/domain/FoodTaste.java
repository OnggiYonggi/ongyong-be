package com.onggiyonggi.domain.review.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum FoodTaste {

    GOOD("GOOD", "맛있어요"),
    ADEQUATE("SOSO", "그저 그랬어요"),
    OVER_FILLED("BAD", "별로에요");

    private final String key;
    private final String description;

}
