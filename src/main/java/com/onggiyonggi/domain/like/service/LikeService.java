package com.onggiyonggi.domain.like.service;

import com.onggiyonggi.domain.like.domain.Like;
import com.onggiyonggi.domain.like.dto.response.LikeToggleResponseDto;
import com.onggiyonggi.domain.like.repository.LikeRepository;
import com.onggiyonggi.domain.member.domain.Member;
import com.onggiyonggi.domain.review.domain.Review;
import com.onggiyonggi.domain.review.service.ReviewService;
import com.onggiyonggi.global.auth.CustomUserDetails;
import com.onggiyonggi.global.response.GeneralException;
import com.onggiyonggi.global.response.Status;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LikeService {

    private final LikeRepository likeRepository;

    public Like getByReviewIdAndMemberId(Long reviewId, String memberId) {
        return likeRepository.findByReviewIdAndMemberId(reviewId, memberId)
            .orElseThrow(() -> new GeneralException(Status.LIKE_NOT_FOUND));
    }

    public Boolean isExistByReviewIdAndMemberId(Long reviewId, String memberId) {
        return likeRepository.existsByReviewIdAndMemberId(reviewId, memberId);
    }

    public Like saveLikeEntity(Like like) {
        return saveLike(like);
    }

    public void deleteLikeEntity(Like like) {
        likeRepository.delete(like);
    }

    private Like saveLike(Like like) {
        return likeRepository.save(like);
    }

}
