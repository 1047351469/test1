package json;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

public class TestJson {
    public static void main(String[] args) {
        // 构建对象
        UserDto user = new UserDto(
                "Alice",
                30,
                LocalDate.of(1993, 5, 10),
                LocalDateTime.of(2025, 5, 10, 12, 0),
                List.of("admin", "user"),
                Map.of("ip", "192.168.0.1", "active", true, "loginCount", 7),
                new String[]{"reading", "coding", "travel"}
        );
        ResponseDto<UserDto> response = new ResponseDto<>(200, "success", user);
        // 对象 → JSON
        String json = JsonConverter.toJson(response);
        System.out.println("序列化1:\n" + json);

        // JSON → 对象
        ResponseDto<UserDto> parsed = JsonConverter.fromJson(json, UserDto.class);
        System.out.println("反序列化2:\n" + parsed.getData());

        String listJsonBak = "{\n" +
                "  \"code\": 200,\n" +
                "  \"message\": \"success\",\n" +
                "  \"data\": [\n" +
                "    {\n" +
                "      \"name\": \"Alice\",\n" +
                "      \"age\": 30,\n" +
                "      \"birthday\": \"1993-05-10\",\n" +
                "      \"lastLogin\": \"2025-05-10T12:00:00\",\n" +
                "      \"roles\": [\"admin\", \"user\"],\n" +
                "      \"metadata\": {\n" +
                "        \"ip\": \"192.168.0.1\",\n" +
                "        \"active\": true,\n" +
                "        \"loginCount\": 7\n" +
                "      },\n" +
                "      \"hobbies\": [\"reading\", \"coding\", \"travel\"]\n" +
                "    },\n" +
                "    {\n" +
                "      \"name\": \"Bob\",\n" +
                "      \"age\": 28,\n" +
                "      \"birthday\": \"1995-03-20\",\n" +
                "      \"lastLogin\": \"2025-05-11T09:30:00\",\n" +
                "      \"roles\": [\"user\"],\n" +
                "      \"metadata\": {\n" +
                "        \"ip\": \"192.168.0.2\",\n" +
                "        \"active\": false,\n" +
                "        \"loginCount\": 3\n" +
                "      },\n" +
                "      \"hobbies\": [\"gaming\", \"music\"]\n" +
                "    }\n" +
                "  ]\n" +
                "}\n";
        String listJson=JsonConverter.readJsonString("userList.json");

        ResponseDto<List<UserDto>> result = JsonConverter.fromJsonToList(listJson, UserDto.class);
        System.out.println(result);
    }


}
