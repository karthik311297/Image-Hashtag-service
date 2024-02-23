package com.imagehashtag.service.imagestorage;


import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@ConditionalOnProperty(name = "image.storage.use.cloud", havingValue = "true")
public class CloudImageStorageService implements ImageStorageService
{
    @Override
    public String saveImageToStorage(MultipartFile imageFile)
    {
        return null;
    }
}
