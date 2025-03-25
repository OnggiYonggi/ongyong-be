package com.onggiyonggi.domain.review.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ReusableContainerType {

    TUMBLER("TUMBLER", "텀블러"),
    LOCK_AND_LOCK("LOCK_AND_LOCK", "락앤락"),
    POT("POT", "냄비"),
    FRYING_PAN("FRYING_PAN", "후라이팬");

    private final String key;
    private final String description;

}
