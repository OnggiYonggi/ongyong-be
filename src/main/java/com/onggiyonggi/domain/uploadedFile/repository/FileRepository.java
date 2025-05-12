package com.onggiyonggi.domain.uploadedFile.repository;

import com.onggiyonggi.domain.uploadedFile.domain.UploadedFile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FileRepository extends JpaRepository<UploadedFile, Long> {

}
