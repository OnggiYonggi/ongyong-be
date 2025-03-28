package com.onggiyonggi.domain.store.dto.response;

import com.onggiyonggi.domain.store.domain.Store;
import com.onggiyonggi.domain.store.domain.StoreRank;
import com.onggiyonggi.domain.store.domain.StoreType;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class StoreDetailResponseDto {

    private Long id;
    private StoreType storeType;
    private Double latitude; // 위도
    private Double longitude; // 경도
    private String address;
    private String name;
    private StoreRank storeRank;
    private String businessHours;
    // 리뷰 리스트 여기에 포함할 지 고민.

    public static StoreDetailResponseDto toDto(Store store) {
        return StoreDetailResponseDto.builder()
            .id(store.getId())
            .storeType(store.getStoreType())
            .latitude(store.getLatitude())
            .longitude(store.getLongitude())
            .address(store.getAddress())
            .name(store.getName())
            .storeRank(store.getStoreRank())
            .businessHours(store.getBusinessHours())
            .build();
    }

}
