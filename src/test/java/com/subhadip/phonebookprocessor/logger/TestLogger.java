package com.subhadip.phonebookprocessor.logger;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class TestLogger {

	public static void setUp() throws SecurityException, IOException {
		Logger logger = Logger.getLogger("");
		
		Handler fileHandler = new FileHandler("TestLog.log", false);
		fileHandler.setFormatter(new SimpleFormatter());
		logger.addHandler(fileHandler);
		
		logger.setLevel(Level.FINEST);
	}
}
