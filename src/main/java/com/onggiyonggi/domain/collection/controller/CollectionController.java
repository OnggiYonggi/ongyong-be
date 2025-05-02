package com.onggiyonggi.domain.collection.controller;

import com.onggiyonggi.domain.collection.service.CollectionService;
import com.onggiyonggi.domain.pet.dto.response.PetResponseDto;
import com.onggiyonggi.domain.pet.service.PetService;
import com.onggiyonggi.global.auth.CustomUserDetails;
import com.onggiyonggi.global.response.ApiResponse;
import com.onggiyonggi.global.response.Status;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/collection")
@RequiredArgsConstructor
@Tag(name = "Collection", description = "컬렉션 api")
public class CollectionController {

    private final CollectionService collectionService;

    @PostMapping("/")
    @Operation(summary = "컬렉션에 펫 추가 API", description = "컬렉션에 다 성장한 펫을 저장한 API입니다.<br>")
    public ApiResponse<?> addCollection(
        @AuthenticationPrincipal CustomUserDetails customUserDetails,
        Long characterId) {
        Long id = collectionService.addCollection(customUserDetails, characterId);
        return ApiResponse.success(Status.CREATED.getCode(),
            Status.CREATED.getMessage(), id);
    }

/*
    @GetMapping("/")
    @Operation(summary = "내 컬렉션 조회하기 API", description = "현재 나의 컬렉션 정보를 불러옵니다.")
    public ApiResponse<?> getMyPet(@AuthenticationPrincipal CustomUserDetails customUserDetails) {
        PetResponseDto responseDto = petService.getMyPet(customUserDetails);
        return ApiResponse.success(Status.OK.getCode(),
            Status.OK.getMessage(), responseDto);
    }
*/

}
