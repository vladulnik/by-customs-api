package by.customs.by_customs_api.dto;

public class ItemDto {

    private Long id;
    private String hsCode;
    private double value;
    private double weight;
    private String originCountry;

    public ItemDto() {}

    public ItemDto(Long id, String hsCode, double value, double weight, String originCountry) {
        this.id = id;
        this.hsCode = hsCode;
        this.value = value;
        this.weight = weight;
        this.originCountry = originCountry;
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
}
