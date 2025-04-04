package com.onggiyonggi.domain.review.service;

import com.onggiyonggi.domain.member.domain.Member;
import com.onggiyonggi.domain.review.domain.Review;
import com.onggiyonggi.domain.review.dto.request.ReviewRequestDto;
import com.onggiyonggi.domain.review.dto.response.ReviewPreviewResponseDto;
import com.onggiyonggi.domain.review.dto.response.ReviewResponseDto;
import com.onggiyonggi.domain.review.repository.ReviewRepository;
import com.onggiyonggi.domain.store.domain.Store;
import com.onggiyonggi.domain.store.service.StoreService;
import com.onggiyonggi.global.auth.CustomUserDetails;
import com.onggiyonggi.global.response.GeneralException;
import com.onggiyonggi.global.response.Status;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final StoreService storeService;

    public Long createReview(ReviewRequestDto requestDto, CustomUserDetails customUserDetails) {
        Review review = Review.toEntity(requestDto);
        Member member = customUserDetails.getMember();
        Long storeId = requestDto.getStoreId();
        Store store = storeService.getStore(storeId);
        review.updateMember(member);
        review.updateStore(store);
        return save(review).getId();
    }

    public ReviewResponseDto getReviewDetail(Long id) {
        Review review = getReviewById(id);
        return ReviewResponseDto.toDto(review);
    }

    public List<ReviewPreviewResponseDto> getMyReviewPreview(CustomUserDetails customUserDetails) {
        Member member = customUserDetails.getMember();
        return getReviewListByMemberId(member.getId()).stream()
            .map(ReviewPreviewResponseDto::from)
            .collect(Collectors.toList());
    }

    private Review save(Review review) {
        return reviewRepository.save(review);
    }

    private Review getReviewById(Long id) {
        return reviewRepository.findById(id)
            .orElseThrow(() -> new GeneralException(Status.REVIEW_NOT_FOUND));
    }

    private List<Review> getReviewListByMemberId(String memberId) {
        return reviewRepository.findAllByMemberId(memberId);
    }

}
