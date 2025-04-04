package com.onggiyonggi.domain.review.service;

import com.onggiyonggi.domain.member.domain.Member;
import com.onggiyonggi.domain.review.domain.Review;
import com.onggiyonggi.domain.review.dto.request.ReviewRequestDto;
import com.onggiyonggi.domain.review.repository.ReviewRepository;
import com.onggiyonggi.domain.store.domain.Store;
import com.onggiyonggi.domain.store.service.StoreService;
import com.onggiyonggi.global.auth.CustomUserDetails;
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

    private Review save(Review review) {
        return reviewRepository.save(review);
    }

}
