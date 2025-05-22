package com.onggiyonggi.domain.review.dto.response;

import com.onggiyonggi.domain.review.domain.FillLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class FillLevelResponseDto {

    private String key;
    private String description;

    public static FillLevelResponseDto from(FillLevel type) {
        return new FillLevelResponseDto(type.getKey(), type.getDescription());
    }

}
