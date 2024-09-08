package org.example.testxml.utils;

import org.w3c.dom.Document;

/**
 * Интерфейс работы с XPath, включая настройку пространства имен
 */
public interface XPathEvaluator {

    /**
     * Получение значения по ключу XML документа
     *
     * @param doc        документ DOM XML
     * @param expression ключ
     * @return значение
     * @throws Exception возможные ошибки
     */
    String evaluateXPath(Document doc, String expression) throws Exception;
}
