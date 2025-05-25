package com.onggiyonggi.domain.review.dto.response;

import com.onggiyonggi.domain.review.domain.FoodTaste;
import com.onggiyonggi.domain.review.domain.ReusableContainerSize;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class ReusableContainerSizeResponseDto {

    private String key;
    private String description;

    public static ReusableContainerSizeResponseDto from(ReusableContainerSize type) {
        return new ReusableContainerSizeResponseDto(type.getKey(), type.getDescription());
    }

}
