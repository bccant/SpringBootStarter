package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mapper.*;
import com.udacity.jwdnd.course1.cloudstorage.model.*;
import org.springframework.stereotype.*;

import javax.annotation.*;
import java.util.*;

@Service
public class NoteService {
    private List<Note> noteList;
    private NoteMapper noteMapper;

    public NoteService(NoteMapper noteMapper) {
        this.noteMapper = noteMapper;
    }

    @PostConstruct
    public void noteConstruct() {
        this.noteList = new ArrayList<>();
    }

    public Note getNoteById(int noteId) {
        return noteMapper.getNoteById(noteId);
    }

    public List<Note> getAllNotes(int userId) {
        return noteMapper.getAllNotes(userId);
    }

    public void addNote(Note note) {
        noteMapper.addNote(note);
    }

    public int deleteNote(int noteId) {
        return noteMapper.deleteNote(noteId);
    }

    public int UpdateNote(Note note) {
        return noteMapper.updateNote(note);
    }
}
