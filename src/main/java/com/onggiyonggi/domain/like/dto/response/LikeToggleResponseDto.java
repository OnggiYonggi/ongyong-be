package com.onggiyonggi.domain.like.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class LikeToggleResponseDto {

    private Long reviewId;
    private Long likes;

}
