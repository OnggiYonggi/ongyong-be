package com.onggiyonggi.global.util;

import com.amazonaws.services.s3.model.ObjectMetadata;
import java.io.IOException;
import java.util.UUID;
import org.springframework.web.multipart.MultipartFile;

public class FileUtil {

    public static String generateRandomFileName(String originalFileName) {
        String extension = "";
        int dotIndex = originalFileName.lastIndexOf(".");
        if (dotIndex != -1 && dotIndex != originalFileName.length() - 1) {
            extension = originalFileName.substring(dotIndex);
        }
        String uuid = UUID.randomUUID().toString();
        return uuid + extension;
    }

    public static ObjectMetadata extractObjectMetadata(MultipartFile file) throws IOException {
        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentLength(file.getSize());
        metadata.setContentType(file.getContentType());
        return metadata;
    }

}
