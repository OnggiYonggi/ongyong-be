package com.onggiyonggi.domain.pet.controller;

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
@RequestMapping("/pet")
@RequiredArgsConstructor
@Tag(name = "Pet", description = "펫 api")
public class PetController {

    private final PetService petService;

    @PostMapping("/")
    @Operation(summary = "새로운 펫 생성 API", description = "새로운 펫을 랜덤으로 뽑을 수 있는 API입니다.<br>"
        + "이미 컬렉션에 존재하는 캐릭터는 뽑히지 않습니다.")
    public ApiResponse<?> createPet(@AuthenticationPrincipal CustomUserDetails customUserDetails) {
        PetResponseDto responseDto = petService.createPet(customUserDetails);
        return ApiResponse.success(Status.OK.getCode(),
            Status.OK.getMessage(), responseDto);
    }

    @GetMapping("/")
    @Operation(summary = "내 펫 조회하기 API", description = "현재 내가 기르고 있는 펫 정보를 불러옵니다.")
    public ApiResponse<?> getMyPet(@AuthenticationPrincipal CustomUserDetails customUserDetails) {
        PetResponseDto responseDto = petService.getMyPet(customUserDetails);
        return ApiResponse.success(Status.OK.getCode(),
            Status.OK.getMessage(), responseDto);
    }

}
