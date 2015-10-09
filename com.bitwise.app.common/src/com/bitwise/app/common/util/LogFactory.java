package com.bitwise.app.common.util;

import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;

import org.eclipse.core.runtime.Platform;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.joran.JoranConfigurator;
import ch.qos.logback.core.util.Loader;

public class LogFactory {
	final public String CLASSIC_FILE = "logback.xml";
    final public String LOG_DIR = "config/logger/";
    
    private static final Logger loggers = LoggerFactory.getLogger(LogFactory.class);
    public static final LogFactory INSTANCE = new LogFactory();
    
    private Logger logger;
    
    private LogFactory(){
    	writeLogsOnFileAndConsole();
    }
    
    public Logger getLogger(Class<?> clazz){
    	return LoggerFactory.getLogger(clazz.getName());
    }
    
    /**
     * Use Logger logger = LogFactory.INSTANCE.getLogger(Class<?> clazz)
     */
    @Deprecated
    public LogFactory(String className) {
    	logger = LoggerFactory.getLogger(className);
    	writeLogsOnFileAndConsole();
	}
    
    /**
     * Use Logger logger = LogFactory.INSTANCE.getLogger(Class<?> clazz)
     */
    @Deprecated
    public Logger getLogger(){
    	return logger;
    }
    
	private void writeLogsOnFileAndConsole() {
		loggers.debug("****Configuring Logger****");
        try {
	            ClassLoader loader = new URLClassLoader(new URL[]
	            		{new File(Platform.getInstallLocation().getURL().getPath() + LOG_DIR).toURI().toURL()});
	            LoggerContext lc = (LoggerContext) LoggerFactory.getILoggerFactory();
	            URL url = Loader.getResource(CLASSIC_FILE, loader);
	            if (url != null) {
	                JoranConfigurator configurator = new JoranConfigurator();
	                configurator.setContext(lc);
	                lc.reset();
	                configurator.doConfigure(url);
	                lc.start();
            }
            loggers.debug("****Logger Configured Successfully****");
        } catch(Exception e){
        	loggers.error("Failed to configure the logger {}", e.getMessage());
        }
    }

}
