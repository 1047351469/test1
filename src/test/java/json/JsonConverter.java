package json;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class JsonConverter {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    static {
        // 配置 ObjectMapper
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
    }

    // JSON → ResponseDto<T>
    public static <T> ResponseDto<T> fromJson(String json, Class<T> dataClass) {
        try {
            JavaType responseType = objectMapper
                    .getTypeFactory()
                    .constructParametricType(ResponseDto.class, dataClass);
            return objectMapper.readValue(json, responseType);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("反序列化失败", e);
        }
    }

    // JSON → ResponseDto<List<T>>
    public static <T> ResponseDto<java.util.List<T>> fromJsonToList(String json, Class<T> elementType) {
        try {
            JavaType listType = objectMapper.getTypeFactory()
                    .constructCollectionType(java.util.List.class, elementType);
            JavaType responseType = objectMapper.getTypeFactory()
                    .constructParametricType(ResponseDto.class, listType);
            return objectMapper.readValue(json, responseType);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("反序列化 List<T> 失败", e);
        }
    }

    // 任意对象 → JSON
    public static String toJson(Object obj) {
        try {
            return objectMapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("序列化失败", e);
        }
    }

    public static String readJsonString(String fileName) {
        try (InputStream is = JsonConverter.class.getClassLoader().getResourceAsStream(fileName)) {
            if (is == null) {
                throw new RuntimeException("资源文件不存在: " + fileName);
            }
            return new String(is.readAllBytes(), StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new RuntimeException("读取资源文件失败: " + fileName, e);
        }
    }
}
