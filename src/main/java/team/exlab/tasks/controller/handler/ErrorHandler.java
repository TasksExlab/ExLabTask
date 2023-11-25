package team.exlab.tasks.controller.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import team.exlab.tasks.service.exception.ApiError;
import team.exlab.tasks.service.exception.BaseException;
import team.exlab.tasks.service.validation.ValidationErrorResponse;
import team.exlab.tasks.service.validation.Violation;

import java.util.Objects;

@RestControllerAdvice
public class ErrorHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ValidationErrorResponse> onMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        var bindingResult = e.getBindingResult();
        var violations = new java.util.ArrayList<>(bindingResult.getFieldErrors()
                .stream()
                .map(error -> new Violation(
                        error.getField(),
                        error.getDefaultMessage()
                )).toList());

        if (bindingResult.hasGlobalErrors()) {
            violations.add(new Violation(
                            "object",
                            Objects.requireNonNull(bindingResult.getGlobalError()).getDefaultMessage()
                    )
            );
        }
        return new ResponseEntity<>(
                new ValidationErrorResponse(violations),
                HttpStatus.BAD_REQUEST
        );
    }

    @ExceptionHandler(BaseException.class)
    ResponseEntity<ApiError> handleBaseException(BaseException ex) {
        return new ResponseEntity<>(ex.getApiError(), ex.getHttpStatus());
    }
}
