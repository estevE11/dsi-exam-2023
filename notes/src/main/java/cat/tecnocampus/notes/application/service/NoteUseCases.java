package cat.tecnocampus.notes.application.service;

import cat.tecnocampus.notes.application.portsOut.NotePort;
import cat.tecnocampus.notes.domain.Note;
import com.github.f4b6a3.tsid.Tsid;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;

@Service
class NoteUseCases implements cat.tecnocampus.notes.application.portsIn.NoteUseCases {

    private final NotePort notePort;

    public NoteUseCases(NotePort notePort) {
        this.notePort = notePort;
    }

    @Transactional
    @Override
    public void createNote(Note note) {

        Long id = Tsid.fast().toLong();  //genera el Id de la nota
        var newNote = new Note(id, note.title(), note.content(),
                    LocalDateTime.now(), LocalDateTime.now(), note.owner(), false);
        notePort.insert(newNote);

        //TODO 3.1 envia un missatge per preguntar si el propietari de la nota existeix. Al missatge hi necessitem
        // tant el username del propietari com el id de la nota
    }

    @Override
    public void deleteOwnerNotes(String owner) {
        notePort.deleteOwnerNotes(owner);
    }

    @Override
    public List<Note> getOwnerNotes(String owner) {
        return notePort.findByOwner(owner);
    }

    @Override
    public List<Note> getAllNotes() {
        return notePort.findAll();
    }

    @Override
    public Note getNoteById(Long id) {
        return notePort.findById(id);
    }

}
