package com.onggiyonggi.domain.item.service;

import com.onggiyonggi.domain.item.domain.Item;
import com.onggiyonggi.domain.item.dto.request.ItemRequestDto;
import com.onggiyonggi.domain.item.dto.response.ItemResponseDto;
import com.onggiyonggi.domain.item.repository.ItemRepository;
import com.onggiyonggi.domain.review.domain.Review;
import com.onggiyonggi.domain.review.service.ReviewService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class ItemService {

    private final ItemRepository itemRepository;
    private final ReviewService reviewService;

    public void saveItems(List<ItemRequestDto> itemRequestDtoList) {
        for (ItemRequestDto requestDto : itemRequestDtoList) {
            saveItem(requestDto);
        }
    }

    public Item saveItem(ItemRequestDto requestDto) {
        return saveItemEntity(requestDto);
    }

    private Item saveItemEntity(ItemRequestDto requestDto) {
        Long reviewId = requestDto.getReviewId();
        Review review = reviewService.getReviewEntityById(reviewId);
        Item item = Item.toEntity(requestDto, review);
        return itemRepository.save(item);
    }

}
