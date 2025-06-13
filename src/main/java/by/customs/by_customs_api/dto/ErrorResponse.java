package by.customs.by_customs_api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Структура тела ответа при ошибках.
 */
@Data
@AllArgsConstructor
public class ErrorResponse {
    private String code;
    private String message;
}
