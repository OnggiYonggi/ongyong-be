package com.onggiyonggi.domain.review.facade;

import com.onggiyonggi.domain.like.domain.Like;
import com.onggiyonggi.domain.like.dto.response.LikeToggleResponseDto;
import com.onggiyonggi.domain.like.service.LikeService;
import com.onggiyonggi.domain.member.domain.Member;
import com.onggiyonggi.domain.review.domain.Review;
import com.onggiyonggi.domain.review.service.ReviewService;
import com.onggiyonggi.global.auth.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReviewLikeFacade {

    private final ReviewService reviewService;
    private final LikeService likeService;

    public LikeToggleResponseDto toggleLikeStatus(Long reviewId, CustomUserDetails customUserDetails) {
        Member member = customUserDetails.getMember();
        Review review = reviewService.getReviewEntityById(reviewId);
        boolean alreadyLiked = likeService.isExistByReviewIdAndMemberId(reviewId, member.getId());

        if (alreadyLiked) {
            Like like = likeService.getByReviewIdAndMemberId(reviewId, member.getId());
            likeService.deleteLikeEntity(like);
            review.decreaseLike();
            reviewService.saveReviewEntity(review);
            return LikeToggleResponseDto.builder()
                .likes(review.getLikes())
                .reviewId(reviewId)
                .build();
        } else {
            Like like = Like.createEntity(review, member);
            likeService.saveLikeEntity(like);
            review.increaseLike();
            reviewService.saveReviewEntity(review);
            return LikeToggleResponseDto.builder()
                .likes(review.getLikes())
                .reviewId(reviewId)
                .build();
        }
    }

}
