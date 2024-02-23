package com.imagehashtag.service.imagestorage;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@ConditionalOnProperty(name = "image.storage.use.cloud", havingValue = "false")
public class LocalImageStorageService implements ImageStorageService
{
    @Value("${image.storage.local.directory}")
    private String localStorageFolder;
    
    @Override
    public String saveImageToStorage(MultipartFile imageFile) throws IOException
    {
        Path storageFolder = Paths.get(localStorageFolder)
                .toAbsolutePath().normalize();
        String fileName = imageFile.getOriginalFilename();
        try(InputStream stream = imageFile.getInputStream())
        {
            Files.copy(stream, storageFolder
                    .resolve(fileName), StandardCopyOption.REPLACE_EXISTING);
        }
        return fileName;
    }
}
