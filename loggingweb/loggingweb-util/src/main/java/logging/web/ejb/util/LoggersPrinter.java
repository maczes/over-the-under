package logging.web.ejb.util;

import java.util.Collection;
import java.util.SortedSet;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LoggersPrinter {

	private static final Logger logger = Logger.getLogger(LoggersPrinter.class.getName());
	
	public void printNames(SortedSet<Logger> loggers){
		for(Logger loggerEntry : loggers){
			logger.log(Level.INFO, "loggerName: " + loggerEntry.getName());
		}
	}
	
	public void printNames(Collection<Logger> loggers){
		for(Logger loggerEntry : loggers){
			logger.log(Level.INFO, "loggerName: " + loggerEntry.getName());
		}
	}
	
	public void printNamesAndLevels(Collection<Logger> loggers){
		for(Logger loggerEntry : loggers){
			logger.log(Level.INFO, "loggerName: " + loggerEntry.getName() + ", loggerLevel: " + loggerEntry.getLevel() );
		}
	}
}
