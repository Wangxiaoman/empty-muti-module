package com.three.config;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;

/**
 * 用于加载框架的配置信息，初始化框架的参数，只在框架内部使用！
 * @author 陈文鹏 2013-3-23下午7:47:23
 */
public class ConfigInitializer implements ServletContextListener {
  /*
   * (non-Javadoc)
   * @see javax.servlet.ServletContextListener#contextInitialized(javax.servlet
   * .ServletContextEvent)
   */
  Logger logger = Logger.getLogger(ConfigInitializer.class);

  @Override
  public void contextInitialized(ServletContextEvent event) {
    ServletContext context = event.getServletContext();
    String configFile = context.getInitParameter("com.three.config.file");
    String lo4jFile = context.getInitParameter("com.three.lo4j.file");

    if (StringUtils.isNotEmpty(lo4jFile)) {
      String prefix = context.getRealPath("/");
      DOMConfigurator.configure(prefix + lo4jFile);// log4j.xml用DOMConfigurator初始化
      logger.info("------>init log4j.xml success : " + prefix + lo4jFile);
    }
    if (StringUtils.isNotEmpty(configFile)) {
      SystemGlobals.loadConfig(configFile);
      SystemGlobals.startSystemGlobalsTimer(configFile);
      logger.info("------>init SystemGlobals.properties success : " + configFile);
    }

  }

  /*
   * (non-Javadoc)
   * @see javax.servlet.ServletContextListener#contextDestroyed(javax.servlet. ServletContextEvent)
   */
  @Override
  public void contextDestroyed(ServletContextEvent event) {

  }
}
