package com.onggiyonggi.domain.receipt.service;

import com.onggiyonggi.domain.receipt.dto.response.ReceiptResponseDto;
import com.onggiyonggi.domain.receipt.repository.ItemRepository;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
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
public class ReceiptService {

    private final RestTemplate restTemplate;
    private final ItemRepository itemRepository;

    public ReceiptResponseDto analysisReceipt(MultipartFile file) {
        // 이 부분에 AI 서버에 API 요청
        String aiServerUrl = "실제 AI 서버 주소";

        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.MULTIPART_FORM_DATA);
            MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
            body.add("file", new MultipartInputStreamFileResource(file.getInputStream(), file.getOriginalFilename()));
            HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(body, headers);

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
