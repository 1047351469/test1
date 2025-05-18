package xml;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestXml {
    public static void main(String[] args) throws Exception {
        PersonDTO person = new PersonDTO();
        person.setId(1);
        person.setName("Alice");

        person.setHobbies(Arrays.asList("Reading", "Coding"));
        person.setTags(new HashSet<>(Arrays.asList("engineer", "blogger")));

        Map<String, String> attr = new HashMap<>();
        attr.put("lang", "Java");
        attr.put("level", "senior");
        person.setAttributes(attr);

        AddressDTO addr = new AddressDTO();
        addr.setCity("Tokyo");
        addr.setZip("100-0001");
        person.setAddress(addr);

        // DTO → XML
        String xml = XmlUtils.serialize(person);
        System.out.println("生成されたXML：\n" + xml);

        // XML → DTO
        PersonDTO result = XmlUtils.deserialize(xml, PersonDTO.class);
        System.out.println(result);
        // 	フィールドの値が一致するか確認する
        assertEquals(1, result.getId());
        assertEquals("Alice", result.getName());
        assertTrue(result.getHobbies().contains("Reading"));
        assertTrue(result.getTags().contains("engineer"));
        assertEquals("Java", result.getAttributes().get("lang"));
        assertEquals("Tokyo", result.getAddress().getCity());
    }
    }
