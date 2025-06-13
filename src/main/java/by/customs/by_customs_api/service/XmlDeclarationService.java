package by.customs.by_customs_api.service;

import by.customs.by_customs_api.dto.DeclarationDto;
import by.customs.by_customs_api.dto.ItemDto;
import by.customs.by_customs_api.dto.ParticipantDto;
import by.customs.by_customs_api.dto.PaymentDto;
import by.customs.by_customs_api.exception.exceptions.XmlGenerationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import javax.xml.XMLConstants;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.StringReader;
import java.util.List;

/**
 * Сервис для генерации XML-декларации с XSD-валидацией.
 */
@Slf4j
@Service
public class XmlDeclarationService {

    /**
     * Генерирует XML-декларацию по данным и валидирует её по XSD.
     * @param decl декларация
     * @param items товарные позиции
     * @param parts участники
     * @param pays платежи
     * @return XML-строка
     */
    public String generateXml(DeclarationDto decl,
                              List<ItemDto> items,
                              List<ParticipantDto> parts,
                              List<PaymentDto> pays) {
        try {
            StringBuilder xml = new StringBuilder();
            xml.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
            xml.append("<Declaration ")
                    .append("xmlns=\"urn:by.customs:declaration\" ")
                    .append("number=\"").append(escape(decl.getNumber())).append("\">\n");
            xml.append("  <date>").append(decl.getDate()).append("</date>\n");

            xml.append("  <Items>\n");
            for (ItemDto i : items) {
                xml.append("    <Item>\n")
                        .append("      <HSCode>").append(escape(i.getHsCode())).append("</HSCode>\n")
                        .append("      <Value>").append(i.getValue()).append("</Value>\n")
                        .append("      <Weight>").append(i.getWeight()).append("</Weight>\n")
                        .append("      <OriginCountry>").append(escape(i.getOriginCountry()))
                        .append("</OriginCountry>\n")
                        .append("    </Item>\n");
            }
            xml.append("  </Items>\n");

            xml.append("  <Participants>\n");
            for (ParticipantDto p : parts) {
                xml.append("    <Participant>\n")
                        .append("      <Name>").append(escape(p.getName())).append("</Name>\n")
                        .append("      <Address>").append(escape(p.getAddress())).append("</Address>\n")
                        .append("    </Participant>\n");
            }
            xml.append("  </Participants>\n");

            xml.append("  <Payments>\n");
            for (PaymentDto p : pays) {
                xml.append("    <Payment>\n")
                        .append("      <Duty>").append(p.getDuty()).append("</Duty>\n")
                        .append("      <VAT>").append(p.getVat()).append("</VAT>\n")
                        .append("      <Excise>").append(p.getExcise()).append("</Excise>\n")
                        .append("    </Payment>\n");
            }
            xml.append("  </Payments>\n");

            xml.append("</Declaration>");

            String xmlString = xml.toString();
            validateByXsd(xmlString, "xsd/dt1.xsd");
            return xmlString;

        } catch (Exception e) {
            log.error("Ошибка генерации или валидации XML", e);
            throw new XmlGenerationException("Ошибка генерации или валидации XML", e);
        }
    }

    private void validateByXsd(String xml, String xsdPath) throws Exception {
        SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
        Schema schema = factory.newSchema(new ClassPathResource(xsdPath).getURL());
        Validator validator = schema.newValidator();
        validator.validate(new StreamSource(new StringReader(xml)));
    }

    private String escape(String s) {
        if (s == null) return "";
        return s
                .replace("&", "&amp;")
                .replace("<", "&lt;")
                .replace(">", "&gt;")
                .replace("\"", "&quot;")
                .replace("'", "&apos;");
    }
}
