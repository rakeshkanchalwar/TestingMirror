package com.bitwise.app.common.util;

import ch.qos.logback.classic.LoggerContext; 
import ch.qos.logback.classic.joran.JoranConfigurator;
import ch.qos.logback.core.joran.spi.JoranException;
import ch.qos.logback.core.util.Loader;

import org.eclipse.core.runtime.Platform;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;

public class ELTLoggerUtil {
	final public  String CLASSIC_FILE = "logback.xml";
    final public String BASE_PATH=Platform.getInstallLocation().getURL().getPath();
    final public String LOG_DIR = "config/ELTLogging/";
    private static final String className = "ELTLoggerUtil";
    private static final ELTLoggerUtil eltLogger = new ELTLoggerUtil(className);
    
    Logger logger;
    
    public ELTLoggerUtil(String className) {
    	logger = LoggerFactory.getLogger(className+".class");
	}
    
    public void info(String infoMsg) {
        logger.info(infoMsg);
        writeLogsOnFileAndConsole();
    }

    public void debug(String debugMsg) {
        logger.debug(debugMsg);
        writeLogsOnFileAndConsole();
    }

    public void error(Exception e) {
        logger.error(e.getMessage());
        writeLogsOnFileAndConsole();
    }

    public void trace(String traceMsg) {
        logger.trace(traceMsg);
        writeLogsOnFileAndConsole();
    }

    public void warn(String warnMsg) {
        logger.warn(warnMsg);
        writeLogsOnFileAndConsole();

    }
    
	private void writeLogsOnFileAndConsole() {
        try {
            ClassLoader loader = new URLClassLoader(new URL[]{new File(BASE_PATH+LOG_DIR).toURI().toURL()});
            LoggerContext lc = (LoggerContext) LoggerFactory.getILoggerFactory();
             URL url = Loader.getResource(CLASSIC_FILE, loader);
            if (url != null) {
                JoranConfigurator configurator = new JoranConfigurator();
                configurator.setContext(lc);
                lc.reset();
                configurator.doConfigure(url);
                lc.start();
            }
        } catch (JoranException je) {
        	eltLogger.error(je);
            je.printStackTrace();
        } catch (MalformedURLException e) {
        	eltLogger.error(e);
            e.printStackTrace();
        }
    }

}
