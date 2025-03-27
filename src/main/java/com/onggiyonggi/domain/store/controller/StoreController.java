package com.onggiyonggi.domain.store.controller;

import com.onggiyonggi.domain.store.dto.request.StoreRequestDto;
import com.onggiyonggi.domain.store.service.StoreService;
import com.onggiyonggi.global.response.ApiResponse;
import com.onggiyonggi.global.response.Status;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/store")
@RequiredArgsConstructor
@Tag(name = "Store", description = "가게 api")
public class StoreController {

    private final StoreService storeService;

    @PostMapping("/")
    @Operation(summary = "가게 등록 API", description = "새로운 가게를 등록할 수 있는 API입니다.\n"
        + "첫 리뷰 등록 시에 가게 등록을 먼저 해주세요.\n"
        + "리턴 값은 가게의 id입니다. \n해당 값을 [리뷰 등록 API]의 파라미터로 활용해주세요.")
    public ApiResponse<?> createStore(StoreRequestDto requestDto) {
        Long id = storeService.createStore(requestDto);
        return ApiResponse.success(Status.OK.getCode(),
            Status.OK.getMessage(), id);
    }

    // 현재 위치로 가게 리스트 조회

    // id 값으로 가게 상세 조회

    // 가게 제보하기


}
