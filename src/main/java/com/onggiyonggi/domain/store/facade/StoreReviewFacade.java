package com.onggiyonggi.domain.store.facade;

import com.onggiyonggi.domain.review.dto.response.ReviewResponseDto;
import com.onggiyonggi.domain.review.service.ReviewService;
import com.onggiyonggi.domain.store.domain.Store;
import com.onggiyonggi.domain.store.domain.StoreRank;
import com.onggiyonggi.domain.store.service.StoreService;
import com.onggiyonggi.global.response.CursorPageResponse;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StoreReviewFacade {

    private final ReviewService reviewService;
    private final StoreService storeService;

    public CursorPageResponse<ReviewResponseDto> getPagedReview(Long storeId, LocalDateTime cursor, int size) {
        Store store = storeService.getStore(storeId);
        return reviewService.getPagedReviewsByStoreId(store.getId(), cursor, size);
    }

    public void updateStoreRankByCondition(Long storeId) {
        Store store = storeService.getStore(storeId);
        int reviewNumber = reviewService.getReviewCountByStoreId(storeId);

        if (store.getStoreRank() == StoreRank.BAN) {
            return;
        }
        if (store.getStoreRank() == StoreRank.ROOKIE &&
            store.getCreatedAt().isAfter(LocalDateTime.now().minusWeeks(1))) {
            return;
        }

        StoreRank newRank = determineRankByReviewCount(reviewNumber);

        if (store.getStoreRank() != newRank) {
            store.updateStoreRank(newRank);
            storeService.saveStore(store);
        }
    }

    private StoreRank determineRankByReviewCount(int reviewCount) {
        if (reviewCount <= StoreRank.BRONZE.getBoundaryValue()) {
            return StoreRank.BRONZE;
        } else if (reviewCount <= StoreRank.SILVER.getBoundaryValue()) {
            return StoreRank.SILVER;
        } else {
            return StoreRank.GOLD;
        }
    }

}
