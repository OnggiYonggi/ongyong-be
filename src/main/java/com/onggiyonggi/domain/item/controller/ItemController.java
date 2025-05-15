package com.onggiyonggi.domain.item.controller;

import com.onggiyonggi.domain.item.dto.request.ItemRequestDto;
import com.onggiyonggi.domain.item.service.ItemService;
import com.onggiyonggi.domain.receipt.dto.response.ReceiptResponseDto;
import com.onggiyonggi.global.response.ApiResponse;
import com.onggiyonggi.global.response.Status;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.io.IOException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/item")
@RequiredArgsConstructor
@Tag(name = "Item", description = "물품 api")
public class ItemController {

    private final ItemService itemService;

    @PostMapping(value = "/")
    @Operation(summary = "물품 등록 API", description = "리뷰에 적힌 구매 물품들을 저장하는 API입니다.")
    public ApiResponse<?> analysisReceipt(@RequestBody List<ItemRequestDto> requestDtoList) throws IOException {
        itemService.saveItems(requestDtoList);
        return ApiResponse.success(Status.OK.getCode(),
            Status.OK.getMessage(), null);
    }

}
