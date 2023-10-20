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
        notes = new ArrayList<WebNote>();
        notes.add(new WebNote(++NOTE_COUNT, "test1"));
        notes.add(new WebNote(++NOTE_COUNT, "test2"));
        notes.add(new WebNote(++NOTE_COUNT, "test3"));
        notes.add(new WebNote(++NOTE_COUNT, "test4"));
        notes.add(new WebNote(++NOTE_COUNT, "test5"));
    }

    public List<WebNote> index() {
        return notes;
    }

    public WebNote show(int id) {
        return notes.stream().filter(n -> n.getId() == id).findAny().orElse(null);
    }
}
