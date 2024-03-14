package cat.tecnocampus.users.application.portsIn;

import cat.tecnocampus.users.domain.User;
import cat.tecnocampus.users.domain.UserWithNotes;

import java.util.List;

public interface UserUseCases {
    User createUser(String username, String name, String secondName, String email);

    void registerUser(User user);

    void deleteUser(String username);

    List<UserWithNotes> getUsers();

    UserWithNotes getUser(String userName);

    boolean userExists(String username);
}
