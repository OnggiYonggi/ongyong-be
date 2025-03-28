package com.onggiyonggi.domain.store.dto.response;

import com.onggiyonggi.domain.store.domain.Store;
import com.onggiyonggi.domain.store.domain.StoreRank;
import com.onggiyonggi.domain.store.domain.StoreType;
import com.onggiyonggi.domain.store.dto.request.StorePreviewRequestDto;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class StorePreviewResponseDto {

    private Long id;
    private StoreRank storeRank;
    private StoreType storeType;
    private Double latitude; // 위도
    private Double longitude; // 경도

    public static StorePreviewResponseDto toDto(Store store) {
        return StorePreviewResponseDto.builder()
            .id(store.getId())
            .storeRank(store.getStoreRank())
            .storeType(store.getStoreType())
            .latitude(store.getLatitude())
            .longitude(store.getLongitude())
            .build();
    }

}
