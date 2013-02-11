package logging.web.ejb.bean;

import java.io.Serializable;
import java.util.List;
import java.util.SortedSet;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

import logging.web.ejb.util.LoggersFilter;
import logging.web.ejb.util.LoggersPrinter;
import logging.web.ejb.util.LoggersRetriever;

public class DataHandler implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private static final Logger logger = Logger.getLogger(DataHandler.class.getName());
	
	private Level levels[] = { Level.OFF, Level.SEVERE, Level.CONFIG, Level.INFO, Level.FINE, Level.FINER, Level.FINEST, Level.ALL };
	
	private SortedSet<Logger> loggers;
	
	//@Inject //DOES NOT WORK! WHY????
	LoggersRetriever loggersRetriever;
	
	public DataHandler() {
		loggersRetriever = new LoggersRetriever();
		reloadData(LogManager.getLogManager());
	}
	
//	@PostConstruct //DOES NOT WORK! WHY??
//	protected void init(){
//		loggersRetriever = new LoggersRetriever();
//	}
	
	void reloadData(LogManager lmgr) {
		synchronized(this){			
			SortedSet<Logger> loggers = loggersRetriever.retrieveLoggers(lmgr);
			logger.log(Level.INFO, "loggers size after reloading: " + loggers.size());
			this.loggers = loggers;
		}
	}
	
	void reloadData() {
		reloadData(LogManager.getLogManager());
	}

	public SortedSet<Logger> getLoggers() {
		return this.loggers;
	}

	public void setLoggers(SortedSet<Logger> loggers) {
		this.loggers = loggers;
	}
	
	public void filter(LoggersFilter loggersFilter) {
		loggers = loggersFilter.filter(loggers);
	}

	public void setLoggersForLogManager(LogManager logManager) {
		reloadData(logManager);
	}
		
	public void print(LoggersPrinter loggersPrinter){
		loggersPrinter.printNames(loggers);
	}

	public Level[] getLevels() {
		synchronized(this){	
			return levels;
		}
	}

	public void setLevels(Level levels[]) {
		this.levels = levels;
	}
	
	public void updateLoggers(List<Logger> updated){
		for(Logger logger : updated){
			logger.log(Level.INFO, "setting up logger: " + logger.getName() + " - level: " + logger.getLevel());
			LogManager.getLogManager().getLogger(logger.getName()).setLevel(logger.getLevel());
		}
		reloadData();
	}

	
	
}
