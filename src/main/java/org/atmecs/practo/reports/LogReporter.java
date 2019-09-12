package org.atmecs.practo.reports;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.atmecs.practo.contstants.FileConstants;

public class LogReporter {
		Logger logger = null;

		public void LogReport(String message) {
		PropertyConfigurator.configure(FileConstants.logfile);
		logger = Logger.getLogger(LogReporter.class.getName());
		logger.info(message);
		}
		}
