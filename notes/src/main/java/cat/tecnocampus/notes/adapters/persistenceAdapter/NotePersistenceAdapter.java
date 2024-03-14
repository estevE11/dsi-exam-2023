package cat.tecnocampus.notes.adapters.persistenceAdapter;

import cat.tecnocampus.notes.application.portsOut.NotePort;
import cat.tecnocampus.notes.application.service.NoteDoesNotExistException;
import cat.tecnocampus.notes.domain.Note;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.List;
@Component
class NotePersistenceAdapter implements NotePort{
    private final NoteRepository noteRepository;

    NotePersistenceAdapter(NoteRepository noteRepository) {
        this.noteRepository = noteRepository;
    }

    @Override
    public List<Note> findAll() {
        return noteRepository.findAllNotes();
    }

    @Override
    public List<Note> findByOwner(String owner) {
        return noteRepository.findAllNotesByOwner(owner);
    }

    @Override
    public Note findById(Long id) {
        return noteRepository.findNoteById(id).orElseThrow(() -> new NoteDoesNotExistException(id));
    }

    @Override
    public void insert(Note note) {
        NoteEntity noteEntity = noteEntityFromNote(note);
        noteRepository.save(noteEntity);
    }

    private NoteEntity noteEntityFromNote(Note note) {
        return new NoteEntity(note.id(), note.title(), note.content(),
                note.dateCreation(), note.dateEdit(), note.owner(), note.checked());
    }

    @Override
    @Transactional
    public void deleteOwnerNotes(String owner) {
        noteRepository.deleteByOwner(owner);
    }

}
