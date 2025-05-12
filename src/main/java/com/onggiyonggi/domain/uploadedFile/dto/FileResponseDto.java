package com.onggiyonggi.domain.uploadedFile.dto;

import com.onggiyonggi.domain.uploadedFile.domain.UploadedFile;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class FileResponseDto {

    Long id;
    String url;

    public static FileResponseDto toDto(UploadedFile file) {
        return FileResponseDto.builder()
            .id(file.getId())
            .url(file.getUrl())
            .build();
    }

}
