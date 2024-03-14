package cat.tecnocampus.notes.application.service;

import cat.tecnocampus.notes.application.portsIn.UpdateNoteUserExists;
import cat.tecnocampus.notes.application.portsOut.NotePort;
import cat.tecnocampus.notes.domain.Note;
import org.springframework.stereotype.Component;

@Component
class UpdateUserNoteExists implements UpdateNoteUserExists {
    private final NotePort notePort;

    UpdateUserNoteExists(NotePort notePort) {
        this.notePort = notePort;
    }

    // TODO 3.2 Quan es reb la resposta de si l'usuari existeix, heu de cridar aquest mètode que s'encarrega de canviar
    //  la propietat checked a true o esborra la nota segons convingui. De fet, si l'usuari no existeix esborra totes les
    //  possibles notes d'aquell usuari (però això no té cap importància per l'examen).
    @Override
    public void updateNoteUserExists(String username, Long noteId, boolean userExists) {
        if (userExists) {
            Note old = notePort.findById(noteId);
            Note updated = new Note(old.id(), old.title(), old.content(), old.dateCreation(), old.dateEdit(), old.owner(), true);
            notePort.insert(updated);
        }
        else notePort.deleteOwnerNotes(username);
    }
}
