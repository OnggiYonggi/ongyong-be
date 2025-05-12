package com.onggiyonggi.domain.uploadedFile.service;

import com.onggiyonggi.domain.uploadedFile.dto.FileResponseDto;
import com.onggiyonggi.domain.uploadedFile.repository.FileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class FileService {

    private final FileRepository fileRepository;

    public FileResponseDto saveFile(MultipartFile file) {
        String url = null;
        return FileResponseDto.toDto(url);
    }

}
