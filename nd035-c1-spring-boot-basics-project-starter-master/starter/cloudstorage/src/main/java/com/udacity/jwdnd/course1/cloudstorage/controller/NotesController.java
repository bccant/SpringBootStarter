package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.mapper.*;
import com.udacity.jwdnd.course1.cloudstorage.model.*;
import com.udacity.jwdnd.course1.cloudstorage.services.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.security.core.*;
import org.springframework.stereotype.*;
import org.springframework.ui.*;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Controller
@RequestMapping()
public class  NotesController {

    @Autowired
    private NoteService noteService;
    private UserMapper userMapper;

    public NotesController(NoteService noteService, UserMapper userMapper) {
        this.noteService = noteService;
        this.userMapper = userMapper;
    }

    @PostMapping("/notes")
    public String addOrUpdateNotes(Authentication authentication, @ModelAttribute("notes")Note newNote, Model model) {
        User user = userMapper.getUser(authentication.getName());
        newNote.setUserid(user.getUserid());

        if (newNote.getNoteid() == null) {
            noteService.addNote(newNote);
        } else {
            noteService.UpdateNote(newNote);
        }

        List<Note> notes = noteService.getAllNotes(user.getUserid());

        model.addAttribute("notes", notes);

        return "redirect:/result?success";
        
    }

    @GetMapping("/notes/delete")
    public String deleteNote(@RequestParam("id") int noteid) {
        noteService.deleteNote(noteid);
        return "redirect:/result?success";
    }

    @GetMapping("/notes")
    public String getNote(Authentication authentication, Model model) {
        User user = userMapper.getUser(authentication.getName());
        model.addAttribute("notes", noteService.getAllNotes(user.getUserid()));
        return "home";
    }

}
