package cat.tecnocampus.notes.application.portsIn;

import cat.tecnocampus.notes.domain.Note;

import java.util.List;

public interface NoteUseCases {
    void createNote(Note noteLab);

    void deleteOwnerNotes(String owner);

    List<Note> getOwnerNotes(String owner);

    List<Note> getAllNotes();

    Note getNoteById(Long id);
}
