package cat.tecnocampus.users.domain;

import java.time.LocalDateTime;

public record Note(Long id, String title, String content, LocalDateTime dateCreation,
                   LocalDateTime dateEdit, String owner, boolean checked){
}
