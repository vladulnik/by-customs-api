package by.customs.by_customs_api.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO для платежа
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PaymentDto {
    private Long id;
    private Double duty;
    private Double vat;
    private Double excise;
    @NotNull(message = "declarationId is required")
    private Long declarationId;
}
