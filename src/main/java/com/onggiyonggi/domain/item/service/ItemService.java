package com.onggiyonggi.domain.item.service;

import com.onggiyonggi.domain.item.domain.Item;
import com.onggiyonggi.domain.item.dto.response.ItemResponseDto;
import com.onggiyonggi.domain.item.repository.ItemRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class ItemService {

    private final ItemRepository itemRepository;
    public List<ItemResponseDto> getItemsByReviewId(Long reviewId) {
        return findItemsByReviewId(reviewId).stream()
            .map(ItemResponseDto::toDto)
            .toList();
    }

    private List<Item> findItemsByReviewId(Long reviewId) {
        return itemRepository.findAllByReviewId(reviewId);
    }

    public Item saveItem(Item item) {
        return saveItemEntity(item);
    }

    private Item saveItemEntity(Item item) {
        return itemRepository.save(item);
    }

}
