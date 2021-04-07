package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mapper.*;
import com.udacity.jwdnd.course1.cloudstorage.model.*;
import org.springframework.stereotype.*;

import javax.annotation.*;
import java.util.*;

@Service
public class CredentialService {
    private List<Credential> credentialList;
    private CredentialMapper credentialMapper;

    public CredentialService(CredentialMapper credentialMapper) {
        this.credentialMapper = credentialMapper;
    }

    @PostConstruct
    public void credConstruct() {
        this.credentialList = new ArrayList<>();
    }

    public Credential getCredById(int credId) {
        return credentialMapper.getCredById(credId);
    }

    public List<Credential> getAllCreds(int userId) {
        return credentialMapper.getAllCreds(userId);
    }

    public int addCredential(Credential credential) {
        return credentialMapper.addCredential(credential);
    }

    public int deleteCred(int credId) {
        return credentialMapper.deleteCred(credId);
    }

    public int updateCred(Credential credential) {
        return credentialMapper.updateCred(credential);
    }
}
