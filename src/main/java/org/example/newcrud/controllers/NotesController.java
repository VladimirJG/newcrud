package org.example.newcrud.controllers;

import org.example.newcrud.dao.WebNoteDAO;
import org.example.newcrud.models.WebNote;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller // класс является контроллером и обрабатывает http запросы
@RequestMapping("/notes") // адрес html файлов для работы методов класса контроллера
//класс управляет http запросами для работы с данными приложения(в данном случае notes)
public class NotesController {
    private final WebNoteDAO webNoteDAO;

    @Autowired // инициализация при помощи автоматически найденных бинов
    public NotesController(WebNoteDAO webNoteDAO) {
        this.webNoteDAO = webNoteDAO;
    }

    @GetMapping // гет запрос
    public String index(Model model) {
        model.addAttribute("notes", webNoteDAO.index()); // Используя медоды интерфйса Model добавляем весь List DAO класса
        return "/notes/index"; // возвращаем  всю добавленную инфу в index.html для последующей передачи на веб страницу
    }

    @GetMapping("/{id}") // возврат по id одного элемента листа(db) DAO класса // гет запрос
    public String show(@PathVariable("id") int id, Model model) {
        model.addAttribute("note", webNoteDAO.show(id));
        return "/notes/show"; // передача в views/show.html  из model
    }

    /* @PatchMapping // метод создания новой заметки с передачей в модель параметров элемента
     public String create(@RequestParam("note")  String note, Model model) {
         WebNote webNote = new WebNote();
         webNote.setNote(note);
         return "successPage";
     }*/
    @GetMapping("/new") // создание и добавление нового элемента в new и дальнейший вывод // гет запрос
    public String newNote(Model model) {
        model.addAttribute("webNote", new WebNote());
        return "/notes/new";
    }

    @PostMapping /*метод создания новой заметки через @ModelAttribute с передачей всего объекта (в данном случае WebNote )*/
    public String create(@ModelAttribute("webNote") WebNote webNote) {
        webNoteDAO.save(webNote); // после исполнения метода save  перенаправляем в html  для вывода
        return "redirect:/notes";
    }

    @GetMapping("/{id}/edit")// переход на страницу для редактирования элемента
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("webNote", webNoteDAO.show(id));
        return "/notes/edit";
    }

    @PatchMapping("/{id}") // редактирование элемента
    public String update(@ModelAttribute("webNote") WebNote webNote, @PathVariable("id") int id) {
        webNoteDAO.update(id, webNote);
        return "redirect:/notes";
    }

    @DeleteMapping("/{id}") //удаление элемента
    public String delete(@PathVariable("id") int id) {
        webNoteDAO.delete(id);
        return "redirect:/notes";
    }
}
