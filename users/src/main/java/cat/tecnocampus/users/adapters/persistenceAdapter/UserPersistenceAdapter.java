package cat.tecnocampus.users.adapters.persistenceAdapter;

import cat.tecnocampus.users.application.portsOut.UsersPort;
import cat.tecnocampus.users.domain.User;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class UserPersistenceAdapter implements UsersPort {
    private final UsersRepository usersRepository;

    private UserEntity UserEntityFromUser(User user) {
        return new UserEntity(user.username(), user.name(), user.secondName(), user.email());
    }

    public UserPersistenceAdapter(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    @Override
    public void insert(User userLab) {
        usersRepository.save(UserEntityFromUser(userLab));
    }

    @Override
    public List<User> findAll() {
        return usersRepository.findAllUsers();
    }

    @Override
    public Optional<User> findByUsername(String userName) {
        return usersRepository.findUserByUsername(userName);
    }

    @Override
    public void delete(String username) {
        usersRepository.deleteById(username);
    }

    @Override
    public boolean existsUser(String username) {
        return usersRepository.existsById(username);
    }
}
