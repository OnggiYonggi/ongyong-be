package com.onggiyonggi.domain.review.dto.response;

import com.onggiyonggi.domain.item.dto.response.ItemResponseDto;
import com.onggiyonggi.domain.review.domain.FillLevel;
import com.onggiyonggi.domain.review.domain.FoodTaste;
import com.onggiyonggi.domain.review.domain.ReusableContainerSize;
import com.onggiyonggi.domain.review.domain.ReusableContainerType;
import com.onggiyonggi.domain.review.domain.Review;
import java.util.List;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class ReviewResponseDto {

    private Long id;
    private String memberId;
    private String imageURL;
    private String content;
    private ReusableContainerType reusableContainerType;
    private ReusableContainerSize reusableContainerSize;
    private FillLevel fillLevel;
    private FoodTaste foodTaste;
    private Long likes;
    private Boolean hasLikeByMe;
    private List<ItemResponseDto> itemResponseDtoList;

    public static ReviewResponseDto toDto(Review review) {
        return ReviewResponseDto.builder()
            .id(review.getId())
            .memberId(review.getMember().getId())
            .imageURL(review.getImageURL())
            .content(review.getContent())
            .reusableContainerType(review.getReusableContainerType())
            .reusableContainerSize(review.getReusableContainerSize())
            .fillLevel(review.getFillLevel())
            .foodTaste(review.getFoodTaste())
            .likes(review.getLikes())
            .build();
    }

}
