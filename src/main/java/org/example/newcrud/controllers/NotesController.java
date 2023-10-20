package org.example.newcrud.controllers;

import org.example.newcrud.dao.WebNoteDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/notes")
public class NotesController {
    private final WebNoteDAO webNoteDAO;

    @Autowired
    public NotesController(WebNoteDAO webNoteDAO) {
        this.webNoteDAO = webNoteDAO;
    }

    @GetMapping
    public String index(Model model) {
        model.addAttribute("notes", webNoteDAO.index());
        return "/notes/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        model.addAttribute("note", webNoteDAO.show(id));
        return "/notes/show";
    }
}
