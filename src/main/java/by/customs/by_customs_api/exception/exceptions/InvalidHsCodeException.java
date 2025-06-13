package by.customs.by_customs_api.exception.exceptions;

/**
 * Исключение выбрасывается при попытке передать некорректный код ТН ВЭД.
 */
public class InvalidHsCodeException extends RuntimeException {
    public InvalidHsCodeException(String message) {
        super(message);
    }
}
