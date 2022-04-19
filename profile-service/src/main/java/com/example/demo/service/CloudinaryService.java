package com.example.demo.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

public interface CloudinaryService {

    String uploadImage(MultipartFile multipartFile) throws IOException;

    Map deleteImage(String id, Map<Object, Object> options) throws IOException;
}
