package util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ApiInfo {
    private String apiUrl;
    private String requestMethod; // "get" or "post"
    private String apiId;

    public ApiInfo(String apiUrl, String requestMethod, String apiId) {
        this.apiUrl = apiUrl;
        this.requestMethod = requestMethod;
        this.apiId = apiId;
    }

    public String getApiUrl() {
        return apiUrl;
    }

    public String getRequestMethod() {
        return requestMethod;
    }

    public String getApiId() {
        return apiId;
    }
}

class ApiInfoUtils {

    private static final Map<String, ApiInfo> API_INFO_LIST = new HashMap<>();

    static {
        API_INFO_LIST.put("WGA001", new ApiInfo("/WGA001", "post", "WGA001"));
        API_INFO_LIST.put("WGA002", new ApiInfo("/WGA002", "post", "WGA002"));
        API_INFO_LIST.put("ESB001", new ApiInfo("/ESB001", "post", "ESB001"));
        API_INFO_LIST.put("INT001", new ApiInfo("/INT001", "post", "INT001"));
        API_INFO_LIST.put("INT002", new ApiInfo("/INT002", "post", "INT002"));
        API_INFO_LIST.put("INT003", new ApiInfo("/INT003", "post", "INT003"));
    }

    public static List<String> getApiIdList() {
        return new ArrayList<>(API_INFO_LIST.keySet());
    }
}
