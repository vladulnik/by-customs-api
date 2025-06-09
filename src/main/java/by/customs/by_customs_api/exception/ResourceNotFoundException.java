package by.customs.by_customs_api.exception;

/**
 * Бросается, когда запрашиваемая сущность не найдена в базе.
 */
public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException(String message) {
        super(message);
    }
}
