package com.udacity.jwdnd.course1.cloudstorage.controller;

import org.springframework.security.core.*;
import org.springframework.security.core.context.*;
import org.springframework.security.web.authentication.logout.*;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.*;

@Controller
@RequestMapping("/login")
public class LoginController {

    @GetMapping("")
    public String loginView() {
        return "login";
    }

}
