package by.customs.by_customs_api.util;

import by.customs.by_customs_api.dto.*;
import by.customs.by_customs_api.service.XmlDeclarationService;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.List;

public class XmlGenerationMain {
    public static void main(String[] args) throws Exception {
        XmlDeclarationService xmlService = new XmlDeclarationService();

        // Создаём тестовые данные, как в юнит-тесте
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

        // Генерируем XML
        String xml = xmlService.toXml(
                decl,
                List.of(item),
                List.of(part),
                List.of(pay)
        );

        // Сохраняем XML в файл
        Files.write(Paths.get("test.xml"), xml.getBytes(StandardCharsets.UTF_8));
        System.out.println("XML сохранён как test.xml!");
    }
}
