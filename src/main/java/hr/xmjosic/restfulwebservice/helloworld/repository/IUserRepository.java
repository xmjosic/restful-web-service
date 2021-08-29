package hr.xmjosic.restfulwebservice.helloworld.repository;

import hr.xmjosic.restfulwebservice.helloworld.model.User;

import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * User repository.
 */
public interface IUserRepository {
    /**
     * Fetch all users.
     *
     * @return Returns list of users.
     */
    Optional<List<User>> getUsers();

    /**
     * Save a new user.
     *
     * @param username Username.
     * @param birthDate Birthdate.
     * @return
     */
    Optional<User> saveUser(String username, Instant birthDate);

    /**
     * Fetch user by ID.
     *
     * @param id Provided ID.
     * @return Returns user.
     */
    Optional<User> getUserById(int id);

    /**
     * Delete user by ID.
     *
     * @param id Provided ID.
     * @return Returns true if user is deleted.
     */
    Optional<Boolean> deleteUserById(int id);
}
