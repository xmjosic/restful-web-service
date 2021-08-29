package hr.xmjosic.restfulwebservice.helloworld.controller;

import com.fasterxml.jackson.annotation.JsonView;
import hr.xmjosic.restfulwebservice.helloworld.model.User;
import hr.xmjosic.restfulwebservice.helloworld.model.View;
import hr.xmjosic.restfulwebservice.helloworld.service.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * User rest controller.
 */
@RestController
@RequestMapping("/api/v1/users")
@Slf4j
public class UserController {

    private final IUserService service;

    @Autowired
    public UserController(@Qualifier("H2Service") IUserService service) {
        this.service = service;
    }

    /**
     * Fetch all users.
     *
     * @return Returns list of all users.
     */
    @JsonView(View.AllUsersList.class)
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<User> getAllUsers() {
        log.info("Get all users");
        return this.service.getUsers();
    }

    /**
     * Save a new user.
     *
     * @param newUser New user DTO object.
     * @return Returns created user.
     * @throws ExecutionException Exception.
     * @throws InterruptedException Exception.
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public EntityModel<User> saveUser(@JsonView(View.createUser.class) @Valid @RequestBody User newUser) throws ExecutionException, InterruptedException {
        log.info("Save new user: {}", newUser);
        User user = this.service.saveUser(newUser);
        EntityModel<User> retVal = EntityModel.of(user);
        WebMvcLinkBuilder webMvcLinkBuilder =
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getUserById(user.getId().toString()));
        retVal.add(webMvcLinkBuilder.withSelfRel());
        return retVal;
    }

    /**
     * Get user by ID.
     *
     * @param id User ID.
     * @return Returns user by provided ID.
     */
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public User getUserById(@PathVariable String id) {
        log.info("Get user with ID: {}", id);
        return this.service.getUserById(Integer.parseInt(id));
    }

    /**
     * Delete user by ID.
     *
     * @param id User ID.
     * @return Returns user by provided ID.
     */
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUserById(@PathVariable String id) {
        log.info("Delete user with ID: {}", id);
        this.service.deleteUserById(Integer.parseInt(id));
    }
}
