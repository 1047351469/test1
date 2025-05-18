package json;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

public class TestJson {
    public static void main(String[] args) {
        UserDTO user = new UserDTO(
                "Alice",
                30,
                LocalDate.of(1993, 5, 10),
                LocalDateTime.of(2025, 5, 10, 12, 0),
                List.of("admin", "user"),
                Map.of("ip", "192.168.0.1", "active", true, "loginCount", 7),
                new String[]{"reading", "coding", "travel"},
                new AddressDTO("東京","001")
        );
        ResponseDto<UserDTO> response = new ResponseDto<>(200, "success", user);
        // 对象 → JSON
        String json = JsonUtils.serialize(response);
        System.out.println("序列化1:\n" + json);

        // JSON → 对象
        ResponseDto<UserDTO> parsed = JsonUtils.deserialize(json, UserDTO.class);
        System.out.println("反序列化2:\n" + parsed);

    }


}
