package com.udacity.jwdnd.course1.cloudstorage.mapper;

import com.udacity.jwdnd.course1.cloudstorage.model.*;
import org.apache.ibatis.annotations.*;

import java.util.*;

@Mapper
public interface CredentialMapper {
    @Select("Select * from CREDENTIALS where credentialid = #{credentialid}")
    Credential getCredById(Integer credentialid);

    @Select("Select * from CREDENTIALS where userid = #{userid}")
    List<Credential> getAllCreds(Integer userid);

    @Insert("Insert into CREDENTIALS (url, username, key, password, userid) VALUES(#{url},#{username},#{key},#{password}, #{userid})")
    @Options(useGeneratedKeys = true, keyProperty = "credentialid")
    int addCredential(Credential credential);

    @Delete("Delete from CREDENTIALS where credentialid = #{credentialid}")
    int deleteCred(Integer credentialid);

    @Update("Update CREDENTIALS set url=#{url}, username=#{username}, key=#{key}, password=#{password} where credentialid=#{credentialid}")
    int updateCred(Credential credential);
}
