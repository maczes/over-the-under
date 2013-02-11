package logging.web.ejb.util;

import java.util.Comparator;
import java.util.Enumeration;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

import javax.enterprise.context.RequestScoped;

@RequestScoped
public class LoggersRetriever {

	private static final Logger logger = Logger.getLogger(LoggersRetriever.class.getName());
	
	public SortedSet<Logger> retrieveLoggers(LogManager lmgr){
		logger.log(Level.FINER, "starting retrieve loggers.. ");
		
		Enumeration<String> loggerNames = lmgr.getLoggerNames();
		SortedSet<Logger> loggers = new TreeSet<Logger>(new Comparator<Logger>() {
			@Override
			public int compare(Logger o1, Logger o2) {
				return o1.getName().compareTo(o2.getName());
			}
		});
		
		String loggerName = null;
		
		while(loggerNames.hasMoreElements()){
			loggerName = loggerNames.nextElement();
			logger.log(Level.FINER, "retrieving : " + loggerName);
			loggers.add(Logger.getLogger(loggerName));
		}
		
		logger.log(Level.FINER, "returning result: loggers size: " + loggers.size());
		return loggers;
	}
	
}
