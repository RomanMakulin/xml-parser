package org.example.testxml;

import org.example.testxml.dto.PartyIdentifierDTO;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.nio.file.Files;
import java.nio.file.Paths;

@SpringBootApplication
public class TestXmlApplication implements CommandLineRunner {

    private final XMLParsingService<PartyIdentifierDTO> xmlParsingService;  // Автоматическая инъекция сервиса

    public TestXmlApplication(XMLParsingService<PartyIdentifierDTO> XMLParsingService) {
        this.xmlParsingService = XMLParsingService;
    }

    public static void main(String[] args) {
        SpringApplication.run(TestXmlApplication.class, args);
    }

    @Override
    public void run(String... args) {
        try {
            byte[] xmlBytes = loadFileAsBytes("application-ykep.xml");
//            byte[] xmlBytes = loadFileAsBytes("application1.xml");

            PartyIdentifierDTO dto = new PartyIdentifierDTO();
            dto = xmlParsingService.extractData(xmlBytes, dto);

            System.out.println(dto);
        } catch (Exception e) {
            e.printStackTrace(); // Вывод стека ошибки
        }
    }

    /**
     * Предположим, что нам нужен массив байт
     *
     * @param filePath путь к файлу (физическому)
     * @return массив байт
     * @throws Exception возможные ошибки
     */
    private byte[] loadFileAsBytes(String filePath) throws Exception {
        return Files.readAllBytes(Paths.get(filePath));  // Чтение файла в массив байт
    }

}
