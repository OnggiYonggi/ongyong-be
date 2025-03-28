package com.onggiyonggi.domain.store.dto.request;

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
public class StorePreviewRequestDto {

    @NotNull
    @Schema(description = "위도", example = "37.0")
    private Double latitude;

    @NotNull
    @Schema(description = "경도", example = "127.0")
    private Double longitude;

    @NotNull
    @Schema(description = "반경", example = "3")
    private Integer radius;

}
