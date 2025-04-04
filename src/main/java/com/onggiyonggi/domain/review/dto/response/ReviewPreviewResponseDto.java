package com.onggiyonggi.domain.review.dto.response;

import com.onggiyonggi.domain.review.domain.Review;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class ReviewPreviewResponseDto {

    private String imageURL;
    private Long id;

    public static ReviewPreviewResponseDto from(Review review) {
        return ReviewPreviewResponseDto.builder()
            .imageURL(review.getImageURL())
            .id(review.getId())
            .build();
    }

}
