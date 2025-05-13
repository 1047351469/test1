package util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ScreenInfo {
    public static class Screen{
        private String screenNo;
        private String screenName;
        private String screenId;
        private String screenGroup;
        private boolean isDLpad;
        private boolean isWeb;
        // 无参构造
        public Screen() {
        }

        // 全参构造
        public Screen(String screenNo, String screenName, String screenId, String screenGroup, boolean isDLpad, boolean isWeb) {
            this.screenNo = screenNo;
            this.screenName = screenName;
            this.screenId = screenId;
            this.screenGroup = screenGroup;
            this.isDLpad = isDLpad;
            this.isWeb = isWeb;
        }

        public String getScreenNo() {
            return screenNo;
        }

        public void setScreenNo(String screenNo) {
            this.screenNo = screenNo;
        }

        public String getScreenName() {
            return screenName;
        }

        public void setScreenName(String screenName) {
            this.screenName = screenName;
        }

        public String getScreenId() {
            return screenId;
        }

        public void setScreenId(String screenId) {
            this.screenId = screenId;
        }

        public String getScreenGroup() {
            return screenGroup;
        }

        public void setScreenGroup(String screenGroup) {
            this.screenGroup = screenGroup;
        }

        public boolean getIsDLpad() {
            return isDLpad;
        }

        public void setIsDLpad(boolean isDLpad) {
            this.isDLpad = isDLpad;
        }

        public boolean getIsWeb() {
            return isWeb;
        }

        public void setIsWeb(boolean isWeb) {
            this.isWeb = isWeb;
        }

        public String toString() {
            return "ScreenInfo{" +
                    "screenNo='" + screenNo + '\'' +
                    ", screenName='" + screenName + '\'' +
                    ", screenId='" + screenId + '\'' +
                    ", screenGroup='" + screenGroup + '\'' +
                    ", isDLpad=" + isDLpad +
                    ", isWeb=" + isWeb +
                    '}';
        }

    }
   private static final Map<String,Screen> SCREEN_INFO_LIST=new HashMap<>();
    static {
        SCREEN_INFO_LIST.put("XCHM6301", new Screen("1","訓練資料入力", "XCHM6310", "XAAG3H01", true, false));
        SCREEN_INFO_LIST.put("XCHM6302", new Screen("2","家族資料登録使用画面", "XCHM6320", "XAAG3H01", true, false));
        SCREEN_INFO_LIST.put("XCHM6303", new Screen("3","職業資料入力（種類）画面", "XCHM6330", "XAAG3H01", true, true));
        SCREEN_INFO_LIST.put("XCHM6304", new Screen("4-1-I","職業資料入力（経験）画面", "XCHM6341", "XAAG3H01", true, false));
        SCREEN_INFO_LIST.put("XCHM6305", new Screen("4-2","職業資料入力（詳細）画面", "XCHM6342", "XAAG3H01", true, false));
        SCREEN_INFO_LIST.put("XCHM6306", new Screen("5-1","就業情報入力", "XCHM6361", "XAAG3H01", true, true));
        SCREEN_INFO_LIST.put("XCHM6307", new Screen("6","職業訓練実績入力（詳細）画面", "XCHM6360", "XAAG3H01", true, true));
    }
    public static boolean isExistScreenInfoNo(String screenNo) {
        return SCREEN_INFO_LIST.values().stream()
                .anyMatch(info -> screenNo.equals(info.getScreenNo()));
    }

    public static boolean isExistScreenInfoId(String screenId) {
        return SCREEN_INFO_LIST.values().stream()
                .anyMatch(info -> screenId.equals(info.getScreenId()));
    }

    public static Screen getScreenInfoNo(String screenNo) {
        return SCREEN_INFO_LIST.values().stream()
                .filter(info -> screenNo.equals(info.getScreenNo()))
                .findFirst()
                .orElse(null);
    }

    public static Screen getScreenInfoId(String screenId) {
        return SCREEN_INFO_LIST.values().stream()
                .filter(info -> screenId.equals(info.getScreenId()))
                .findFirst()
                .orElse(null);
    }

    public static List<Screen> getScreenInfoList() {
        return new ArrayList<>(SCREEN_INFO_LIST.values());
    }

}
