package com.easyaccess.app.files.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

import com.easyaccess.app.common.constants.Messages;

@Service
public class FileStorageService {
  @Value("${files.path}")
  private String storagePath;

  private static final Logger logger = LoggerFactory.getLogger(FileStorageService.class);

  public String saveFile(MultipartFile file, String folderName) {
    try {
      Path folderPath = Path.of(storagePath, folderName);
      Files.createDirectories(folderPath);

      String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
      Path filePath = folderPath.resolve(fileName);

      Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

      return filePath.toString();
    } catch (IOException e) {
      logger.error(e.getMessage());
      throw new RuntimeException(Messages.FAILED_TO_SAVE_IMAGE, e);
    }
  }

  public String updateFile(MultipartFile file, String oldFilePath, String folderName) {
    deleteFileByPath(oldFilePath);
    return saveFile(file, folderName);
  }

  public boolean deleteFileByPath(String filePath) {
    try {
      Path path = Path.of(filePath);
      return Files.deleteIfExists(path);
    } catch (IOException e) {
      logger.error(e.getMessage(), filePath);
      throw new RuntimeException(Messages.FAILED_TO_DELETE_IMAGE, e);
    }
  }

  public boolean deleteFile(String fileName, String folderName) {
    return deleteFileByPath(Path.of(storagePath, folderName, fileName).toString());
  }
}
