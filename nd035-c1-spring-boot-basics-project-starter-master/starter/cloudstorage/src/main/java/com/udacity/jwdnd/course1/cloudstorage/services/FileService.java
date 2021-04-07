package com.udacity.jwdnd.course1.cloudstorage.services;


import com.udacity.jwdnd.course1.cloudstorage.mapper.*;
import com.udacity.jwdnd.course1.cloudstorage.model.*;
import org.springframework.stereotype.*;

import javax.annotation.*;
import java.util.*;

@Service
public class FileService {
    private List<Files> filesList;
    private FileMapper fileMapper;

    public FileService(FileMapper fileMapper) {
        this.fileMapper = fileMapper;
    }

    @PostConstruct
    public void fileConstruct() {
        this.filesList = new ArrayList<>();
    }

    public Files getFileById(int fileId) {
        return fileMapper.getFileId(fileId);
    }

    public Files getFileByName(int userId, String fileName) {
        return fileMapper.getFileByName(userId, fileName);
    }

    public List<Files> getAllFiles(int userid) {
        return fileMapper.getAllFiles(userid);
    }

    public void addFile(Files file) {
        fileMapper.addFile(file);
    }

    public int deleteFile(String fileName) {
        return fileMapper.deleteFile(fileName);
    }
}
