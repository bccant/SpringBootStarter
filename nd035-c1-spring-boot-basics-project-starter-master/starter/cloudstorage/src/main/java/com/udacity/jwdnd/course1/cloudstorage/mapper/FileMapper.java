package com.udacity.jwdnd.course1.cloudstorage.mapper;

import com.udacity.jwdnd.course1.cloudstorage.model.*;
import org.apache.ibatis.annotations.*;

import java.util.*;

@Mapper
public interface FileMapper {
    @Select("Select * from FILES where fileId = #{fileId}")
    Files getFileId(Integer fileId);

    @Select("Select * from FILES where userid = #{userid}")
    List<Files> getAllFiles(Integer userid);

    @Select("select * from FILES where userid = #{userid} and filename = #{filename}")
    Files getFileByName(@Param("userid")Integer userid, @Param("filename")String filename);

    @Select("Select * from NOTES where userid = #{userid}")
    List<Note> getAllNotes(Integer userid);

    @Insert("Insert INTO FILES (filename, contenttype, filesize, filedata, userid) VALUES(#{filename}, #{contenttype}, #{filesize}, #{filedata}, #{userid})")
    @Options(useGeneratedKeys = true, keyProperty = "fileId")
    int addFile(Files files);

//    @Delete("DELETE FROM FILES where fileId = #{fileId}")
//    int deleteFile(Integer fileId);

    @Delete("DELETE FROM FILES where filename = #{filename}")
    int deleteFile(String filename);
}
