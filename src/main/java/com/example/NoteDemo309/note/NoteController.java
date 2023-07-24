package com.example.NoteDemo309.note;

import com.example.NoteDemo309.security.CustomUserDetails;
import com.example.NoteDemo309.user.User;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
//import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/note")
@RequiredArgsConstructor
public class NoteController {
    private final NoteService noteService;

    @GetMapping("/list")
    public String noteList(@AuthenticationPrincipal CustomUserDetails loggedUser, Model model) {
        /*List<Note> noteList = noteService.noteList();
        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!noteList = " + noteList);
        model.addAttribute("notes", noteList);
        return "note/note-list";*/
        System.out.println("loggedUser = " + loggedUser);

        return findPaginated(loggedUser, 1, model);

        /*return "note/note-list";*/
    }

    /*@GetMapping("/list")
    public String noteList(@AuthenticationPrincipal CustomUserDetails loggedUser, Model model) {
        List<Note> noteList = noteService.noteList();
        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!noteList = " + noteList);
        model.addAttribute("notes", noteList);
        return "note/note-list";

        *//*System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!loggedUser.getId() = " + loggedUser.getId());
        return findPaginated(1, loggedUser.getId(), model);*//*

        *//*return "note/note-list";*//*
    }*/

    @GetMapping("/list/page/{pageNumber}")
    public String findPaginated(@AuthenticationPrincipal CustomUserDetails loggedUser,
                                @PathVariable("pageNumber") int pageNumber, Model model) {
        System.out.println("loggedUser = " + loggedUser);
        int pageSize = 5;

        Page<Note> page = noteService.findPaginated(pageNumber, pageSize, loggedUser.getId());
        List<Note> noteList = page.getContent();

        model.addAttribute("page", page);
        model.addAttribute("currentPage", pageNumber);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());

        model.addAttribute("notes", noteList);

        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!noteList.size() = " + noteList.size());

        return "note/note-list";
    }

    @GetMapping("/create")
    public String createNote(Model model) {
        Note note = new Note();
        model.addAttribute("note", note);
        return "note/note-form";
    }

    /*@PostMapping("/save")
    public String createNote(Principal principal,
                             @ModelAttribute("note") Note note,
                             @RequestParam Privacy privacy) {
        note.setPrivacy(privacy);
        noteService.save(principal.getName(), note);
        return "redirect:/note/list";
    }*/

    @PostMapping("/save")
    public String createNote(@Valid @ModelAttribute("note") Note note,
                             BindingResult result,
                             Model model,
                             @RequestParam Privacy privacy,
                             Principal principal) {

        if (result.hasErrors()) {
            model.addAttribute("note", note);
            return "note/note-form";
        }

        note.setPrivacy(privacy);
        noteService.save(principal.getName(), note);
        return "redirect:/note/list";
    }


    @GetMapping("/edit")
    public String editNote(@RequestParam(value = "id") UUID id, Model model) {
        Note note = noteService.getById(id);
        if (note == null) {
            return "redirect:/error/404";
        } else {
            model.addAttribute("note", note);
            return "note/note-form";
        }
    }

    @PostMapping("/delete")
    public String deleteNote(@RequestParam(value = "id") UUID id) {
        noteService.deleteById(id);
        return "redirect:/note/list";
    }

    @GetMapping("/share/{id}")
    public String shareNote(@PathVariable(name = "id") UUID id, Model model, Principal principal) {
        Note note = noteService.getById(id);
        if (note == null) {
            return "redirect:/error/404";
        } else if (note.getPrivacy().label.equals("Private") && !note.getAuthor().equals(principal.getName())) {
            return "redirect:/error/404";
        } else {
            model.addAttribute("note", note);
            return "note/note-info";
        }
    }
}
