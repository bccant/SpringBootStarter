package com.udacity.jwdnd.course1.cloudstorage.mapper;

import com.udacity.jwdnd.course1.cloudstorage.model.*;
import org.apache.ibatis.annotations.*;

import java.util.*;

@Mapper
public interface NoteMapper {
    @Select("Select * from NOTES where noteid = #{noteid}")
    Note getNoteById(Integer noteid);

    @Select("Select * from NOTES where userid = #{userid}")
    List<Note> getAllNotes(Integer userid);

    @Insert("Insert INTO NOTES (notetitle, notedescription, userid) VALUES(#{notetitle}, #{notedescription}, #{userid})")
    @Options(useGeneratedKeys = true, keyProperty = "noteid")
    int addNote(Note note);

    @Delete("DELETE FROM NOTES WHERE noteid = #{noteid}")
    int deleteNote(Integer noteid);

    @Update("UPDATE NOTES SET notetitle=#{notetitle}, notedescription = #{notedescription} WHERE noteid = #{noteid}")
    int updateNote(Note note);

}
