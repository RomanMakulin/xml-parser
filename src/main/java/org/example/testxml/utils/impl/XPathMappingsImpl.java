package org.example.testxml.utils.impl;

import org.example.testxml.utils.XPathMappings;
import org.springframework.stereotype.Service;

import java.util.HashMap;

// В дальнейшем можно будет расширять и переопределять их для других реализаций
@Service
public class XPathMappingsImpl implements XPathMappings {

    /**
     * Ключ-значение, где ключ - кастомный идентификатор,
     * а значение - атрибут в XML чтобы получать данные
     */
    private final HashMap<String, String> xPathMap;

    public XPathMappingsImpl() {
        this.xPathMap = generateMapWithPaths();
    }

    /**
     * Генерация HashMap. Задаем ключи и атрибуты в XML
     *
     * @return объект hashMap, заполненный данными
     */
    private HashMap<String, String> generateMapWithPaths() {
        HashMap<String, String> paths = new HashMap<>();

        paths.put("Type", "//rucom:SignatureKind");
        paths.put("OrganizationStandardName", "//com:OrganizationStandardName");
        paths.put("EntityIdentifier", "//com:PartyIdentifier[@com:officeCode='RU' and @com:partyIdentifierCategory='Entity identifier']");
        paths.put("OrganizationTaxIdentifier", "//tmk:Applicant//com:PartyIdentifier[@com:officeCode='RU' and @com:partyIdentifierCategory='Tax identifier']");
        paths.put("IndividualTaxIdentifier", "//com:Representative//com:PartyIdentifier[@com:officeCode='RU' and @com:partyIdentifierCategory='Tax identifier']");
        paths.put("Author", "//com:PersonFullName");
        paths.put("NationalIdNumber", "//com:PartyIdentifier[@com:officeCode='RU' and @com:partyIdentifierCategory='National identification number']");
        paths.put("PostalAddressText", "//com:PostalAddressText");
        paths.put("Date", "//com_v3:ElectronicSignatureDate");
        // TODO добавить position (должность)

        return paths;
    }

    @Override
    public String getXPath(String key) {
        return xPathMap.get(key);
    }
}
