package test;

import com.anyi.reggie.entity.Address;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Configuration
@Data
@ConfigurationProperties(prefix = "user")
@NoArgsConstructor
public class UserConfig {
    private Long id;
    private String name;
    private boolean active;
    private int age;
    private double balance;
    private List<String> roles; // 绑定 roles 列表
    private Address address;    // 绑定 address 对象
    private Map<String, Object> preferences; // 绑定 preferences Map
}
