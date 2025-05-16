package com.onggiyonggi.domain.review.facade;

import com.onggiyonggi.domain.item.domain.Item;
import com.onggiyonggi.domain.item.dto.request.ItemRequestDto;
import com.onggiyonggi.domain.item.dto.response.ItemResponseDto;
import com.onggiyonggi.domain.item.service.ItemService;
import com.onggiyonggi.domain.review.domain.Review;
import com.onggiyonggi.domain.review.service.ReviewService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReviewItemFacade {

    private final ReviewService reviewService;
    private final ItemService itemService;

    public void saveItems(List<ItemRequestDto> itemRequestDtoList) {
        for (ItemRequestDto requestDto : itemRequestDtoList) {
            saveItem(requestDto);
        }
    }

    public Item saveItem(ItemRequestDto requestDto) {
        Long reviewId = requestDto.getReviewId();
        Review review = reviewService.getReviewEntityById(reviewId);
        Item item = Item.toEntity(requestDto, review);
        return itemService.saveItem(item);
    }

}
