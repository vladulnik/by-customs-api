package by.customs.by_customs_api.xml;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import java.time.LocalDate;
import java.util.List;

/**
 * Модель для генерации XML для декларации.
 */
@JacksonXmlRootElement(localName = "Declaration")
public class DeclarationXml {

    @JacksonXmlProperty(isAttribute = true, localName = "number")
    private String number;

    @JacksonXmlProperty(localName = "date")
    private LocalDate date;

    @JacksonXmlElementWrapper(localName = "Items")
    @JacksonXmlProperty(localName = "Item")
    private List<ItemXml> items;

    @JacksonXmlElementWrapper(localName = "Participants")
    @JacksonXmlProperty(localName = "Participant")
    private List<ParticipantXml> participants;

    @JacksonXmlProperty(localName = "Payments")
    private PaymentsXml payments;

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

    public List<ItemXml> getItems() {
        return items;
    }

    public void setItems(List<ItemXml> items) {
        this.items = items;
    }

    public List<ParticipantXml> getParticipants() {
        return participants;
    }

    public void setParticipants(List<ParticipantXml> participants) {
        this.participants = participants;
    }

    public PaymentsXml getPayments() {
        return payments;
    }

    public void setPayments(PaymentsXml payments) {
        this.payments = payments;
    }
}
