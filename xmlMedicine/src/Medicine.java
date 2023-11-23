import jakarta.xml.bind.annotation.*;

import java.util.Comparator;
import java.util.List;

@XmlRootElement(name = "Medicine")
@XmlAccessorType(XmlAccessType.FIELD)
public class Medicine {
    public static Comparator<Medicine> NameComparator = Comparator.comparing(Medicine::getName);

    // Доданий метод для порівняння за назвою препарату
    public static Comparator<Medicine> getNameComparator() {
        return NameComparator;
    }
    @XmlAttribute
    private int id;

    private String Name;
    private String Pharm;
    private String Group;

    @XmlElementWrapper(name = "Analogs")
    @XmlElement(name = "Analog")
    private List<String> Analogs;

    @XmlElementWrapper(name = "Versions")
    @XmlElement(name = "Version")
    private List<Version> Versions;

    public Medicine() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getPharm() {
        return Pharm;
    }

    public void setPharm(String pharm) {
        Pharm = pharm;
    }

    public String getGroup() {
        return Group;
    }

    public void setGroup(String group) {
        Group = group;
    }

    // Constructors, getters, and setters
    // ...
}
