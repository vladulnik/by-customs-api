package by.customs.by_customs_api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

/**
 * DTO для товарной позиции.
 */
public class ItemDto {
    private Long id;

    @NotBlank(message = "Код ТН ВЭД обязателен")
    private String hsCode;

    @PositiveOrZero(message = "Стоимость должна быть неотрицательной")
    private double value;

    @Positive(message = "Вес должен быть положительным")
    private double weight;

    @NotBlank(message = "Страна происхождения обязательна")
    private String originCountry;

    @NotNull(message = "ID декларации обязателен")
    private Long declarationId;

    public ItemDto() {
    }

    public ItemDto(Long id, String hsCode, double value, double weight, String originCountry, Long declarationId) {
        this.id = id;
        this.hsCode = hsCode;
        this.value = value;
        this.weight = weight;
        this.originCountry = originCountry;
        this.declarationId = declarationId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getHsCode() {
        return hsCode;
    }

    public void setHsCode(String hsCode) {
        this.hsCode = hsCode;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public String getOriginCountry() {
        return originCountry;
    }

    public void setOriginCountry(String originCountry) {
        this.originCountry = originCountry;
    }

    public Long getDeclarationId() {
        return declarationId;
    }

    public void setDeclarationId(Long declarationId) {
        this.declarationId = declarationId;
    }
}
