package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.mapper.*;
import com.udacity.jwdnd.course1.cloudstorage.model.*;
import com.udacity.jwdnd.course1.cloudstorage.services.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.*;
import org.springframework.stereotype.*;
import org.springframework.ui.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.*;

import java.io.IOException;
import java.net.URLConnection;
import java.util.*;

@Controller
@RequestMapping()
public class FileController {
    private FileService fileService;
    private UserMapper userMapper;

    public FileController(FileService fileService, UserMapper userMapper) {
        this.fileService = fileService;
        this.userMapper = userMapper;
    }

    @PostMapping("/files")
    public String addFiles(Authentication authentication, @RequestParam("fileUpload") MultipartFile file, Model model) {
        if (file.getOriginalFilename().isEmpty()) {
            model.addAttribute("error", "Upload failed, as the file is empty.");
            return "result";
        }

        String fileName = file.getOriginalFilename();

        User user = userMapper.getUser(authentication.getName());

        if (fileService.getFileByName(user.getUserid(), fileName) != null) {
            model.addAttribute("error", "Upload failed, as the file with the same name exists.");
            return "result";
        }
        Files uploadedFile = new Files();

        try {
            uploadedFile.setFiledata(file.getBytes());
            uploadedFile.setContenttype(file.getContentType());
            uploadedFile.setFilename(file.getOriginalFilename());
            uploadedFile.setFilesize(file.getSize() + "");

            uploadedFile.setUserid(user.getUserid());
            fileService.addFile(uploadedFile);
        } catch (IOException e) {
            e.printStackTrace();
            return "redirect:/result?error";
        }

        List<Files> files = fileService.getAllFiles(user.getUserid());

        model.addAttribute("files", fileService.getAllFiles(user.getUserid()));

        return "redirect:/result?success";

    }

    @GetMapping("/files/delete")
    public String deleteFile(@RequestParam("id") String fileName) {
        fileService.deleteFile(fileName);
        return "redirect:/result?success";
    }

    @GetMapping(
            value = "/files/download",
            produces = MediaType.APPLICATION_OCTET_STREAM_VALUE
    )
    public ResponseEntity<byte[]> getFile(Authentication authentication, Model model, @RequestParam("id") String fileName) {
        User user = userMapper.getUser(authentication.getName());
        Files dwldFile = fileService.getFileByName(user.getUserid(), fileName);

        byte[] output = dwldFile.getFiledata();

        HttpHeaders responseHeaders = new HttpHeaders();
        //get the mimetype
        String mimeType = URLConnection.guessContentTypeFromName(dwldFile.getFilename());
        if (mimeType == null) {
            //unknown mimetype so set the mimetype to application/octet-stream
            mimeType = "application/octet-stream";
        }

        responseHeaders.setContentType(MediaType.valueOf(mimeType));
        responseHeaders.setContentLength(output.length);

        return new ResponseEntity<byte[]>(output, responseHeaders, HttpStatus.OK);
    }

    @GetMapping("/files")
    public String getFiles(Authentication authentication, Model model) {
        User user = userMapper.getUser(authentication.getName());
        model.addAttribute("files", fileService.getAllFiles(user.getUserid()));
        return "home";
    }
}
