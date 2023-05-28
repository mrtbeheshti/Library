package com.example.library.util;

import com.example.library.pojo.log.LogObjectPOJO;
import lombok.extern.log4j.Log4j2;
import org.apache.logging.log4j.Logger;

public class LogUtil {
    public static void info(Logger log, LogObjectPOJO logObject) {
        log.info(logObject.getLog());
    }
    public static void error(Logger log, LogObjectPOJO logObject) {
        log.error(logObject.getLog());
    }
    public static void warn(Logger log, LogObjectPOJO logObject) {
        log.warn(logObject.getLog());
    }
}
