package xml;

import java.util.Arrays;

public class TestXml {
    public static void main(String[] args) throws Exception {
        // 构造 UserDTO 列表
        UserDTO user1 = new UserDTO();
        user1.setName("Alice");
        user1.setAge(30);

        UserDTO user2 = new UserDTO();
        user2.setName("Bob");
        user2.setAge(25);

        UserListWrapper userListWrapper = new UserListWrapper();
        userListWrapper.setUsers(Arrays.asList(user1, user2));

        // 构造响应对象
        ResponseDataDTO response = new ResponseDataDTO();
        response.setCode(200);
        response.setStatus("success");
        response.setData(userListWrapper);

        // 1. 序列化为 XML
        String xml = XmlUtils.serialize(response);
        System.out.println("=== XML ===");
        System.out.println(xml);

        // 2. 反序列化回对象
        ResponseDataDTO parsed = XmlUtils.deserialize(xml);
        Object data = parsed.getData();

        System.out.println("\n=== Deserialized ===");
        if (data instanceof UserListWrapper) {
            ((UserListWrapper) data).getUsers().forEach(u ->
                    System.out.println(u.getName() + " / " + u.getAge())
            );
        } else {
            System.out.println("data 类型不是 UserListWrapper");
        }
    }
}
