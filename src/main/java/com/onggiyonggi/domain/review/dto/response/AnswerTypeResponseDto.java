package com.onggiyonggi.domain.review.dto.response;

import java.util.List;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class AnswerTypeResponseDto {

    private List<FillLevelResponseDto> fillLevelResponseDto;
    private List<FoodTasteResponseDto> foodTasteResponseDto;
    private List<ReusableContainerSizeResponseDto> reusableContainerSizeResponseDto;
    private List<ReusableContainerTypeResponseDto> reusableContainerTypeResponseDto;

    public static AnswerTypeResponseDto of (
        List<FillLevelResponseDto> fillLevelResponseDto,
        List<FoodTasteResponseDto> foodTasteResponseDto,
        List<ReusableContainerSizeResponseDto> reusableContainerSizeResponseDto,
        List<ReusableContainerTypeResponseDto> reusableContainerTypeResponseDto
        ) {
        return AnswerTypeResponseDto.builder()
            .fillLevelResponseDto(fillLevelResponseDto)
            .foodTasteResponseDto(foodTasteResponseDto)
            .reusableContainerSizeResponseDto(reusableContainerSizeResponseDto)
            .reusableContainerTypeResponseDto(reusableContainerTypeResponseDto)
            .build();
    }

}
