package com.imagehashtag.service.imagestorage;

import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

public interface ImageStorageService
{
    String saveImageToStorage(MultipartFile imageFile) throws IOException;
}
