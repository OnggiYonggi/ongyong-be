package com.onggiyonggi.domain.store.facade;

import com.onggiyonggi.domain.review.dto.response.ReviewResponseDto;
import com.onggiyonggi.domain.review.service.ReviewService;
import com.onggiyonggi.domain.store.domain.Store;
import com.onggiyonggi.domain.store.service.StoreService;
import com.onggiyonggi.global.response.CursorPageResponse;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
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

}
