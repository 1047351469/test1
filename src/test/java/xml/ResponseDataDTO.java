package xml;

import cn.hutool.core.map.MapWrapper;
import jakarta.xml.bind.annotation.*;

@XmlRootElement(name = "response")
@XmlAccessorType(XmlAccessType.FIELD)
public class ResponseDataDTO {

    private int code;
    private String status;

    // 支持多种类型作为 data 的内容
    @XmlElements({
            @XmlElement(name = "user", type = UserDTO.class),
            @XmlElement(name = "userList", type = UserListWrapper.class),
            @XmlElement(name = "map", type = MapWrapper.class),
            @XmlElement(name = "message", type = String.class)
            // 需要的话可以加：@XmlElement(name = "date", type = DateWrapper.class)
    })
    private Object data;

    // Getters & Setters
    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}

