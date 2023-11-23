import jakarta.xml.bind.annotation.XmlAttribute;

public class Certificate {
    @XmlAttribute
    private String number;
    @XmlAttribute
    private String issueDate;
    @XmlAttribute
    private String expiryDate;
    @XmlAttribute
    private String registeringAuthority;

    // Constructors, getters, and setters
    // ...
}
