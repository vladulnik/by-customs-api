package by.customs.by_customs_api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * DTO для декларации
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DeclarationDto {
    private Long id;
    private String number;
    private LocalDate date;
    @Builder.Default
    private List<ItemDto> items = new ArrayList<>();
    @Builder.Default
    private List<ParticipantDto> participants = new ArrayList<>();
    @Builder.Default
    private List<PaymentDto> payments = new ArrayList<>();
}
