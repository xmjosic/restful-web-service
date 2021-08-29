package hr.xmjosic.restfulwebservice.helloworld.service;

import hr.xmjosic.restfulwebservice.helloworld.model.User;

import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * User service.
 */
public interface IUserService {
    /**
     * Get list of all users.
     *
     * @return List of users.
     */
    List<User> getUsers();

    /**
     * Save a new user.
     *
     * @param newUser New user.
     * @return Returns created user.
     * @throws ExecutionException Exception.
     * @throws InterruptedException Exception.
     */
    User saveUser(User newUser) throws ExecutionException, InterruptedException;

    /**
     * Get user by ID.
     *
     * @param id Provided user ID.
     * @return Returns fetched user by ID.
     */
    User getUserById(int id);

    /**
     * Delete user by ID.
     *
     * @param id Provided user ID.
     * @return Returns true if user is deleted.
     */
    boolean deleteUserById(int id);
}
