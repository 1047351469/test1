package xml;

import jakarta.xml.bind.*;

import javax.xml.transform.stream.StreamSource;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;

import static java.lang.Boolean.TRUE;
import static jakarta.xml.bind.Marshaller.JAXB_FORMATTED_OUTPUT;

public class XmlUtils {

    // 全局只创建一次 JAXBContext，线程安全
    private static final JAXBContext jaxbContext = createJAXBContext();

    // 私有构造方法：禁止实例化
    private XmlUtils() {}

    // 创建 JAXBContext（只注册需要的类）
    private static JAXBContext createJAXBContext() {
        try {
            return JAXBContext.newInstance(ResponseDataDTO.class);
        } catch (JAXBException e) {
            throw new RuntimeException("Failed to initialize JAXBContext", e);
        }
    }

    // 将对象转成 XML 字符串
    public static String serialize(ResponseDataDTO responseData) throws JAXBException, IOException {
        Marshaller marshaller = jaxbContext.createMarshaller();
        marshaller.setProperty(JAXB_FORMATTED_OUTPUT, TRUE);

        try (StringWriter sw = new StringWriter()) {
            marshaller.marshal(responseData, sw);
            return sw.toString();
        }
    }

    // 将 XML 字符串还原成对象
    public static ResponseDataDTO deserialize(String body) throws JAXBException {
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
        try (StringReader stringReader = new StringReader(body)) {
            return (ResponseDataDTO) unmarshaller.unmarshal(new StreamSource(stringReader));
        }
    }
}

