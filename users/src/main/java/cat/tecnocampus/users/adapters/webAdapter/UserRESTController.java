package cat.tecnocampus.users.adapters.webAdapter;

import cat.tecnocampus.users.application.portsIn.UserUseCases;
import cat.tecnocampus.users.application.service.UserDoesNotExistException;
import cat.tecnocampus.users.domain.User;
import cat.tecnocampus.users.domain.UserWithNotes;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
class UserRESTController {
    private UserUseCases userUseCases;

    public UserRESTController(UserUseCases userUseCases) {
        this.userUseCases = userUseCases;
    }

    @GetMapping(value = "/users", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<UserWithNotes> listUsers() {
        return userUseCases.getUsers();
    }

    @GetMapping(value = "/users/{username}", produces = MediaType.APPLICATION_JSON_VALUE)
    public UserWithNotes showUser(@PathVariable String username) {
        return userUseCases.getUser(username);
    }

    @GetMapping(value = "/users/exists/{username}", produces = MediaType.APPLICATION_JSON_VALUE)
    public boolean existsUser(@PathVariable String username) {
        return userUseCases.userExists(username);
    }

    @PostMapping(value = "/users", produces = MediaType.APPLICATION_JSON_VALUE)
    public void createUser(@RequestBody User user) {
        userUseCases.registerUser(user);
    }

    @DeleteMapping(value = "/users/{username}", produces = MediaType.APPLICATION_JSON_VALUE)
    public void deleteUser(@PathVariable String username) {
        userUseCases.deleteUser(username);
    }

    @ExceptionHandler({UserDoesNotExistException.class})
    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String userDoesNotExist(Exception exception) {
        return exception.getMessage();
    }

}
