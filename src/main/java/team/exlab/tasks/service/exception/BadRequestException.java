package team.exlab.tasks.service.exception;

import org.springframework.http.HttpStatus;

public class BadRequestException extends BaseException {
    public BadRequestException(String errorCode, String description) {
        super(ApiError.builder()
                        .errorCode(errorCode)
                        .description(description)
                        .build(),
                HttpStatus.BAD_REQUEST);
    }
}
