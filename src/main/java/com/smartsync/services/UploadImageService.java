package com.smartsync.services;

import org.springframework.web.multipart.MultipartFile;

public interface UploadImageService {

    public String uploadImage(MultipartFile file,String publicId);

    public String getUploadedImage(String publicId);

}
