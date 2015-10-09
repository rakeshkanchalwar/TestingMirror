package com.bitwise.app.graph.helper;

import java.util.HashMap;

import org.slf4j.Logger;

import com.bitwise.app.common.util.LogFactory;

public class LoggerUtil {

	private static HashMap<String, Logger> loggerMap = new HashMap<String, Logger>();

	public static Logger getLoger(Object obj) {
		String className = obj.getClass().getName();
		Logger tempLogger = loggerMap.get(className);
		if (tempLogger != null) {
			return tempLogger;
		} else {
			tempLogger = new LogFactory(className).getLogger();
			loggerMap.put(className, tempLogger);
			return tempLogger;
		}

	}
}
