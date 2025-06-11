package by.customs.by_customs_api.xml;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

import java.util.List;

/**
 * Обёртка для списка платежей в XML-декларации.
 */
@JacksonXmlRootElement(localName = "Payments")
public class PaymentsXml {

    /**
     * Список элементов PaymentXml.
     * Аннотация @JacksonXmlElementWrapper(useWrapping = false)
     * сообщает, что каждый элемент списка
     * должен сериализоваться как отдельный тег <Payment>,
     * без дополнительного вложенного контейнера.
     */
    @JacksonXmlElementWrapper(useWrapping = false)
    @JacksonXmlProperty(localName = "Payment")
    private List<PaymentXml> payments;

    public List<PaymentXml> getPayments() {
        return payments;
    }

    public void setPayments(List<PaymentXml> payments) {
        this.payments = payments;
    }
}
