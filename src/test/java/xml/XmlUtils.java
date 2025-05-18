package xml;

import jakarta.xml.bind.*;


import java.io.StringReader;
import java.io.StringWriter;


public class XmlUtils {

    // XML → DTO
    public static <T> T deserialize(String xml, Class<T> dtoClass) throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(dtoClass);
        Unmarshaller unmarshaller = context.createUnmarshaller();
        return (T) unmarshaller.unmarshal(new StringReader(xml));
    }

    // DTO → XML
    public static String serialize(Object dto) throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(dto.getClass());
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        StringWriter writer = new StringWriter();
        marshaller.marshal(dto, writer);
        return writer.toString();
    }
}

