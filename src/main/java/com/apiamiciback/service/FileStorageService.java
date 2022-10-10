package com.apiamiciback.service;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.stream.Stream;

@Service
@Slf4j
public class FileStorageService {

    private final Path root = Paths.get("uploads");
    public void init() {
        try {
            Files.createDirectory(root);
        } catch (IOException e) {
            throw new RuntimeException("Could not initialize folder for upload!");
        }
    }
    String fileCode = RandomStringUtils.randomAlphanumeric(8);
    public String save(MultipartFile file, String fileName) {
        try (InputStream inputStream = file.getInputStream()) {
            Path filePath = root.resolve(fileCode + "-" + fileName);
            Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
        } catch (Exception e) {
            throw new RuntimeException("Could not store the file. Error: " + e.getMessage());
        }

        String pathUrl = fileCode + "-" + fileName;
        return pathUrl;
    }

    public Resource load(String filename) {
        try {
            log.info("file " + filename);
            Path file = root.resolve(filename);
            Resource resource = new UrlResource(file.toUri());

            log.info(resource.toString());

            if (resource.exists() || resource.isReadable()) {
                return resource;
            } else {
                throw new RuntimeException("Could not read the file!");
            }
        } catch (MalformedURLException e) {
            throw new RuntimeException("Error: " + e.getMessage());
        }
    }

    public void deleteAll() {
        FileSystemUtils.deleteRecursively(root.toFile());
    }

    public Stream<Path> loadAll() {
        try {
            return Files.walk(this.root, 1).filter(path -> !path.equals(this.root)).map(this.root::relativize);
        } catch (IOException e) {
            throw new RuntimeException("Could not load the files!");
        }
    }

    private Path foundFile;

    public Resource getFileAsResource(String fileCode) throws IOException {
        Path dirPath = Paths.get("uploads");
        log.info("dir path " + dirPath);
        Files.list(dirPath).forEach(file -> {
            if (file.getFileName().toString().startsWith(fileCode)) {
                foundFile = file;
                log.info("file found " + foundFile);
                return;
            }
        });

        if (foundFile != null) {
            log.info("URI " + foundFile.toUri().toString());
            return new UrlResource(foundFile.toUri());
        }

        return null;
    }
}





