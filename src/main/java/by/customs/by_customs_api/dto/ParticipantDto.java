package by.customs.by_customs_api.dto;

/**
 * DTO для участника декларации.
 */
public class ParticipantDto {
    private Long id;
    private String name;
    private String address;
    private Long declarationId;

    public ParticipantDto() {}

    public ParticipantDto(Long id, String name, String address, Long declarationId) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.declarationId = declarationId;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }

    public Long getDeclarationId() { return declarationId; }
    public void setDeclarationId(Long declarationId) { this.declarationId = declarationId; }
}
