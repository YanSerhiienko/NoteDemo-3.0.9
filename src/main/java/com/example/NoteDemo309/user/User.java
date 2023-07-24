package com.example.NoteDemo309.user;

import com.example.NoteDemo309.note.Note;
import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import lombok.Data;
import org.hibernate.annotations.NaturalId;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NaturalId
    @Size(min = 5, max = 50)

    private String login;

    @Column
    @Size(min = 8, max = 100)
    //@Pattern(regexp = "")
    private String password;

    @OneToMany(mappedBy = "user")
    private List<Note> notes = new ArrayList<>();

    /*@OneToMany
    @JoinTable(
            name="users_notes",
            joinColumns = @JoinColumn( name="user_id"),
            inverseJoinColumns = @JoinColumn( name="note_id")
    )
    private List<Note> notes = new ArrayList<>();*/

    @Enumerated(EnumType.STRING)
    private Role role;
}
