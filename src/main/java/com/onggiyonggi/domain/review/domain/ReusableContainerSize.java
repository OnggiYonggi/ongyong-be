package com.onggiyonggi.domain.review.domain;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ReusableContainerSize {

    // 텀블러
    TUMBLER_100ML("TUMBLER_100ML", "100ml"),
    TUMBLER_500ML("TUMBLER_500ML", "500ml"),
    TUMBLER_1L("TUMBLER_1L", "1L"),

    // 락앤락
    LOCK_AND_LOCK_SMALL("LOCK_AND_LOCK_SMALL", "소"),
    LOCK_AND_LOCK_MEDIUM("LOCK_AND_LOCK_MEDIUM", "중"),
    LOCK_AND_LOCK_LARGE("LOCK_AND_LOCK_LARGE", "대"),

    // 냄비
    POT_SMALL("POT_SMALL", "소"),
    POT_MEDIUM("POT_MEDIUM", "중"),
    POT_LARGE("POT_LARGE", "대"),

    // 후라이팬
    FRYING_PAN_SMALL("FRYING_PAN_SMALL", "소"),
    FRYING_PAN_MEDIUM("FRYING_PAN_MEDIUM", "중"),
    FRYING_PAN_LARGE("FRYING_PAN_LARGE", "대");

    private final String key;
    private final String description;
}
