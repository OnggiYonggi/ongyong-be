package com.onggiyonggi.domain.review.repository;

import com.onggiyonggi.domain.review.domain.Review;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ReviewRepository extends JpaRepository<Review, Long> {

    List<Review> findAllByMemberId(String memberId);

    @Query("""
    SELECT r FROM Review r
    WHERE r.store.id = :storeId
    AND (
        r.createdAt < :createdAt
    )
    ORDER BY r.createdAt DESC, r.id DESC
""")
    List<Review> findByStoreIdAndCursor(
        @Param("storeId") Long storeId,
        @Param("createdAt") LocalDateTime createdAt,
        Pageable pageable
    );


    @Query("""
    SELECT r FROM Review r
    WHERE r.store.id = :storeId
    ORDER BY r.createdAt DESC
""")
    List<Review> findByStoreIdOrderByCreatedAtDesc(
        @Param("storeId") Long storeId,
        Pageable pageable
    );

    int countByStoreId(Long storeId);

}
