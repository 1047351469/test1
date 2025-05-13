package com.anyi.reggie.config;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

@Component
public class MessageUtil {

    private static MessageSource staticMessageSource; // 缓存为静态变量

    // 由 Spring 注入
    public MessageUtil(MessageSource messageSource) {
        MessageUtil.staticMessageSource = messageSource;
    }

    /**
     * 可在任何地方通过 MessageUtil.get(...) 调用
     */
    public static String get(String code, Object... args) {
        return staticMessageSource.getMessage(code, args, LocaleContextHolder.getLocale());
    }
}
