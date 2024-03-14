package cat.tecnocampus.notes.adapters.persistenceAdapter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
public class NoteEntity {

    @Id
    private Long id;
    @Column
    private String title;
    @Column
    private String content;
    @Column
    private LocalDateTime dateCreation;
    @Column
    private LocalDateTime dateEdit;
    @Column
    private String owner;
    @Column
    private boolean checked;

    public NoteEntity() {
    }

    public NoteEntity(Long id, String title, String content, LocalDateTime dateCreation, LocalDateTime dateEdit, String owner, boolean checked) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.dateCreation = dateCreation;
        this.dateEdit = dateEdit;
        this.owner = owner;
        this.checked = checked;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public LocalDateTime getDateCreation() {
        return dateCreation;
    }

    public LocalDateTime getDateEdit() {
        return dateEdit;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setDateCreation(LocalDateTime dateCreation) {
        this.dateCreation = dateCreation;
    }

    public void setDateEdit(LocalDateTime dateEdit) {
        this.dateEdit = dateEdit;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String toString(){
        return "NoteLab: "+this.title+", Content: "+ this.content;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }
}
