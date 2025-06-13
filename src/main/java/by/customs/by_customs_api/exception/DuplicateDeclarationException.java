package by.customs.by_customs_api.exception;

/**
 * Выбрасывается при попытке создать декларацию с уже существующим номером.
 */
public class DuplicateDeclarationException extends BadRequestException {
    public DuplicateDeclarationException(String declarationNumber) {
        super("Declaration number already exists: " + declarationNumber);
    }
}
