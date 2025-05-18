package xml;

import jakarta.xml.bind.annotation.*;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import lombok.Data;

import java.util.List;
import java.util.Map;
import java.util.Set;

@Data
@XmlRootElement(name = "person")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = { "id", "name", "hobbies", "tags", "attributes", "address" })
public class PersonDTO {
    private int id;
    private String name;

    @XmlElementWrapper(name = "hobbies")
    @XmlElement(name = "hobby")
    private List<String> hobbies;

    @XmlElementWrapper(name = "tags")
    @XmlElement(name = "tag")
    private Set<String> tags;

    @XmlJavaTypeAdapter(MapAdapter.class)
    private Map<String, String> attributes;

    private AddressDTO address;

}
