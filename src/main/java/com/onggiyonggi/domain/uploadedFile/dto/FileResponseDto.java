package com.onggiyonggi.domain.uploadedFile.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class FileResponseDto {

    Long id;
    String url;

    public static FileResponseDto toDto(String url) {
        return FileResponseDto.builder()
            .url(url)
            .build();
    }

}
