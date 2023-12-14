package team.exlab.tasks.controller.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import team.exlab.tasks.service.exception.ApiError;
import team.exlab.tasks.service.exception.BaseException;
import team.exlab.tasks.service.validation.ValidationErrorResponse;
import team.exlab.tasks.service.validation.Violation;

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
        return new ResponseEntity<>(
                new ValidationErrorResponse(violations),
                HttpStatus.BAD_REQUEST
        );
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ApiError> onMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException e) {
        return new ResponseEntity<>(
                ApiError.builder()
                        .errorCode("id.format.invalid")
                        .description("Неверный формат идентификатора")
                        .build(),
                HttpStatus.BAD_REQUEST
        );
    }

    @ExceptionHandler(BaseException.class)
    ResponseEntity<ApiError> handleBaseException(BaseException ex) {
        return new ResponseEntity<>(ex.getApiError(), ex.getHttpStatus());
    }
}
