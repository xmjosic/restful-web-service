package hr.xmjosic.restfulwebservice.helloworld.repository.impl;

import hr.xmjosic.restfulwebservice.helloworld.model.User;
import hr.xmjosic.restfulwebservice.helloworld.repository.IUserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.*;

@Repository("staticList")
@Slf4j
public   class UserStaticListRepositoryImpl implements IUserRepository {

    public static List<User> users = new ArrayList<>();
    private static int idCounter = 4;

    static {
        users.add(new User(1,"john.travolta", Instant.now()));
        users.add(new User(2,"travolta", Instant.now()));
        users.add(new User(3,"john", Instant.now()));
    }

    @Override
    public Optional<List<User>> getUsers() {
        return Optional.ofNullable(users);
    }

    @Override
    public Optional<User> saveUser(String username, Instant birthDate) {
        User user = new User();
        user.setUsername(username);
        user.setBirthDate(birthDate);
        user.setId(idCounter++);
        users.add(user);
        log.info("Created user {}", user);
        return Optional.of(user);
    }

    @Override
    public Optional<User> getUserById(int id) {
        return users
                .stream()
                .filter(user -> user.getId() == id)
                .findFirst();
    }

    @Override
    public Optional<Boolean> deleteUserById(int id) {
        return Optional.of(users.removeIf(user -> user.getId() == id));
    }
}
