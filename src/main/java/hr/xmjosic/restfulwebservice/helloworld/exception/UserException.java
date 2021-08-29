package hr.xmjosic.restfulwebservice.helloworld.exception;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserException extends RuntimeException {
    private String message;
}
