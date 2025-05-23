package com.onggiyonggi.domain.review.dto.response;

import com.onggiyonggi.domain.review.domain.Review;
import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class ReviewPreviewResponseDto {

    private String imageURL;
    private Long id;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public static ReviewPreviewResponseDto from(Review review) {
        return ReviewPreviewResponseDto.builder()
            .imageURL(review.getImageURL())
            .id(review.getId())
            .createdAt(review.getCreatedAt())
            .updatedAt(review.getUpdatedAt())
            .build();
    }

}
