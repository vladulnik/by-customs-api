package by.customs.by_customs_api.service;

import by.customs.by_customs_api.dto.DeclarationDto;
import by.customs.by_customs_api.dto.ItemDto;
import by.customs.by_customs_api.dto.ParticipantDto;
import by.customs.by_customs_api.dto.PaymentDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

class XmlDeclarationServiceTest {

    private XmlDeclarationService xmlService;

    @BeforeEach
    void init() {
        xmlService = new XmlDeclarationService();
    }

    @Test
    void toXmlShouldContainAllSections() throws Exception {
        DeclarationDto decl = new DeclarationDto();
        decl.setNumber("TEST-123");
        decl.setDate(LocalDate.of(2025, 6, 15));

        ItemDto item = new ItemDto();
        item.setHsCode("HS0001");
        item.setValue(123.45);
        item.setWeight(1.2);
        item.setOriginCountry("CN");
        item.setDeclarationId(null);

        ParticipantDto part = new ParticipantDto();
        part.setName("ACME Corp");
        part.setAddress("ул. Пример, 1");
        part.setDeclarationId(null);

        PaymentDto pay = new PaymentDto();
        pay.setDuty(10.0);
        pay.setVat(2.0);
        pay.setExcise(0.5);
        pay.setDeclarationId(null);

        String xml = xmlService.toXml(
                decl,
                List.of(item),
                List.of(part),
                List.of(pay)
        );

        assertTrue(xml.contains("number=\"TEST-123\""), "Должен быть атрибут number=\"TEST-123\"");
        assertTrue(xml.contains("<date>2025-06-15</date>"), "Должна быть дата декларации");
        assertTrue(xml.contains("<Items>"), "Должен быть контейнер <Items>");
        assertTrue(xml.contains("<HSCode>HS0001</HSCode>"), "Должен быть тег <HSCode>");
        assertTrue(xml.contains("<Participants>"), "Должен быть контейнер <Participants>");
        assertTrue(xml.contains("<Name>ACME Corp</Name>"), "Должен быть тег <Name>");
        assertTrue(xml.contains("<Payments>"), "Должен быть контейнер <Payments>");
        assertTrue(xml.contains("<Duty>10.0</Duty>"), "Должен быть тег <Duty>");
    }
}
