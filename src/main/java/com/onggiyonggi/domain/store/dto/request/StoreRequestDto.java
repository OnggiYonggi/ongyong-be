package com.onggiyonggi.domain.store.dto.request;

import com.onggiyonggi.domain.store.domain.StoreRank;
import com.onggiyonggi.domain.store.domain.StoreType;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StoreRequestDto {

    @NotNull
    @Schema(description = "가게 타입", example = "FOOD")
    private StoreType storeType;

    @NotNull
    @Schema(description = "위도", example = "37.299742")
    private Double latitude;

    @NotNull
    @Schema(description = "경도", example = "127.044380")
    private Double longitude;

    @NotNull
    @NotEmpty
    @NotBlank
    @Schema(description = "주소", example = "경기도 수원시 영통구 대학1로 38")
    private String address;

    @NotNull
    @NotEmpty
    @NotBlank
    @Schema(description = "이름", example = "엽기떡볶이 광교경기대점")
    private String name;

    @Schema(description = "영업시간", example = "")
    private String businessHours;

}
