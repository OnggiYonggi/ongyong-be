package com.onggiyonggi.global.scheduler;

import com.onggiyonggi.domain.review.repository.ReviewRepository;
import com.onggiyonggi.domain.review.service.ReviewService;
import com.onggiyonggi.domain.store.domain.Store;
import com.onggiyonggi.domain.store.domain.StoreRank;
import com.onggiyonggi.domain.store.repository.StoreRepository;
import com.onggiyonggi.domain.store.service.StoreService;
import java.time.LocalDateTime;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class StoreGradeScheduler {

    private final StoreService storeService;
    private final ReviewService reviewService;


    @Scheduled(cron = "0 0 0 * * *") // 매일 자정에 실행
    public void upgradeStoreGrades() {
        LocalDateTime oneWeekAgo = LocalDateTime.now().minusWeeks(1);

        List<Store> rookieStores = storeService.getStoreListByGradeAndCreatedAtBefore(StoreRank.ROOKIE, oneWeekAgo);

        for (Store store : rookieStores) {
            int reviewCount = reviewService.getReviewCountByStoreId(store.getId());

            if (reviewCount <= 30) {
                store.updateStoreRank(StoreRank.BRONZE);
            } else if (reviewCount <= 99) {
                store.updateStoreRank(StoreRank.SILVER);
            } else {
                store.updateStoreRank(StoreRank.GOLD);
            }
        }

        storeService.saveAll(rookieStores);
    }
}

