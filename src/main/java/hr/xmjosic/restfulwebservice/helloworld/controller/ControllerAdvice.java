package hr.xmjosic.restfulwebservice.helloworld.controller;

import hr.xmjosic.restfulwebservice.helloworld.dto.ErrorMsgDto;
import hr.xmjosic.restfulwebservice.helloworld.exception.UserException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import java.time.Instant;
import java.util.Date;

/**
 * Rest controller advice.
 */
@RestControllerAdvice
public class ControllerAdvice {
    /**
     * Handler for a user exception.
     *
     * @param ex User exception.
     * @param request Servlet request.
     * @return Returns custom exception message.
     */
    @ExceptionHandler(value = UserException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ErrorMsgDto userExceptionHandler(UserException ex, HttpServletRequest request) {
        return new ErrorMsgDto(HttpStatus.BAD_REQUEST.value(), ex.getMessage(), request.getServletPath(), Instant.now());
    }

    /**
     * Handler for a MethodArgumentNotValidException exception.
     *
     * @param ex MethodArgumentNotValidException.
     * @param request Servlet request.
     * @return Returns custom exception message.
     */
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ErrorMsgDto MethodArgumentNotValidExceptionHandler(MethodArgumentNotValidException ex, HttpServletRequest request) {
        String errorMsg = ex.getBindingResult().toString();
        if (ex.getBindingResult().getFieldError() != null && ex.getBindingResult().getFieldError().getDefaultMessage() != null)
            errorMsg = ex.getBindingResult().getFieldError().getDefaultMessage();
        return new ErrorMsgDto(HttpStatus.BAD_REQUEST.value(), errorMsg, request.getServletPath(), Instant.now());
    }

    /**
     * Handler for unchecked exceptions.
     *
     * @param ex Exception.
     * @param request Servlet request.
     * @return Returns custom exception message.
     */
    @ExceptionHandler(value = Exception.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorMsgDto uncheckedExceptionHandler(Exception ex, HttpServletRequest request) {
        return new ErrorMsgDto(HttpStatus.INTERNAL_SERVER_ERROR.value(), ex.getMessage(), request.getServletPath(), Instant.now());
    }
}
