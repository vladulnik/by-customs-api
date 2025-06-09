package by.customs.by_customs_api.dto;

/**
 * DTO для представления таможенного платежа.
 */
public class PaymentDto {

    private Long id;
    private double duty;
    private double vat;
    private double excise;
    private Long declarationId;

    public PaymentDto() {
    }

    public PaymentDto(Long id, double duty, double vat, double excise, Long declarationId) {
        this.id = id;
        this.duty = duty;
        this.vat = vat;
        this.excise = excise;
        this.declarationId = declarationId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getDuty() {
        return duty;
    }

    public void setDuty(double duty) {
        this.duty = duty;
    }

    public double getVat() {
        return vat;
    }

    public void setVat(double vat) {
        this.vat = vat;
    }

    public double getExcise() {
        return excise;
    }

    public void setExcise(double excise) {
        this.excise = excise;
    }

    public Long getDeclarationId() {
        return declarationId;
    }

    public void setDeclarationId(Long declarationId) {
        this.declarationId = declarationId;
    }
}
