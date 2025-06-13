package by.customs.by_customs_api.exception;

/**
 * Выбрасывается, когда код ТН ВЭД не проходит валидацию.
 */
public class InvalidHsCodeException extends BadRequestException {
    public InvalidHsCodeException(String hsCode) {
        super("Invalid HS code: " + hsCode);
    }
}
