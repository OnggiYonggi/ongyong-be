package com.onggiyonggi.domain.character.controller;

import com.onggiyonggi.domain.character.dto.response.NaturalMonumentCharacterResponseDto;
import com.onggiyonggi.domain.character.service.CharacterService;
import com.onggiyonggi.domain.collection.dto.responseDto.CollectionResponseDto;
import com.onggiyonggi.global.auth.CustomUserDetails;
import com.onggiyonggi.global.response.ApiResponse;
import com.onggiyonggi.global.response.Status;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/character")
@RequiredArgsConstructor
@Tag(name = "Character", description = "캐릭터 api")
public class CharacterController {

    private final CharacterService characterService;

    @GetMapping("/")
    @Operation(summary = "모든 캐릭터 조회하기 API", description = "모든 캐릭터의 정보를 불러옵니다.")
    public ApiResponse<?> getMyPet() {
        List<NaturalMonumentCharacterResponseDto> responseDto = characterService.getAllCharacters();
        return ApiResponse.success(Status.OK.getCode(),
            Status.OK.getMessage(), responseDto);
    }

}
