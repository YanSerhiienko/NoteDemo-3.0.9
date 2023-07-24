package com.example.NoteDemo309.note;

public enum Privacy {
    PRIVATE("Private"),
    PUBLIC("Public");

    public final String label;

    private Privacy(String label) {
        this.label = label;
    }
}
