package hr.xmjosic.restfulwebservice.helloworld.service.impl;

import hr.xmjosic.restfulwebservice.helloworld.exception.UserException;
import hr.xmjosic.restfulwebservice.helloworld.model.User;
import hr.xmjosic.restfulwebservice.helloworld.repository.IUserRepository;
import hr.xmjosic.restfulwebservice.helloworld.service.IUserService;
import hr.xmjosic.restfulwebservice.helloworld.validator.IValidator;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutionException;

@Service("staticListService")
@AllArgsConstructor
@Slf4j
public class UserStaticListServiceImpl implements IUserService {

    private final IUserRepository repository;
    private final IValidator validator;

    @Override
    public List<User> getUsers() {
        Optional<List<User>> users = this.repository.getUsers();
        log.info("Fetched users {}", users);
        return users.orElseThrow(() -> new UserException("Users not found!"));
    }

    @Override
    public User saveUser(User newUser) throws ExecutionException, InterruptedException {
        log.info("Validate new user.");
        this.validator.validateUser(newUser.getUsername(), newUser.getBirthDate());
        log.info("New user validated!");
        Optional<User> user = this.repository.saveUser(newUser.getUsername(), newUser.getBirthDate());
        log.info("User created {}", user);
        return user.orElseThrow(() -> new UserException("User not created!"));
    }

    @Override
    public User getUserById(int id) {
        Optional<User> userById = this.repository.getUserById(id);
        log.info("User with provided id: {}", userById);
        return userById.orElseThrow(() -> new UserException("User not found!"));
    }

    @Override
    public boolean deleteUserById(int id) {
        log.info("User with ID {} will be deleted", id);
        Optional<Boolean> userDeleted = this.repository.deleteUserById(id);
        boolean deleted = userDeleted.orElseThrow(() -> new UserException("User not found!"));
        log.info("User deleted: {}", deleted);
        if (!deleted) throw new UserException("User not found!");
        return deleted;
    }
}
