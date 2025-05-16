package com.onggiyonggi.domain.receipt.controller;

import com.onggiyonggi.domain.pet.dto.response.PetResponseDto;
import com.onggiyonggi.domain.receipt.dto.response.ReceiptResponseDto;
import com.onggiyonggi.domain.receipt.service.ReceiptService;
import com.onggiyonggi.global.auth.CustomUserDetails;
import com.onggiyonggi.global.response.ApiResponse;
import com.onggiyonggi.global.response.Status;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/receipt")
@RequiredArgsConstructor
@Tag(name = "Receipt", description = "영수증 api")
public class ReceiptController {

    private final ReceiptService receiptService;

    @PostMapping(value = "/", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "영수증 분석 API", description = "영수증 이미지를 전달받아 영수증 분석을 합니다.")
    public ApiResponse<?> analysisReceipt(@RequestPart("file") MultipartFile file) throws IOException {
        ReceiptResponseDto responseDto = receiptService.analysisReceipt(file);
        return ApiResponse.success(Status.OK.getCode(),
            Status.OK.getMessage(), responseDto);
    }

}
