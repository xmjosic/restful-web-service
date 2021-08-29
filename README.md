# restful-web-service

## Demo project

Simple user management app.

-REST API controllers

-Validations

-Exception handling

-HATEOAS - Hypermedia as the Engine of Application State


____
#### Controller

```java
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
```

___
#### Service

```java
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
```