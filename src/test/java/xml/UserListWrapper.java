package xml;

import jakarta.xml.bind.annotation.*;

import java.util.List;

@XmlRootElement(name = "userList")
@XmlAccessorType(XmlAccessType.FIELD)
public class UserListWrapper {

    @XmlElement(name = "user")
    private List<UserDTO> users;

    // Getter & Setter
    public List<UserDTO> getUsers() {
        return users;
    }

    public void setUsers(List<UserDTO> users) {
        this.users = users;
    }
}
