import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.XMLConstants;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        try {
            File inputFile = new File("C:\\Users\\hryhorii\\Downloads\\multi-choice-simple\\xmlMedicine\\src\\drugs.xml");
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();
            CustomHandler customHandler = new CustomHandler();
            saxParser.parse(inputFile, customHandler);
            List<Medicine> medicines = customHandler.getMedicines();

           System.out.println("Список ліків");
            for (Medicine v:
                 medicines) {
                System.out.println(v.getName());
            }
            System.out.println("Cписок після сортування за назвою");
            medicines.sort(Medicine.getNameComparator());
            for (Medicine v:
                    medicines) {
                System.out.println(v.getName());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        checkDocByXSD();
    }

    public static void checkDocByXSD()
    {
        try {
            SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            Schema schema = factory.newSchema(new File("C:\\Users\\hryhorii\\Downloads\\multi-choice-simple\\xmlMedicine\\src\\drugsdetails.xsd")); // Підставте шлях до вашого XSD файлу

            Validator validator = schema.newValidator();
            Source source = new StreamSource(new File("C:\\Users\\hryhorii\\Downloads\\multi-choice-simple\\xmlMedicine\\src\\drugs.xml")); // Підставте шлях до вашого XML файлу

            validator.validate(source);
            System.out.println("XML документ відповідає XSD схемі.");
        } catch (Exception e) {
            System.out.println("XML документ не відповідає XSD схемі.");
            e.printStackTrace();
        }
    }
    }

class CustomHandler extends DefaultHandler {
    private List<Medicine> medicines;
    private Medicine currentMedicine;
    private StringBuilder data;

    public List<Medicine> getMedicines() {
        return medicines;
    }

    @Override
    public void startDocument() throws SAXException {
        medicines = new ArrayList<>();
        data = new StringBuilder();
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if (qName.equalsIgnoreCase("Medicine")) {
            currentMedicine = new Medicine();
            currentMedicine.setId(Integer.parseInt(attributes.getValue("id")));
        } else if (qName.equalsIgnoreCase("Name") || qName.equalsIgnoreCase("Pharm")
                || qName.equalsIgnoreCase("Group")) {
            data.setLength(0);
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        data.append(new String(ch, start, length).trim());
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if (qName.equalsIgnoreCase("Medicine")) {
            medicines.add(currentMedicine);
        } else if (qName.equalsIgnoreCase("Name")) {
            currentMedicine.setName(data.toString());
        } else if (qName.equalsIgnoreCase("Pharm")) {
            currentMedicine.setPharm(data.toString());
        } else if (qName.equalsIgnoreCase("Group")) {
            currentMedicine.setGroup(data.toString());
        }
    }
}
