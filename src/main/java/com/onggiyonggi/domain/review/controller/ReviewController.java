package com.onggiyonggi.domain.review.controller;

import com.onggiyonggi.domain.review.dto.request.ReviewRequestDto;
import com.onggiyonggi.domain.review.dto.response.ReviewPreviewResponseDto;
import com.onggiyonggi.domain.review.dto.response.ReviewResponseDto;
import com.onggiyonggi.domain.review.service.ReviewService;
import com.onggiyonggi.domain.store.domain.StoreRank;
import com.onggiyonggi.domain.store.dto.request.StoreRequestDto;
import com.onggiyonggi.domain.store.dto.response.StoreDetailResponseDto;
import com.onggiyonggi.domain.store.facade.StoreReviewFacade;
import com.onggiyonggi.global.auth.CustomUserDetails;
import com.onggiyonggi.global.response.ApiResponse;
import com.onggiyonggi.global.response.Status;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
    private final StoreReviewFacade storeReviewFacade;

    @PostMapping("/")
    @Operation(summary = "리뷰 등록 API", description = "리뷰를 등록할 수 있는 API 입니다.")
    public ApiResponse<?> createReview(@RequestBody @Valid ReviewRequestDto requestDto,
        @AuthenticationPrincipal CustomUserDetails customUserDetails) {
        Long id = reviewService.createReview(requestDto, customUserDetails);
        storeReviewFacade.updateStoreRankByCondition(requestDto.getStoreId());
        return ApiResponse.success(Status.OK.getCode(),
            Status.OK.getMessage(), id);
    }

    @GetMapping("/{id}")
    @Operation(summary = "리뷰 상세 조회 API", description = "리뷰의 id 값으로 상세정보를 조회할 수 있는 API입니다")
    public ApiResponse<ReviewResponseDto> getReviewDetial(@PathVariable Long id, @AuthenticationPrincipal CustomUserDetails customUserDetails) {
        ReviewResponseDto responseDto = reviewService.getReviewDetail(id, customUserDetails);
        return ApiResponse.success(Status.OK.getCode(),
            Status.OK.getMessage(), responseDto);
    }

    @GetMapping("/my")
    @Operation(summary = "내가 작성한 리뷰 리스트 조회 API", description = "내가 작성한 리뷰들의 미리보기를 조회할 수 있는 API입니다")
    public ApiResponse<List<ReviewPreviewResponseDto>> getMyReviewPreview(@AuthenticationPrincipal CustomUserDetails customUserDetails) {
        List<ReviewPreviewResponseDto> responseDto = reviewService.getMyReviewPreview(customUserDetails);
        return ApiResponse.success(Status.OK.getCode(),
            Status.OK.getMessage(), responseDto);
    }

}
