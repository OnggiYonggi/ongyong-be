package com.onggiyonggi.domain.receipt.service;

import com.onggiyonggi.domain.receipt.dto.response.ReceiptResponseDto;
import com.onggiyonggi.domain.item.repository.ItemRepository;
import com.onggiyonggi.domain.uploadedFile.dto.FileResponseDto;
import com.onggiyonggi.domain.uploadedFile.service.FileService;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
@Slf4j
public class ReceiptService {

    private final RestTemplate restTemplate;
    private final FileService fileService;
    @Value("${ai.url}")
    private String aiServerUrl;

    public ReceiptResponseDto analysisReceipt(MultipartFile file) {
        try {
            FileResponseDto fileResponseDto = fileService.saveFile(file);
            String imageURL = fileResponseDto.getUrl();
            log.info(imageURL);

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            Map<String, String> requestBody = new HashMap<>();
            requestBody.put("url", imageURL);

            HttpEntity<Map<String, String>> requestEntity = new HttpEntity<>(requestBody, headers);

            ResponseEntity<ReceiptResponseDto> response = restTemplate.exchange(
                aiServerUrl,
                HttpMethod.POST,
                requestEntity,
                ReceiptResponseDto.class
            );

            return response.getBody();

        } catch (IOException e) {
            throw new RuntimeException("파일 처리 중 오류 발생", e);
        }
    }

}
