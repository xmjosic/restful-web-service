package hr.xmjosic.restfulwebservice.helloworld.validator;

import hr.xmjosic.restfulwebservice.helloworld.exception.UserException;

import java.time.Instant;
import java.util.Date;
import java.util.concurrent.ExecutionException;

/**
 * Validator.
 */
public interface IValidator {
    /**
     * Validate user.
     *
     * @param username Provided username.
     * @param birthDate Provided birthdate.
     */
    void validateUser(String username, Instant birthDate) throws ExecutionException, InterruptedException;

    /**
     * Throws exception if not valid.
     *
     * @param valid Validation flag.
     * @param errorMsg Error message.
     */
    default void validate(boolean valid, String errorMsg) {
        if (!valid) throw new UserException(errorMsg);
    }
}
