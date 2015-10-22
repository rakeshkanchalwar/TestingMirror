package com.bitwise.app.graph.helper;

import java.util.HashMap;

import org.slf4j.Logger;

import com.bitwise.app.common.util.LogFactory;

/**
 * @author Bitwise
 * 
 */
public class LoggerUtil {
	private static HashMap<Class<?>, Logger> loggerMap = new HashMap<>();

	public static Logger getLoger(Class<?> clazz) {
		Logger tempLogger = loggerMap.get(clazz);
		if (tempLogger != null) {
			return tempLogger;
		} else {
			tempLogger = LogFactory.INSTANCE.getLogger(clazz);
			loggerMap.put(clazz, tempLogger);
			return tempLogger;
		}
	}
}
