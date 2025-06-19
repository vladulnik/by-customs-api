package by.customs.by_customs_api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO для товарной позиции
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ItemDto {
    private Long id;
    @NotBlank(message = "hsCode is required")
    private String hsCode;
    private Double value;
    private Double weight;
    private String originCountry;
    @NotNull(message = "declarationId is required")
    private Long declarationId;
}
