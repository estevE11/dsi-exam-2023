package cat.tecnocampus.users.domain;

import java.util.List;

public record UserWithNotes(String username, String name, String secondName, String email, List<Note> notes) {
}

