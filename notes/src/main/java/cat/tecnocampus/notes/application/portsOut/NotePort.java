package cat.tecnocampus.notes.application.portsOut;

import cat.tecnocampus.notes.domain.Note;

import java.util.List;

public interface NotePort {
    List<Note> findAll();

    List<Note> findByOwner(String owner);

    Note findById(Long id);

    void insert(Note note);

    void deleteOwnerNotes(String owner);
}
