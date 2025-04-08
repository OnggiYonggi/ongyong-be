package com.onggiyonggi.domain.like.service;

import com.onggiyonggi.domain.like.domain.Like;
import com.onggiyonggi.domain.like.dto.response.LikeToggleResponseDto;
import com.onggiyonggi.domain.like.repository.LikeRepository;
import com.onggiyonggi.domain.member.domain.Member;
import com.onggiyonggi.domain.review.domain.Review;
import com.onggiyonggi.domain.review.service.ReviewService;
import com.onggiyonggi.global.auth.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LikeService {

    private final LikeRepository likeRepository;
    private final ReviewService reviewService;

    public LikeToggleResponseDto toggleLikeStatus(Long reviewId, CustomUserDetails customUserDetails) {
        Member member = customUserDetails.getMember();
        Review review = reviewService.getReviewEntityById(reviewId);

        return likeRepository.findByReviewIdAndMemberId(reviewId, member.getId())
            .map(like -> {
                likeRepository.delete(like);
                review.decreaseLike();
                reviewService.saveReviewEntity(review);
                return LikeToggleResponseDto.builder()
                    .likes(review.getLikes())
                    .reviewId(reviewId)
                    .build();
            })
            .orElseGet(() -> {
                Like like = Like.createEntity(review, member);
                saveLike(like);
                review.increaseLike();
                reviewService.saveReviewEntity(review);
                return LikeToggleResponseDto.builder()
                    .likes(review.getLikes())
                    .reviewId(reviewId)
                    .build();
            });
    }

    public Like saveLikeEntity(Like like) {
        return saveLike(like);
    }

    private Like saveLike(Like like) {
        return likeRepository.save(like);
    }

}
