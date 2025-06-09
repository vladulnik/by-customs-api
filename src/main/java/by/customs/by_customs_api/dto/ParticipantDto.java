package by.customs.by_customs_api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class ParticipantDto {
    private Long id;

    @NotBlank(message = "Имя участника обязательно")
    private String name;

    @NotBlank(message = "Адрес участника обязателен")
    private String address;

    @NotNull(message = "ID декларации обязателен")
    private Long declarationId;

    public ParticipantDto() {
    }

    public ParticipantDto(Long id, String name, String address, Long declarationId) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.declarationId = declarationId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Long getDeclarationId() {
        return declarationId;
    }

    public void setDeclarationId(Long declarationId) {
        this.declarationId = declarationId;
    }
}
