package com.onggiyonggi.domain.review.dto.response;

import com.onggiyonggi.domain.review.domain.FillLevel;
import com.onggiyonggi.domain.review.domain.FoodTaste;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class FoodTasteResponseDto {

    private String key;
    private String description;

    public static FoodTasteResponseDto from(FoodTaste type) {
        return new FoodTasteResponseDto(type.getKey(), type.getDescription());
    }

}
