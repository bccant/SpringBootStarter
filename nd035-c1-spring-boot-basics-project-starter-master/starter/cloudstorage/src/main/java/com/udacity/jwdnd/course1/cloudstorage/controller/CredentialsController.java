package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.mapper.*;
import com.udacity.jwdnd.course1.cloudstorage.model.*;
import com.udacity.jwdnd.course1.cloudstorage.services.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.security.core.*;
import org.springframework.stereotype.*;
import org.springframework.ui.*;
import org.springframework.web.bind.annotation.*;

import java.security.*;
import java.util.*;

@Controller
@RequestMapping()
public class CredentialsController {
    @Autowired
    private CredentialService credentialService;
    private UserMapper userMapper;
    public HashService hashService;
    SecureRandom random = new SecureRandom();

    public CredentialsController(CredentialService credentialService, HashService hashService, UserMapper userMapper) {
        this.credentialService = credentialService;
        this.hashService = hashService;
        this.userMapper = userMapper;
    }

    @PostMapping("/credentials")
    public String addOrUpdateCredentials(Authentication authentication, @ModelAttribute("credentials")Credential newCred, Model model) {
        User user = getUser(authentication, newCred);

        if (newCred.getCredentialid() == null) {
            credentialService.addCredential(newCred);
        } else {
            credentialService.updateCred(newCred);
        }

        List<Credential> credentials = credentialService.getAllCreds(user.getUserid());

        model.addAttribute("credentials", credentials);

        return "redirect:/result?success";

    }

    private User getUser(Authentication authentication, @ModelAttribute("credentials") Credential newCred) {
        User user = userMapper.getUser(authentication.getName());

        //SecureRandom random = new SecureRandom();
        byte[] salt = new byte[16];
        random.nextBytes(salt);
        String encodedSalt = Base64.getEncoder().encodeToString(salt);
        String hashedPassword = hashService.getHashedValue(newCred.getPassword(), encodedSalt);

        newCred.setKey(hashedPassword);
        //newCred.setKey(encodedSalt);
        //newCred.setPassword(hashedPassword);
        newCred.setUserid(user.getUserid());
        return user;
    }

    @GetMapping("/credentials/delete")
    public String deleteCred(@RequestParam("id") int credid) {
        credentialService.deleteCred(credid);
        return "redirect:/result?success";
    }

    @GetMapping("/credentials")
    public String getCred(Authentication authentication, Model model) {
        User user = userMapper.getUser(authentication.getName());
        model.addAttribute("credentials", credentialService.getAllCreds(user.getUserid()));
        return "home";
    }
}
