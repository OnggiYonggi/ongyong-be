package com.onggiyonggi.domain.item.dto.response;

import com.onggiyonggi.domain.item.domain.Item;
import com.onggiyonggi.domain.item.dto.request.ItemRequestDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ItemResponseDto {

    private String name;

    private Long price;

    private Long count;

    public static ItemResponseDto toDto(Item item) {
        return ItemResponseDto.builder()
            .name(item.getName())
            .price(item.getPrice())
            .count(item.getCount())
            .build();
    }

}