package com.three.config;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.Properties;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.atomic.AtomicReference;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

/**
 * @Description: 操作配置文件的工具类
 */
public class SystemGlobals {
  private static AtomicReference<Properties> PREFERENCES = new AtomicReference<Properties>(new Properties());
  private static Logger LOGGER = Logger.getLogger(SystemGlobals.class);
  private static long LAST_MODIFIED_TIME = new Date().getTime();

  /**
   * @Description:加载配置文件
   */
  public static void loadConfig(String fileName) {
    try {
      InputStream is = SystemGlobals.class.getClassLoader().getResourceAsStream(fileName);
      Properties newProperties = new Properties();
      newProperties.load(is);
      PREFERENCES.set(newProperties);
    } catch (IOException e) {
      LOGGER.error("load SystemGlobals.properties error : ", e);
    }
  }

  public static String getPreference(String key) {
    if (StringUtils.isBlank(key)) {
      return null;
    }
    String value = PREFERENCES.get().getProperty(key);
    if (StringUtils.isBlank(key)) {
      return null;
    }
    return value;
  }

  public static String getPreference(String key, String defaultValue) {
    String value = getPreference(key);
    if (null == value) {
      return defaultValue;
    }
    return value;
  }

  public static int getIntPreference(String key, int defaultValue) {
    String value = getPreference(key);
    if (null == value) {
      return defaultValue;
    }
    return Integer.parseInt(value);
  }

  public static long getLongPreference(String key, long defaultValue) {
    String value = getPreference(key);
    if (null == value) {
      return defaultValue;
    }
    return Long.parseLong(value);
  }

  public static double getDoublePreference(String key, double defaultValue) {
    String value = getPreference(key);
    if (null == value) {
      return defaultValue;
    }
    return Double.parseDouble(value);
  }

  public static boolean getBooleanPreference(String key, boolean defaultValue) {
    String value = getPreference(key);
    if (null == value) {
      return defaultValue;
    }
    return Boolean.valueOf(value);
  }

  public static boolean isProductEnv() {
    return getBooleanPreference("is.product.env", false);
  }

  public static void startSystemGlobalsTimer(final String fileName) {
    Timer timer = new Timer();
    timer.schedule(new TimerTask() {
      @Override
      public void run() {
        String filePath = SystemGlobals.class.getResource("/").getFile().toString() + fileName;
        File file = new File(filePath);
        long millisec = file.lastModified();
        if (millisec > LAST_MODIFIED_TIME) {
          LOGGER.info("configFile:" + fileName + " has changed, reload now");
          loadConfig(fileName);
          LAST_MODIFIED_TIME = millisec;
        }
      }
    }, 20 * 1000, 1000);
  }
}
