package org.example.testxml.service.filler;

import org.example.testxml.dto.PartyIdentifierDTO;
import org.example.testxml.utils.*;
import org.springframework.stereotype.Component;
import org.w3c.dom.Document;

/**
 * Компонент реализующий интерфейс заполняения PartyIdentifierDTO DTO
 */
@Component
public class PartyIdentifierDTOFiller implements DtoFiller<PartyIdentifierDTO> {

    // Настройки маппинга и неймспейсинга.
    // В дальнейшем можно будет расширять и переопределять их для других реализаций
    private final XPathEvaluator xPathEvaluator;
    private final XPathMappings xPathMappings;

    public PartyIdentifierDTOFiller(XPathEvaluator xPathEvaluator, XPathMappings xPathMappings) {
        this.xPathEvaluator = xPathEvaluator;
        this.xPathMappings = xPathMappings;
    }

    @Override
    public void fill(PartyIdentifierDTO dto, Document document) throws Exception {

        dto.setOrganizationStandardName(xPathEvaluator.evaluateXPath(document, xPathMappings.getXPath("OrganizationStandardName")));
        dto.setEntityIdentifier(xPathEvaluator.evaluateXPath(document, xPathMappings.getXPath("EntityIdentifier")));

        if (dto.getOrganizationStandardName() == null) {
            fillIndividualData(dto, document);
        } else {
            fillTaxIdentifiers(dto, document);
        }

        dto.setAuthor(xPathEvaluator.evaluateXPath(document, xPathMappings.getXPath("Author")));
        dto.setNationalIdNumber(xPathEvaluator.evaluateXPath(document, xPathMappings.getXPath("NationalIdNumber")));
        dto.setPostalAddressText(xPathEvaluator.evaluateXPath(document, xPathMappings.getXPath("PostalAddressText")));
        dto.setType(xPathEvaluator.evaluateXPath(document, xPathMappings.getXPath("Type")));
        dto.setElectronicSignatureDate(xPathEvaluator.evaluateXPath(document, xPathMappings.getXPath("Date")));
        // TODO добавить position (должность)
    }

    /**
     * Заполняем ИНН у организации и физ. лица в объекте DTO
     *
     * @param dto      объекте DTO
     * @param document документ XML
     * @throws Exception возможные ошибки
     */
    private void fillTaxIdentifiers(PartyIdentifierDTO dto, Document document) throws Exception {
        dto.setOrganizationTaxIdentifier(xPathEvaluator.evaluateXPath(document, xPathMappings.getXPath("OrganizationTaxIdentifier")));
        dto.setIndividualTaxIdentifier(xPathEvaluator.evaluateXPath(document, xPathMappings.getXPath("IndividualTaxIdentifier")));
    }

    /**
     * Заполняем ИНН у физ лица в объекте DTO
     *
     * @param dto      объект dto
     * @param document документ XML
     * @throws Exception возможные ошибки
     */
    public void fillIndividualData(PartyIdentifierDTO dto, Document document) throws Exception {
        dto.setIndividualTaxIdentifier(xPathEvaluator.evaluateXPath(document, xPathMappings.getXPath("OrganizationTaxIdentifier")));
        dto.setOrganizationTaxIdentifier(null);  // Очистка организационного ИНН
    }

}
