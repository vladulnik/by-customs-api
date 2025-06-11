package by.customs.by_customs_api.xml;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

/**
 * Модель для элемента Item при генерации XML.
 */
public class ItemXml {

    @JacksonXmlProperty(localName = "HSCode")
    private String hsCode;

    @JacksonXmlProperty(localName = "Value")
    private double value;

    @JacksonXmlProperty(localName = "Weight")
    private double weight;

    @JacksonXmlProperty(localName = "OriginCountry")
    private String originCountry;

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
