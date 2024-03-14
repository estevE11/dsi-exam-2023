package cat.tecnocampus.users.application.service;

import cat.tecnocampus.users.application.portsOut.UsersPort;
import cat.tecnocampus.users.domain.Note;
import cat.tecnocampus.users.domain.User;
import cat.tecnocampus.users.domain.UserWithNotes;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.stream.Collectors;

@Service
class UserUseCases implements cat.tecnocampus.users.application.portsIn.UserUseCases {

    private final UsersPort usersPort;
    private final WebClient webClient;
    private final String notesServiceUrl;

    public UserUseCases(UsersPort UsersPort, WebClient webClient, @Value("${app.notes-service.host}") String notesServiceHost) {
        this.usersPort = UsersPort;
        this.webClient = webClient;
        this.notesServiceUrl = "http://" + notesServiceHost + "/notes";
    }

    @Override
    public User createUser(String username, String name, String secondName, String email) {
        User user = new User(username, name, secondName, email);
        registerUser(user);
        return user;
    }

    @Override
    public void registerUser(User user) {
        usersPort.insert(user);
    }

    @Override
    public void deleteUser(String username) {
        if (userExists(username)) {
            usersPort.delete(username);
        }
   }

    @Override
    public List<UserWithNotes> getUsers() {
        // TODO 1.0 (aquí no heu de fer res) Aquest mètode llegeix els usuaris de la BBDD i crida el mètode getUserNotes
        //  per anar a buscar les notes al servei corresponent.
        //  Ja podeu anar al todo 1.1
         return usersPort.findAll().stream().map(user -> new UserWithNotes(user.username(), user.name(), user.secondName(),
                 user.email(), getUserNotes(user.username()))).collect(Collectors.toList());
    }

    @Override
    public UserWithNotes getUser(String username) {
        User user = usersPort.findByUsername(username).orElseThrow(() -> new UserDoesNotExistException(username));
        List<Note> notes = getUserNotes(username);
        UserWithNotes userWithNotes = new UserWithNotes(user.username(), user.name(), user.secondName(), user.email(), notes);
        return userWithNotes;
    }

    // TODO 1.1: heu de refactoritzar aquesta crida de manera que segueixi l'arquitectura hexagonal. Aquesta crida s'hauria
    //  de fer en un adaptador i la seva interfície hauria d'estar en un port de sortida.
    //  Aquí es demana les notes de l'usuari al servei corresponent.

    // TODO 2: heu d'embolcallar la crida amb un circuit breaker de manera que si el circuit és obert es retorni una llista
    //  amb una única nota que tingui per títol "Servei de notes inaccessible". A la resta de propietats de la nota poseu el
    //  que vulgueu
    private List<Note> getUserNotes(String username) {

        return webClient.get()
                .uri(notesServiceUrl + "/" + username)
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<List<Note>>() {})
                .block();
    }
    @Override
    public boolean userExists(String username) {
        return usersPort.existsUser(username);
    }

}
