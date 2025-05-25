package util;

import com.ReggieApplication;
import com.anyi.reggie.config.MessageUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import java.util.regex.Pattern;

import static util.ScreenInfo.*;

@SpringBootTest(classes = ReggieApplication.class)
@WebMvcTest()
public class TestConst {
    @Autowired
    private MessageUtil messageUtil;
    @Test
    void testGetMeseage() {
        String msg = messageUtil.get("user.notfound", "蔡");
        System.out.println(msg);
        System.out.println(MessageInfo.C001);
        System.out.println(CommonCodes.C001);
        boolean isMatch = Pattern.matches(Regex.R001, "カタカナー");
        System.out.println("匹配结果: " + isMatch);

        // 测试 isExistScreenInfoNo
        System.out.println("isExistScreenInfoNo(\"1\") = " + isExistScreenInfoNo("1")); // true

        // 测试 isExistScreenInfoId
        System.out.println("isExistScreenInfoId(\"XCHM6310\") = " + isExistScreenInfoId("XCHM6310")); // true

        // 测试 getScreenInfoNo
        System.out.println("getScreenInfoNo(\"XCHM6310\") = " + getScreenInfoNo("1"));

        // 测试 getScreenInfoId
        System.out.println("getScreenInfoId(\"1\") = " + getScreenInfoId("XCHM6310"));

        // 测试 getScreenInfoList
        System.out.println("getScreenInfoList() = " + getScreenInfoList());
        System.out.println(ApiInfoUtils.getApiIdList());

    }
//    https://zhuanlan.zhihu.com/p/651044202
}
