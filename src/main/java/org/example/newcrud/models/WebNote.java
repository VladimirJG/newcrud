package org.example.newcrud.models;

//структура элемента
public class WebNote {
    private int id;
    private String note;

    public WebNote(int id, String note) {
        this.id = id;
        this.note = note;
    }

    public WebNote() {
    }
// возврат по id
    public int getId() {
        return id;
    }
// изменение id
    public void setId(int id) {
        this.id = id;
    }
//возврат элемента
    public String getNote() {
        return note;
    }
// изменение элемента
    public void setNote(String note) {
        this.note = note;
    }
}
