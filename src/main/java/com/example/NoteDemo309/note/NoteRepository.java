package com.example.NoteDemo309.note;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface NoteRepository extends JpaRepository<Note, UUID> {
    /*@Query(nativeQuery = true, value = "SELECT id, title, content, privacy FROM notes WHERE user_id = :query")
    Page<Note> searchByUserId(@Param("query") String query, Pageable pageable);*/

    Page<Note> findByUserId(String userId, Pageable pageable);

    /*@Query(nativeQuery = true, value = "SELECT * FROM notes WHERE user_id = :query")
    Page<Note> getByUserId(@Param("query") String query, Pageable pageable);*/

    /*@Query(nativeQuery = true, value = "SELECT id, content, privacy, title FROM notes, users_notes " +
            "WHERE note.id = users_notes.note_id AND users_notes.users_id = :query")
    Page<Note> searchByUserId2(@Param("query") String query, Pageable pageable);*/
}
