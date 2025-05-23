package com.onggiyonggi.domain.review.dto.response;

import com.onggiyonggi.domain.review.domain.ReusableContainerType;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class ReusableContainerTypeResponseDto {

    private String key;
    private String description;

    public static ReusableContainerTypeResponseDto from(ReusableContainerType type) {
        return new ReusableContainerTypeResponseDto(type.getKey(), type.getDescription());
    }

}
