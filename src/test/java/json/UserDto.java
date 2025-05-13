package json;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    private String name;
    private int age;
    private LocalDate birthday;
    private LocalDateTime lastLogin;
    private List<String> roles;
    private Map<String, Object> metadata;
    private String[] hobbies;



}
