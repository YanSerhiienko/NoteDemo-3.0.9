package com.example.NoteDemo309.note;

import com.example.NoteDemo309.user.User;
import com.example.NoteDemo309.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class NoteService {
    private final NoteRepository noteRepository;
    private final UserRepository userRepository;

    public Page<Note> findPaginated(int pageNumber, int pageSize, long userId) {
        PageRequest pageable = PageRequest.of(pageNumber - 1, pageSize);
        return noteRepository.findByUserId(String.valueOf(userId), pageable);
        //return noteRepository.findByUserId(String.valueOf(userId), pageable);
        //return noteRepository.searchByUserId2(String.valueOf(userId), pageable);
        //return noteRepository.findAll(pageable);
    }

    public List<Note> noteList() {
        return noteRepository.findAll();
    }

    public void save(String login, Note note) {
        User loggedUser = userRepository.findByLogin(login);
        note.setUser(loggedUser);
        noteRepository.save(note);
    }

    public void save(Note note) {
        noteRepository.save(note);
    }

    public Note getById(UUID id) {
        return noteRepository.findById(id).orElse(null);
    }

    public void deleteById(UUID id) {
        noteRepository.deleteById(id);
    }


}

