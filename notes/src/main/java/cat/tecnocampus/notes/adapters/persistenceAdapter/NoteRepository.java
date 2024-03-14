package cat.tecnocampus.notes.adapters.persistenceAdapter;

import cat.tecnocampus.notes.domain.Note;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface NoteRepository extends JpaRepository<NoteEntity, Long> {
    @Query("""
            select new cat.tecnocampus.notes.domain.Note(
                            n.id, n.title, n.content, n.dateCreation, n.dateEdit, n.owner, n.checked)
            from NoteEntity n 
        """)
    List<Note> findAllNotes();

    @Query("""
            select new cat.tecnocampus.notes.domain.Note(
                            n.id, n.title, n.content, n.dateCreation, n.dateEdit, n.owner, n.checked)
            from NoteEntity n 
            where n.owner = :owner
        """)
    List<Note> findAllNotesByOwner(@Param("owner") String owner);

    @Query("""
            select new cat.tecnocampus.notes.domain.Note(
                            n.id, n.title, n.content, n.dateCreation, n.dateEdit, n.owner, n.checked)
            from NoteEntity n 
            where n.id = :id
        """)
    Optional<Note> findNoteById(@Param("id") Long id);

    @Modifying
    @Query("""
            delete from NoteEntity n 
            where n.owner = :owner
        """)
    void deleteByOwner(@Param("owner") String owner);
}
