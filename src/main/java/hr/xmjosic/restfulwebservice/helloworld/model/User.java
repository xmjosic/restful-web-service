package hr.xmjosic.restfulwebservice.helloworld.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.*;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Size;
import java.time.Instant;

/**
 * User model.
 */
@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
@Entity
public class User {
    /**
     * Unique user ID.
     */
    @JsonView(View.AllUsersList.class)
    @Id
    @GeneratedValue
    @NonNull
    private Integer id;

    /**
     * Unique username of a user.
     */
    @JsonView({View.AllUsersList.class, View.createUser.class})
    @Size(min = 3, max = 25, message = "Username size must be between 3 and 25")
    @NonNull
    private String username;

    /**
     * Birthdate of a user.
     */
    @JsonView(View.createUser.class)
    @NonNull
    private Instant birthDate;

    /**
     * Last modified date.
     */
    @JsonIgnore
    @LastModifiedDate
    private Instant lastModified;
}
