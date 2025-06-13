package by.customs.by_customs_api.exception.exceptions;

/**
 * Исключение выбрасывается при ошибках генерации или сериализации XML-декларации.
 */
public class XmlGenerationException extends RuntimeException {
    public XmlGenerationException(String message) {
        super(message);
    }

    public XmlGenerationException(String message, Throwable cause) {
        super(message, cause);
    }
}
