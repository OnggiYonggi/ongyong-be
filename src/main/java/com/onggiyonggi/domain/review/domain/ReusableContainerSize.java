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
    TUMBLER_100ML(ReusableContainerType.TUMBLER, "100ml"),
    TUMBLER_500ML(ReusableContainerType.TUMBLER, "500ml"),
    TUMBLER_1L(ReusableContainerType.TUMBLER, "1L"),

    // 락앤락
    LOCK_AND_LOCK_SMALL(ReusableContainerType.LOCK_AND_LOCK, "소"),
    LOCK_AND_LOCK_MEDIUM(ReusableContainerType.LOCK_AND_LOCK, "중"),
    LOCK_AND_LOCK_LARGE(ReusableContainerType.LOCK_AND_LOCK, "대"),

    // 냄비
    POT_SMALL(ReusableContainerType.POT, "소"),
    POT_MEDIUM(ReusableContainerType.POT, "중"),
    POT_LARGE(ReusableContainerType.POT, "대"),

    // 후라이팬
    FRYING_PAN_SMALL(ReusableContainerType.FRYING_PAN, "소"),
    FRYING_PAN_MEDIUM(ReusableContainerType.FRYING_PAN, "중"),
    FRYING_PAN_LARGE(ReusableContainerType.FRYING_PAN, "대");

    private final ReusableContainerType containerType;
    private final String sizeLabel;

    // 특정 용기에 해당하는 사이즈 리스트 반환
    public static List<ReusableContainerSize> getSizesByContainerType(ReusableContainerType type) {
        return Arrays.stream(values())
            .filter(size -> size.containerType == type)
            .collect(Collectors.toList());
    }

}
