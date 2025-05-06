package com.onggiyonggi.domain.store.repository;

import com.onggiyonggi.domain.store.domain.Store;
import com.onggiyonggi.domain.store.domain.StoreRank;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface StoreRepository extends JpaRepository<Store, Long> {

    @Query(value = """
    SELECT *, (
        6371 * acos(
            cos(radians(:lat)) *
            cos(radians(s.latitude)) *
            cos(radians(s.longitude) - radians(:lng)) +
            sin(radians(:lat)) *
            sin(radians(s.latitude))
        )
    ) AS distance
    FROM store s
    HAVING distance <= :radius
    ORDER BY distance
""", nativeQuery = true)
    List<Store> findStoresWithinRadius(
        @Param("lat") double latitude,
        @Param("lng") double longitude,
        @Param("radius") double radius
    );

    @Query("""
    SELECT s FROM Store s
    WHERE s.storeRank = :rank
    AND s.createdAt < :createdAt
""")
    List<Store> findByRankAndCreatedAtBefore(
        @Param("rank") StoreRank rank,
        @Param("createdAt") LocalDateTime createdAt
    );

}