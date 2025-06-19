package by.customs.by_customs_api.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO для участника ВЭД
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ParticipantDto {
    private Long id;
    private String name;
    private String address;
    @NotNull(message = "declarationId is required")
    private Long declarationId;
}
