package hr.xmjosic.restfulwebservice.helloworld.validator.impl;

import hr.xmjosic.restfulwebservice.helloworld.model.User;
import hr.xmjosic.restfulwebservice.helloworld.repository.IUserH2Repository;
import hr.xmjosic.restfulwebservice.helloworld.repository.IUserRepository;
import hr.xmjosic.restfulwebservice.helloworld.validator.IValidator;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@Service
@AllArgsConstructor
@Slf4j
public class UserValidatorImpl implements IValidator {

    private final IUserH2Repository repository;

    @Override
    public void validateUser(String username, Instant birthDate) throws ExecutionException, InterruptedException {
        CompletableFuture<Boolean> validUsernameCompletableFuture = this.validateUsername(username);
        CompletableFuture<Boolean> validBirthDateCompletableFuture = this.validateBirthDate(birthDate);

        boolean validUsername = validUsernameCompletableFuture.get();
        log.info("validUsername: {}", validUsername);
        boolean validBirthDate = validBirthDateCompletableFuture.get();
        log.info("validBirthDate: {}", validBirthDate);
        validate(validUsername, "Username exist!");
        validate(validBirthDate, "Birthdate not valid!");
    }

    /**
     * Birthdate validation method.
     *
     * @param birthDate Birthdate of a user.
     * @return Returns true if birthdate is valid.
     */
    @Async
    private CompletableFuture<Boolean> validateBirthDate(Instant birthDate) {
        CompletableFuture<Boolean> validBirthDate = CompletableFuture.completedFuture(Boolean.TRUE);
        if (birthDate.isAfter(Instant.now())) validBirthDate = CompletableFuture.completedFuture(Boolean.FALSE);
        log.info("Birthdate validated by thread: {}", Thread.currentThread().getName());
        return validBirthDate;
    }

    /**
     * Username validation method.
     *
     * @param username Username of a user.
     * @return Returns true if username is available.
     */
    @Async
    private CompletableFuture<Boolean> validateUsername(String username) {
        Optional<List<User>> users = Optional.of(this.repository.findAll());
        log.info("Username validated by thread: {}", Thread.currentThread().getName());
        return CompletableFuture.completedFuture(
                users.orElseThrow(() -> new RuntimeException("no users available"))
                        .stream()
                        .noneMatch(user -> user.getUsername().equals(username)));
    }
}
