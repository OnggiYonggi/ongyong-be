package com.onggiyonggi.domain.uploadedFile.service;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.onggiyonggi.domain.uploadedFile.domain.UploadedFile;
import com.onggiyonggi.domain.uploadedFile.dto.FileResponseDto;
import com.onggiyonggi.domain.uploadedFile.repository.FileRepository;
import com.onggiyonggi.global.config.NCPConfig;
import com.onggiyonggi.global.response.GeneralException;
import com.onggiyonggi.global.response.Status;
import com.onggiyonggi.global.util.FileUtil;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class FileService {

    private final FileRepository fileRepository;
    private final AmazonS3Client objectStorageClient;
    @Value("${ncp.storage.bucket-name}")
    private String bucketName;

    public FileResponseDto saveFile(MultipartFile file) throws IOException {
        String originalFileName = file.getOriginalFilename();
        String randomFileName = FileUtil.generateRandomFileName(originalFileName);
        ObjectMetadata objectMetadata = FileUtil.extractObjectMetadata(file);
        String url = putFileToBucket(file, randomFileName, objectMetadata);
        UploadedFile newFile = UploadedFile.toEntity(url);
        save(newFile);
        return FileResponseDto.toDto(newFile);
    }

    private String putFileToBucket(MultipartFile file, String fileName, ObjectMetadata objectMetadata) {
        try {
            PutObjectRequest request = new PutObjectRequest(bucketName, fileName, file.getInputStream(),
                objectMetadata)
                .withCannedAcl(CannedAccessControlList.PublicRead);
            objectStorageClient.putObject(request);
        } catch (IOException e) {
            throw new GeneralException(Status.FAILED_TO_UPLOAD_FILE);
        }
        return objectStorageClient.getUrl(bucketName, fileName).toString();
    }

    private UploadedFile save(UploadedFile newFile) {
        return fileRepository.save(newFile);
    }

}
