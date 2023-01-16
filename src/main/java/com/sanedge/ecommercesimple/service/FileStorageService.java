package com.sanedge.ecommercesimple.service;

import java.util.List;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface FileStorageService {

    public String storeFile(MultipartFile file);

    public Resource load(String filename);

    public void deleteByName(String fileName);

    public void deleteByNames(List<String> fileNames);
}
