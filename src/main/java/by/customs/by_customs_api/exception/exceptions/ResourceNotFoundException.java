package by.customs.by_customs_api.exception.exceptions;

/**
 * Исключение выбрасывается, когда не найден ресурс по идентификатору.
 */
public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String message) {
        super(message);
    }
}
