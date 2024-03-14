package cat.tecnocampus.users.application.portsOut;

import cat.tecnocampus.users.domain.User;

import java.util.List;
import java.util.Optional;

public interface UsersPort {
    void insert(User userLab);

    List<User> findAll();

    Optional<User> findByUsername(String userName);

    void delete(String username);

    //TODO 3.3 quan arriba el missatge preguntant per l'existència d'un usuari heu de fer la consulta pertinent cridant aquest
    //  port. A la resposta heu d'enviar un missatge amb el username, noteId i true/false segons existència de l'usuari.
    // Fixeu-vos que aquest servei ha de rebre un missatge i tot seguit enviar-ne un altre (recorda molt un dels exemples vists a classe).
    boolean existsUser(String username);
}
