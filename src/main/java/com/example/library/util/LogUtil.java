package com.example.library.util;

import com.example.library.enums.SubjectEnum;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class LogUtil {

    public static void info(String message) {
        System.out.println(message);
    }

    public static void error(Object message) {
//        log.error(createMessage(message));
    }

    public static void warn(String message) {
        System.out.println(message);
    }
    private static String createLogMessage(String message) {
        return "this is log message";
    }

    private static String createMessage(Integer i) {
        return "this is int" + i;
    }
    private static String createMessage(String s) {
        return "this is string" + s;
    }

}
