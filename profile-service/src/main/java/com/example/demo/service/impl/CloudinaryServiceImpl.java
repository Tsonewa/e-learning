package com.example.demo.service.impl;

import com.cloudinary.Cloudinary;
import com.example.demo.service.CloudinaryService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.Map;

@Service
public class CloudinaryServiceImpl implements CloudinaryService {
    private static final String TEMP_FILE = "temp-file";
    private static final String URL = "url";

    private final Cloudinary cloudinary;

    public CloudinaryServiceImpl(Cloudinary cloudinary) {
        this.cloudinary = cloudinary;
    }

    @Override
    public String uploadImage(MultipartFile multipartFile) throws IOException {
        File file = File.createTempFile(TEMP_FILE, multipartFile.getOriginalFilename());
        multipartFile.transferTo(file);
        return this.cloudinary
                .uploader()
                .upload(file, Collections.emptyMap())
                .get(URL)
                .toString();
    }

    @Override
    public Map deleteImage(String id, Map<Object, Object> options) throws IOException {
        return this.cloudinary.uploader().destroy(id, options);
    }
}
