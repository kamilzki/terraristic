package com.kamilzki.terraristic.services;

import org.springframework.web.multipart.MultipartFile;

public interface ImageService
{
    void saveImageFile(Long animalId, MultipartFile multipartFile);
}
