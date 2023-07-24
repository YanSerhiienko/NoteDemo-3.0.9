package com.example.NoteDemo309.note;

import com.example.NoteDemo309.user.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.UUID;

@Data
@Entity
@Table(name = "notes")
public class Note {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column
    @Size(min = 5, max = 100)
    private String title;

    @Column
    @Size(min = 5, max = 10000)
    private String content;

    @Column
    @Enumerated(EnumType.STRING)
    private Privacy privacy;

    @ManyToOne
    @JoinColumn(name = "user_id")  //, nullable = false
    private User user;

    public String getAuthor() {
        return user == null ? "User not found" : user.getLogin();
    }
}
