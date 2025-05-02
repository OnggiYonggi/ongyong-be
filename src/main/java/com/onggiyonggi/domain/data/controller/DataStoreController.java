package com.onggiyonggi.domain.data.controller;


import com.onggiyonggi.domain.character.dto.request.NaturalMonumentCharacterRequestDto;
import com.onggiyonggi.domain.character.service.CharacterService;
import com.onggiyonggi.domain.data.service.DataService;
import com.onggiyonggi.domain.store.dto.request.StoreRequestDto;
import com.onggiyonggi.global.response.ApiResponse;
import com.onggiyonggi.global.response.Status;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/data")
@Tag(name = "Data", description = "적재 api")
public class DataStoreController {

    private final DataService dataService;
    private final CharacterService characterService;

    @PostMapping("/stores")
    @Operation(summary = "가게 저장 API", description = "가게를 저장하는 API 입니다.")
    public ApiResponse<?> saveStores(@RequestBody @Valid List<StoreRequestDto> storeList) {
        dataService.saveStores(storeList);
        return ApiResponse.success(Status.OK.getCode(), Status.OK.getMessage(), "가게 저장 완료");
    }

    @PostMapping("/characters")
    @Operation(summary = "캐릭터 저장 API", description = "캐릭터를 저장할 수 있는 API 입니다.")
    public ApiResponse<?> saveCharacters(@RequestBody @Valid List<NaturalMonumentCharacterRequestDto> characterList) {
        dataService.saveCharacters(characterList);
        return ApiResponse.success(Status.OK.getCode(), Status.OK.getMessage(), "캐릭터 저장 완료");
    }


}
