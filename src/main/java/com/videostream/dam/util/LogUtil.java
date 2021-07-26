package com.videostream.dam.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LogUtil {
    private static  Logger LOGGER = null;
    public static Logger getInstance() {
        if(LOGGER == null) {
            LOGGER = LoggerFactory.getLogger(LogUtil.class);
        }
        return LOGGER;
    }
    public static void info(String msg) {
        getInstance().info(msg);
    }
    public static void error(String msg) {
        getInstance().error(msg);
    }
    public static void debug(String msg) {
        getInstance().debug(msg);
    }
    public static void warn(String msg) {
        getInstance().warn(msg);
    }
    public static void trace(String msg) {
        getInstance().trace(msg);
    }
}
