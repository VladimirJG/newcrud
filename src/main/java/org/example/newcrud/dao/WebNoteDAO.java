package org.example.newcrud.dao;

import org.example.newcrud.models.WebNote;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class WebNoteDAO {
    private static int NOTE_COUNT;
    private List<WebNote> notes;

    //создание списка(ArrayList)
    public WebNoteDAO() {
        notes = new ArrayList<>();
        notes.add(new WebNote(++NOTE_COUNT, "Note 1"));
        notes.add(new WebNote(++NOTE_COUNT, "Note 2"));
        notes.add(new WebNote(++NOTE_COUNT, "Note 3"));
        notes.add(new WebNote(++NOTE_COUNT, "Note 4"));
        notes.add(new WebNote(++NOTE_COUNT, "Note 5"));
    }

    // вывод всего списка на страницу
    public List<WebNote> index() {
        return notes;
    }

    // вывод/переход на страницу элемента по id либо null
    public WebNote show(int id) {
        return notes.stream().filter(n -> n.getId() == id).findAny().orElse(null);
    }

    // сохранение нового элемента
    public void save(WebNote webNote) {
        webNote.setId(++NOTE_COUNT); //добавление id
        notes.add(webNote); /*добавление элемента в списук существующих*/
    }

    // изменение существующего элемента
    public void update(int id, WebNote updateWebNote) {
        WebNote toUpdateNote = show(id);
        toUpdateNote.setNote(updateWebNote.getNote());
    }

    //удаление элемента
    public void delete(int id) {
        notes.removeIf(n -> n.getId() == id);
    }
}
