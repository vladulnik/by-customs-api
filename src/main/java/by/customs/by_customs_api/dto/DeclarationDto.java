package by.customs.by_customs_api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public class DeclarationDto {
    private Long id;

    @NotBlank(message = "Номер декларации обязателен")
    private String number;

    @NotNull(message = "Дата декларации обязательна")
    private LocalDate date;

    public DeclarationDto() {
    }

    public DeclarationDto(Long id, String number, LocalDate date) {
        this.id = id;
        this.number = number;
        this.date = date;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
