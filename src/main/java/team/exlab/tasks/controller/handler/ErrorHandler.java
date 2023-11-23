package team.exlab.tasks.controller.handler;

import io.jsonwebtoken.ExpiredJwtException;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import team.exlab.tasks.service.valildation.ValidationErrorResponse;
import team.exlab.tasks.service.valildation.Violation;

@ControllerAdvice
public class ErrorHandler {
    /*@ResponseBody
    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ValidationErrorResponse onConstraintValidationException(ConstraintViolationException e) {
        var violations = e.getConstraintViolations()
                .stream()
                .map(violation -> new Violation(
                        violation.getPropertyPath().toString(),
                        violation.getMessage()
                )).toList();
        return new ValidationErrorResponse(violations);
    }*/

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ValidationErrorResponse onMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        var violations = e.getBindingResult().getFieldErrors()
                .stream()
                .map(error -> new Violation(
                        error.getField(),
                        error.getDefaultMessage()
                )).toList();
        return new ValidationErrorResponse(violations);
    }

    @ExceptionHandler(ExpiredJwtException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ResponseBody
    public void onExpiredJwtException(ExpiredJwtException e) {

    }
}
