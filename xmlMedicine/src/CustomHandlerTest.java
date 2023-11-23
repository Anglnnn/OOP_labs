import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CustomHandlerTest {

    @Test
    public void testSAXHandler() {
        CustomHandler handler = new CustomHandler();
        String xmlContent = "<Medicines><Medicine id='1'><Name>Ibuprofen</Name><Pharm>Pfizer</Pharm><Group>Analgesic</Group></Medicine></Medicines>";

        try {
            handler.startDocument();
            handler.startElement(null, null, "Medicine", null);
            handler.startElement(null, null, "Name", null);
            handler.characters(xmlContent.toCharArray(), 0, xmlContent.length());
            handler.endElement(null, null, "Name");
            handler.startElement(null, null, "Pharm", null);
            handler.characters(xmlContent.toCharArray(), 0, xmlContent.length());
            handler.endElement(null, null, "Pharm");
            handler.startElement(null, null, "Group", null);
            handler.characters(xmlContent.toCharArray(), 0, xmlContent.length());
            handler.endElement(null, null, "Group");
            handler.endElement(null, null, "Medicine");
            handler.endDocument();

            List<Medicine> medicines = handler.getMedicines();
            assertEquals(1, medicines.size());
            assertEquals("Ibuprofen", medicines.get(0).getName());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
