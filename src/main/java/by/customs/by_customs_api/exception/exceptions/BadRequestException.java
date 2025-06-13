package by.customs.by_customs_api.exception.exceptions;

/**
 * Исключение выбрасывается при ошибке валидации или неверных данных от клиента.
 */
public class BadRequestException extends RuntimeException {
    public BadRequestException(String message) {
        super(message);
    }
}
