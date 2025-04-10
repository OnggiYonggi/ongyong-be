package com.onggiyonggi.domain.pet.controller;

import com.onggiyonggi.domain.pet.dto.response.MyPetResponseDto;
import com.onggiyonggi.domain.pet.service.PetService;
import com.onggiyonggi.domain.review.dto.request.ReviewRequestDto;
import com.onggiyonggi.global.auth.CustomUserDetails;
import com.onggiyonggi.global.response.ApiResponse;
import com.onggiyonggi.global.response.Status;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/pet")
@RequiredArgsConstructor
@Tag(name = "Pet", description = "펫 api")
public class PetController {

    private final PetService petService;

    @GetMapping("/")
    @Operation(summary = "내 펫 조회하기 API", description = "현재 내가 기르고 있는 펫 정보를 불러옵니다.")
    public ApiResponse<?> getMyPet(@AuthenticationPrincipal CustomUserDetails customUserDetails) {
        MyPetResponseDto responseDto = petService.getMyPet(customUserDetails);
        return ApiResponse.success(Status.OK.getCode(),
            Status.OK.getMessage(), responseDto);
    }

}
