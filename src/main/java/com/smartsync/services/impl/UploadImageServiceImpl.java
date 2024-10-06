package com.smartsync.services.impl;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.cloudinary.Cloudinary;
import com.cloudinary.Transformation;
import com.cloudinary.Url;
import com.smartsync.services.UploadImageService;
import com.smartsync.utility.AppConstants;

@Service
public class UploadImageServiceImpl implements UploadImageService {

    private Cloudinary cloudinary;

    public UploadImageServiceImpl(Cloudinary cloudinary) {
        this.cloudinary = cloudinary;
    }

    @Override
    public String uploadImage(MultipartFile file, String publicId) {
        String imageUrl = null;
        try {
            Map<String, String> map = new HashMap<>();
            byte[] data=new byte[file.getInputStream().available()];
            file.getInputStream().read(data);
            map.put("public_id", publicId);

            cloudinary.uploader().upload(data, map);
            imageUrl = this.getUploadedImage(publicId);
            return imageUrl;
        } catch (IOException e) {
            return imageUrl;
        }
    }

    @Override
    public String getUploadedImage(String publicId) {
        return cloudinary.url().transformation(new Transformation<>().width(AppConstants.IMAGE_WIDTH)
                                                                    .height(AppConstants.IMAGE_HEIGHT)
                                                                    .crop(AppConstants.CROP)
                                                )
                                .generate(publicId);
    }

}
