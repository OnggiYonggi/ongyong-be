package com.onggiyonggi.domain.review.controller;

import com.onggiyonggi.domain.review.dto.request.ReviewRequestDto;
import com.onggiyonggi.domain.review.service.ReviewService;
import com.onggiyonggi.domain.store.domain.StoreRank;
import com.onggiyonggi.domain.store.dto.request.StoreRequestDto;
import com.onggiyonggi.global.auth.CustomUserDetails;
import com.onggiyonggi.global.response.ApiResponse;
import com.onggiyonggi.global.response.Status;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/review")
@RequiredArgsConstructor
@Tag(name = "Review", description = "리뷰 api")
public class ReviewController {

    private final ReviewService reviewService;

    @PostMapping("/")
    @Operation(summary = "리뷰 등록 API", description = "리뷰를 등록할 수 있는 API 입니다.")
    public ApiResponse<?> createReview(@RequestBody @Valid ReviewRequestDto requestDto,
        @AuthenticationPrincipal CustomUserDetails customUserDetails) {
        Long id = reviewService.createReview(requestDto, customUserDetails);
        return ApiResponse.success(Status.OK.getCode(),
            Status.OK.getMessage(), id);
    }

}
