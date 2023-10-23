package org.example.newcrud.dao;

import org.example.newcrud.models.WebNote;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class WebNoteDAO {
    private static int NOTE_COUNT;
    private List<WebNote> notes;

    public WebNoteDAO() {
        notes = new ArrayList<>();
        notes.add(new WebNote(++NOTE_COUNT, "Note 1"));
        notes.add(new WebNote(++NOTE_COUNT, "Note 2"));
        notes.add(new WebNote(++NOTE_COUNT, "Note 3"));
        notes.add(new WebNote(++NOTE_COUNT, "Note 4"));
        notes.add(new WebNote(++NOTE_COUNT, "Note 5"));
    }

    public List<WebNote> index() {
        return notes;
    }

    public WebNote show(int id) {
        return notes.stream().filter(n -> n.getId() == id).findAny().orElse(null);
    }

    public void save(WebNote webNote) {
        webNote.setId(++NOTE_COUNT);
        notes.add(webNote);
    }

    public void update(int id, WebNote updateWebNote) {
        WebNote toUpdateNote = show(id);
        toUpdateNote.setNote(updateWebNote.getNote());
    }

    public void delete(int id) {
        notes.removeIf(n -> n.getId() == id);
    }
}
