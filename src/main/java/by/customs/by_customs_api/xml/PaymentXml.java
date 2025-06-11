package by.customs.by_customs_api.xml;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

/**
 * Модель для элемента Payment при генерации XML.
 */
public class PaymentXml {

    @JacksonXmlProperty(localName = "Duty")
    private double duty;

    @JacksonXmlProperty(localName = "VAT")
    private double vat;

    @JacksonXmlProperty(localName = "Excise")
    private double excise;

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
}
