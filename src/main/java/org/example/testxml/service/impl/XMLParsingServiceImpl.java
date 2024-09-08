package org.example.testxml.service.impl;

import org.example.testxml.service.XMLParsingService;
import org.example.testxml.service.filler.PartyIdentifierDTOFiller;
import org.example.testxml.utils.XmlDocumentLoader;
import org.example.testxml.dto.PartyIdentifierDTO;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;

/**
 * Реализация интерфейса парсинга XML файла в DTO из массива байт
 *
 * @param <T> необходимый объект DTO
 */
@Service
public class XMLParsingServiceImpl<T> implements XMLParsingService<T> {

    private final XmlDocumentLoader xmlDocumentLoader;
    private final PartyIdentifierDTOFiller partyIdentifierDTOFiller;

    public XMLParsingServiceImpl(XmlDocumentLoader xmlDocumentLoader,
                                 PartyIdentifierDTOFiller partyIdentifierDTOFiller) {
        this.xmlDocumentLoader = xmlDocumentLoader;
        this.partyIdentifierDTOFiller = partyIdentifierDTOFiller;
    }

    @Override
    public T extractData(byte[] xmlBytes, T dataTransferObject) throws Exception {
        Document document = xmlDocumentLoader.loadDocument(xmlBytes);
        return fillDataTransferObject(document, dataTransferObject);
    }

    /**
     * Вспомогательный метод для заполнения DTO разных типов.
     * Выбирает нужный класс dto в зависимости что передадим
     *
     * @param document           документ с XML
     * @param dataTransferObject объект DTO
     * @return собранный обект DTO
     * @throws Exception возможные ошибки
     */
    private T fillDataTransferObject(Document document, T dataTransferObject) throws Exception {
        if (dataTransferObject instanceof PartyIdentifierDTO) {
            partyIdentifierDTOFiller.fill((PartyIdentifierDTO) dataTransferObject, document);
        }
        // Дополнительные блоки if для других типов DTO

        // Возвращаем тот же объект, который был передан в метод
        return dataTransferObject;
    }
}
