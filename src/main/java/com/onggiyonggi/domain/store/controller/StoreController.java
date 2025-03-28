package com.onggiyonggi.domain.store.controller;

import com.onggiyonggi.domain.store.domain.StoreRank;
import com.onggiyonggi.domain.store.dto.request.StorePreviewRequestDto;
import com.onggiyonggi.domain.store.dto.request.StoreRequestDto;
import com.onggiyonggi.domain.store.dto.response.StoreDetailResponseDto;
import com.onggiyonggi.domain.store.dto.response.StorePreviewResponseDto;
import com.onggiyonggi.domain.store.service.StoreService;
import com.onggiyonggi.global.response.ApiResponse;
import com.onggiyonggi.global.response.Status;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/store")
@RequiredArgsConstructor
@Tag(name = "Store", description = "가게 api")
public class StoreController {

    private final StoreService storeService;

    @PostMapping("/")
    @Operation(summary = "가게 생성 API", description = "새로운 가게를 생성할 수 있는 API입니다.<br>"
        + "사용되는 경우 1. 첫 리뷰 등록 시에 가게 등록을 먼저 해주세요.<br>"
        + "사용되는 경우 2. 가게 제보 시에 사용해주세요.<br><br>"
        + "1, 2를 구분하는 법은 storeRank 값입니다. 1의 경우는 ROOKIE, 2의 경우는 BAN을 전달해주세요.<br>"
        + "리턴 값은 가게의 id입니다.<br>"
        + "1의 경우는 리턴된 id 값을 [리뷰 등록 API]의 파라미터로 활용해주세요.")
    public ApiResponse<?> createStore(@RequestBody @Valid StoreRequestDto requestDto, StoreRank storeRank) {
        Long id = storeService.createStore(requestDto, storeRank);
        return ApiResponse.success(Status.OK.getCode(),
            Status.OK.getMessage(), id);
    }

    @GetMapping("/{id}")
    @Operation(summary = "가게 상세 조회 API", description = "가게의 id 값으로 상세정보를 조회할 수 있는 API입니다.<br>"
        + "")
    public ApiResponse<StoreDetailResponseDto> getStoreDetail(@PathVariable Long id) {
        StoreDetailResponseDto responseDto = storeService.getStoreDetail(id);
        return ApiResponse.success(Status.OK.getCode(),
            Status.OK.getMessage(), responseDto);
    }

    @GetMapping("/")
    @Operation(summary = "현재 위치 기준으로 nkm 내에 위치한 가게 조회", description = "사용자의 위도,경도와 반경을 입력하면 주위의 가게 정보를 반환하는 API입니다.")
    public ApiResponse<List<StorePreviewResponseDto>> getNearbyStore(
        @RequestParam Double latitude,
        @RequestParam Double longitude,
        @RequestParam Integer radius
    ) {
        List<StorePreviewResponseDto> responseDto = storeService.getNearbyStore(latitude, longitude, radius);
        return ApiResponse.success(Status.OK.getCode(),
            Status.OK.getMessage(), responseDto);
    }


}
