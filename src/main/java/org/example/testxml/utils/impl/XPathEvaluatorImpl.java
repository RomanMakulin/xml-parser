package org.example.testxml.utils.impl;

import org.example.testxml.utils.XPathEvaluator;
import org.springframework.stereotype.Component;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import javax.xml.namespace.NamespaceContext;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;
import java.util.*;

// В дальнейшем можно будет расширять и переопределять их для других реализаций
@Component
public class XPathEvaluatorImpl<T> implements XPathEvaluator {

    private final XPath xpath;

    /**
     * Настройка пространства имен чтобы корректно получать данные из XML документа
     */
    public XPathEvaluatorImpl() {
        XPathFactory xPathFactory = XPathFactory.newInstance();
        this.xpath = xPathFactory.newXPath();
        this.xpath.setNamespaceContext(new NamespaceContext() {
            @Override
            public String getNamespaceURI(String prefix) {
                switch (prefix) {
                    case "com":
                        return "http://www.wipo.int/standards/XMLSchema/ST96/Common/1.0.4"; // Основное пространство имен
                    case "com_v3":
                        return "http://www.wipo.int/standards/XMLSchema/ST96/Common/1.0.3"; // Альтернативное пространство имен
                    case "tmk":
                        return "http://www.wipo.int/standards/XMLSchema/ST96/Trademark/1.0.3";
                    case "tns":
                        return "http://ws.rupto.ru/smev3/gosuslugi/clients/tm_registation/1.0.4";
                    case "rucom":
                        return "urn:ru:rupto:common/1.0.0";
                    default:
                        return null;
                }
            }

            @Override
            public String getPrefix(String uri) {
                switch (uri) {
                    case "http://www.wipo.int/standards/XMLSchema/ST96/Common/1.0.4":
                        return "com";
                    case "http://www.wipo.int/standards/XMLSchema/ST96/Common/1.0.3":
                        return "com_v3";
                    default:
                        return null;
                }
            }

            @Override
            public Iterator<String> getPrefixes(String uri) {
                return Collections.singleton(getPrefix(uri)).iterator();
            }
        });
    }

    @Override
    public String evaluateXPath(Document doc, String expression) throws Exception {
        XPathExpression expr = xpath.compile(expression);
        NodeList nodeList = (NodeList) expr.evaluate(doc, XPathConstants.NODESET);
        if (nodeList.getLength() > 0) {
            return nodeList.item(0).getTextContent().trim();
        }
        return null;
    }

    // Новый метод для возврата списка всех совпадающих узлов по XPath-запросу
    public List<String> evaluateXPathForList(Document doc, String expression) throws Exception {
        XPathExpression expr = xpath.compile(expression);
        NodeList nodeList = (NodeList) expr.evaluate(doc, XPathConstants.NODESET);
        List<String> results = new ArrayList<>();
        for (int i = 0; i < nodeList.getLength(); i++) {
            results.add(nodeList.item(i).getTextContent().trim());
        }
        return results;
    }
}
