package com.apiamiciback.service;


import com.apiamiciback.model.FileData;
import com.apiamiciback.repository.FileDataRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Optional;

/**
 * The type File data service.
 */
@Service
@Slf4j
@Transactional
public class FileDataService {

    private final String FOLDER_PATH ="C:\\Users\\djkar\\OneDrive\\Bureau\\BAC INFO\\3 ème\\TFE 2022-2023\\projet-amici-calcio\\amici-calcio-front\\public";

    @Autowired
    private FileDataRepository fileDataRepository;

    /**
     * Upload image to file system string.
     *
     * @param file the file
     * @return the string
     * @throws IOException the io exception
     */
    public String uploadImageToFileSystem (MultipartFile file) throws IOException{
        String filePath = FOLDER_PATH + file.getOriginalFilename();
        FileData fileData = fileDataRepository.save(FileData.builder()
                        .name(file.getName())
                        .type(file.getContentType())
                        .filePath(filePath)
                .build());
        // transfert l'image vers le serveur
        file.transferTo(new File(filePath));

        if(fileData != null) {
            return "File uploaded successfully " + file.getOriginalFilename();
        }
        return null;
    }

    /**
     * Download image from file system byte [ ].
     *
     * @param filename the filename
     * @return the byte [ ]
     * @throws IOException the io exception
     */
    public byte[] downloadImageFromFileSystem (String filename) throws IOException {
        Optional<FileData>fileData = fileDataRepository.findByName(filename);
        String filePath = fileData.get().getFilePath();
        byte[] image = Files.readAllBytes(new File(filePath).toPath());
        return image;
    }
}
