package team.exlab.tasks.service.exception;

import org.springframework.http.HttpStatus;

public class NotFoundException extends BaseException {
    public NotFoundException(String errorCode, String description) {
        super(ApiError.builder()
                        .errorCode(errorCode)
                        .description(description)
                        .build(),
                HttpStatus.NOT_FOUND);
    }
}
