package com.onggiyonggi.domain.like.controller;

import com.onggiyonggi.domain.like.dto.response.LikeToggleResponseDto;
import com.onggiyonggi.domain.like.service.LikeService;
import com.onggiyonggi.global.auth.CustomUserDetails;
import com.onggiyonggi.global.response.ApiResponse;
import com.onggiyonggi.global.response.Status;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/like")
@RequiredArgsConstructor
@Tag(name = "Like", description = "좋아요 api")
public class LikeController {

    private final LikeService likeService;

    @Operation(summary = "[U] 리뷰 좋아요 누르기/취소하기", description = "내부적으로 좋아요가 눌러져 있으면 취소를 하고 눌러져 있지 않으면 누르게 동작합니다.")
    @PostMapping("/likes/{reviewId}")
    public ApiResponse<?> toggleLikeStatus(
        @PathVariable(name = "reviewId") Long reviewId,
        @AuthenticationPrincipal CustomUserDetails customUserDetails
    ) {
        LikeToggleResponseDto responseDto = likeService.toggleLikeStatus(reviewId, customUserDetails);
        return ApiResponse.success(Status.OK.getCode(),
            Status.OK.getMessage(), responseDto);
    }

}
