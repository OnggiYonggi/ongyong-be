package com.onggiyonggi.domain.review.repository;

import com.onggiyonggi.domain.review.domain.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, Long> {

}
