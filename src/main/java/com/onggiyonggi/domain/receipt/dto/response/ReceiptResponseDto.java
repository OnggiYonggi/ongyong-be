package com.onggiyonggi.domain.receipt.dto.response;

import java.util.List;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class ReceiptResponseDto {

    private String location;

    private List<ItemResponseDto> items;

    private String date;

}
