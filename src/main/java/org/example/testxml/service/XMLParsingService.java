package org.example.testxml.service;

/**
 * Интерфейс парсинга XML массива байт в нужный DTO
 *
 * @param <T> объект DTO
 */
public interface XMLParsingService<T> {
    /**
     * Извлечь данные в DTO из массива байт XML
     *
     * @param xmlBytes           массив байт XML
     * @param dataTransferObject необходимый объект DTO
     * @return собранный объект DTO
     * @throws Exception возможные ошибки
     */
    T extractData(byte[] xmlBytes, T dataTransferObject) throws Exception;
}
