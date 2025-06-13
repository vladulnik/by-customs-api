package by.customs.by_customs_api.exception;

/**
 * Базовое исключение для ошибок валидации или бизнес-правил.
 */
public class BadRequestException extends RuntimeException {
    public BadRequestException(String message) {
        super(message);
    }
    public BadRequestException(String message, Throwable cause) {
        super(message, cause);
    }
}
