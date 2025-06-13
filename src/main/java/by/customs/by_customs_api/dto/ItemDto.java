package by.customs.by_customs_api.dto;

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
    private String hsCode;
    private Double value;
    private Double weight;
    private String originCountry;
    private Long declarationId;
}
