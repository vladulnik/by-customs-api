package by.customs.by_customs_api.exception.exceptions;

/**
 * Исключение выбрасывается при попытке создать дубликат декларации.
 */
public class DuplicateDeclarationException extends RuntimeException {
    public DuplicateDeclarationException(String message) {
        super(message);
    }
}
