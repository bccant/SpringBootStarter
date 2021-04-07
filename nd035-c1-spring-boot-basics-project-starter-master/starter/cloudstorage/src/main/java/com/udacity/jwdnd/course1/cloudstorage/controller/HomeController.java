package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.mapper.*;
import com.udacity.jwdnd.course1.cloudstorage.model.*;
import com.udacity.jwdnd.course1.cloudstorage.services.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.security.core.*;
import org.springframework.stereotype.*;
import org.springframework.ui.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.*;

import java.util.*;

@Controller
@RequestMapping()
public class HomeController {
    @Autowired
    private NoteService noteService;
    private UserMapper userMapper;
    private CredentialService credentialService;
    private FileService fileService;

    public HomeController(NoteService noteService, CredentialService credentialService, FileService fileService,
                          UserMapper userMapper) {
        this.noteService = noteService;
        this.credentialService = credentialService;
        this.fileService = fileService;
        this.userMapper = userMapper;
    }

    @GetMapping("/home")
    public String homeView(Authentication authentication, @ModelAttribute("newMessage")Note note,
                           @ModelAttribute("newCred")Credential credential,
                           @ModelAttribute("newFile")Files files,
                           Model model) {
        User user = userMapper.getUser(authentication.getName());
        model.addAttribute("notes", noteService.getAllNotes(user.getUserid()));
        model.addAttribute("credentials", credentialService.getAllCreds(user.getUserid()));
        model.addAttribute("files", fileService.getAllFiles(user.getUserid()));

        return "home";
    }

    @GetMapping("/result")
    public String showResult(
            Authentication authentication,
            @RequestParam(required = false, name = "isSuccess") Boolean isSuccess,
            @RequestParam(required = false, name = "errorType") Integer errorType,
            Model model
    ) {

        Map<String, Object> data = new HashMap<>();

        data.put("isSuccess", isSuccess);
        data.put("errorType", errorType);

        model.addAllAttributes(data);

        return "result";
    }
}
