package cat.tecnocampus.users.application.service;

public class UserDoesNotExistException extends RuntimeException {
    public UserDoesNotExistException(String username) {
        super("User with username: " + username + " doesn't exit");
    }
}
