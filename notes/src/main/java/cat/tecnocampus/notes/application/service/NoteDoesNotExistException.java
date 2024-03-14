package cat.tecnocampus.notes.application.service;

public class NoteDoesNotExistException extends RuntimeException{
    public NoteDoesNotExistException(Long id) {
        super("Note with id " + id + " doen't exist");
    }
}
