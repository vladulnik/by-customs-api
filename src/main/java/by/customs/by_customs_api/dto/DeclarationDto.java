package by.customs.by_customs_api.dto;

import java.time.LocalDate;

public class DeclarationDto {

    private Long id;
    private String number;
    private LocalDate date;

    public DeclarationDto() {}

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
