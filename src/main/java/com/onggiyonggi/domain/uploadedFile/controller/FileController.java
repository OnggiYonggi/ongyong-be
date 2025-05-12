package com.onggiyonggi.domain.uploadedFile.controller;

import com.onggiyonggi.domain.store.domain.StoreRank;
import com.onggiyonggi.domain.store.dto.request.StoreRequestDto;
import com.onggiyonggi.domain.uploadedFile.dto.FileResponseDto;
import com.onggiyonggi.domain.uploadedFile.service.FileService;
import com.onggiyonggi.global.response.ApiResponse;
import com.onggiyonggi.global.response.Status;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/file")
@RequiredArgsConstructor
@Tag(name = "File", description = "파일 api")
public class FileController {

    private final FileService fileService;

    @PostMapping(value = "/", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "파일 업로드 API", description = "이미지를 전달 받아 저장하는 API입니다.")
    public ApiResponse<?> saveFile(@RequestPart("file") MultipartFile file) {
        FileResponseDto responseDto = fileService.saveFile(file);
        return ApiResponse.success(Status.OK.getCode(),
            Status.OK.getMessage(), responseDto);
    }

}
