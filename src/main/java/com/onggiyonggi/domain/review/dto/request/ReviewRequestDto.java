package com.onggiyonggi.domain.review.dto.request;

import com.onggiyonggi.domain.review.domain.FillLevel;
import com.onggiyonggi.domain.review.domain.FoodTaste;
import com.onggiyonggi.domain.review.domain.ReusableContainerSize;
import com.onggiyonggi.domain.review.domain.ReusableContainerType;
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
public class ReviewRequestDto {

    @NotNull
    @Schema(description = "가게 아이디", example = "1")
    private Long storeId;

    @Schema(description = "이미지 경로. 파일 업로드 API의 결과로 받은 URL을 입력", example = "http://~")
    private String imageURL;

    @Schema(description = "파일 id. 파일 업로드 API의 결과로 받은 id를 입력", example = "1")
    private Long fileId;

    @NotNull
    @NotEmpty
    @NotBlank
    @Schema(description = "내용", example = "엽기떡볶이를 다회용기에 포장해봤는데, 양도 넉넉하고 국물도 안 새서 너무 만족스러웠어요! 매콤한 맛 그대로 담겨서 집에서도 맛있게 잘 먹었어요. 환경도 지키고 맛도 챙기니 일석이조네요 😊 앞으로도 계속 다회용기 이용할게요!")
    private String content;

    @NotNull
    @Schema(description = "다회용기 타입", example = "LOCK_AND_LOCK")
    private ReusableContainerType reusableContainerType;

    @NotNull
    @Schema(description = "다회용기 크기", example = "LOCK_AND_LOCK_MEDIUM")
    private ReusableContainerSize reusableContainerSize;

    @NotNull
    @Schema(description = "채워진 정도", example = "ADEQUATE")
    private FillLevel fillLevel;

    @NotNull
    @Schema(description = "음식 맛", example = "GOOD")
    private FoodTaste foodTaste;

}
