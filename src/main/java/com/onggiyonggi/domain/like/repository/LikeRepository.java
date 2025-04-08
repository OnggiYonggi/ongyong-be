package com.onggiyonggi.domain.like.repository;

import com.onggiyonggi.domain.like.domain.Like;
import com.onggiyonggi.domain.member.domain.Member;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LikeRepository extends JpaRepository<Like, String> {

    Optional<Like> findByReviewIdAndMemberId(Long reviewId, String memberId);

    Boolean existsByReviewIdAndMemberId(Long reviewId, String memberId);

}
