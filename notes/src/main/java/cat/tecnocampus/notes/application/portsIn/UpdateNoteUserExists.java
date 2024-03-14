package cat.tecnocampus.notes.application.portsIn;

public interface UpdateNoteUserExists {
    void updateNoteUserExists(String username, Long noteId, boolean userExists);
}
