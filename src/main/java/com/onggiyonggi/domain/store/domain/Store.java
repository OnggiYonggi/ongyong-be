package com.onggiyonggi.domain.store.domain;

import com.onggiyonggi.domain.store.dto.request.StoreRequestDto;
import com.onggiyonggi.global.domain.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Store extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StoreType storeType;

    @Range(min = -90, max = 90)
    @Column(nullable = false)
    private Double latitude; // 위도

    @Range(min = -180, max = 180)
    @Column(nullable = false)
    private Double longitude; // 경도

    @NotNull
    private String address;

    @Column(nullable = false)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StoreRank storeRank;

    private String businessHours;

    public static Store toEntity(StoreRequestDto requestDto) {
        return Store.builder()
            .storeType(requestDto.getStoreType())
            .latitude(requestDto.getLatitude())
            .longitude(requestDto.getLongitude())
            .address(requestDto.getAddress())
            .name(requestDto.getName())
            .businessHours(requestDto.getBusinessHours())
            .build();
    }

    public void updateStoreRank(StoreRank storeRank) {
        this.storeRank = storeRank;
    }

}
