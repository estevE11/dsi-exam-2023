package cat.tecnocampus.notes.adapters.webAdapter;

import cat.tecnocampus.notes.application.portsIn.NoteUseCases;
import cat.tecnocampus.notes.domain.Note;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
class NotesRESTController {
    private NoteUseCases noteUseCases;
    public NotesRESTController(NoteUseCases noteUseCases) {
        this.noteUseCases = noteUseCases;
    }


    @GetMapping(value = "/notes/{owner}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Note> getUserNotes(@PathVariable String owner) {
        List<Note> noteLabs = noteUseCases.getOwnerNotes(owner);
        return noteLabs;
    }

    @GetMapping(value = "/notes", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Note> getAllNotes() {
        List<Note> noteLabs = noteUseCases.getAllNotes();
        return noteLabs;
    }

    @PostMapping(value = "/notes")
    public void createNote(@RequestBody Note note) {
        noteUseCases.createNote(note);
    }

    @DeleteMapping(value = "/notes/{owner}")
    public void deleteOwnerNotes(@PathVariable String owner) {
        noteUseCases.deleteOwnerNotes(owner);
    }

    @ExceptionHandler(UserDoesNotExistException.class)
    public ResponseEntity handleUserDoesNotExist() {
        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }

}
