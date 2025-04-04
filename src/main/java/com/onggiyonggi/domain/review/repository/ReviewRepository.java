package com.onggiyonggi.domain.review.repository;

import com.onggiyonggi.domain.review.domain.Review;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, Long> {

    List<Review> findAllByMemberId(String memberId);

}
