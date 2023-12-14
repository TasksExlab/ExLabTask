package team.exlab.tasks.service.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public abstract class BaseException extends RuntimeException {
    private ApiError apiError;
    private HttpStatus httpStatus;

    public BaseException(ApiError apiError, HttpStatus httpStatus) {
        super(apiError.getDescription());
        this.apiError = apiError;
        this.httpStatus = httpStatus;
    }
}
