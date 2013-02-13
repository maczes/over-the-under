package logging.web.jsp.backend;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import logging.web.util.LoggersFilter;
import logging.web.util.LoggersPrinter;
import logging.web.util.handler.DataHandler;

@ManagedBean(name = "LoggingPage")
@SessionScoped
public class LoggingPage {
	
	private static final Logger logger = Logger.getLogger(LoggingPage.class.getName());
	
	private List<Logger> loggers;
	private List<Level> levels;

 	//@Inject //does not work! 
 	DataHandler loggersHandler;
 	//@Inject //does not work! 
	LoggersPrinter loggersPrinter;
	
	private LoggersFilter loggersFilter;
	
	private String filterexp = "logging.";
	
	public LoggingPage() {
		init();
	}
	
	@PostConstruct
	protected void init(){
		loggersFilter = new LoggersFilter(getFilterexp());	
		loggersHandler = new DataHandler();
		loggersHandler.filter(loggersFilter);
		loggers = new ArrayList<Logger>(loggersHandler.getLoggers());
		levels = Arrays.asList(loggersHandler.getLevels());
		loggersPrinter = new LoggersPrinter();
		loggersPrinter.printNames(loggers);
	}
	
	public List<Logger> getLoggers() {
		logger.log(Level.INFO, "logging loggers from LoggingPage bean: getLoggers: " + loggers);
		loggersPrinter.printNamesAndLevels(loggers);
		return loggers;
	}

	public void setLoggers(List<Logger> loggers) {
		logger.log(Level.INFO, "logging loggers from LoggingPage bean: setLoggers: " + loggers);
		loggersPrinter.printNamesAndLevels(loggers);
		this.loggers = loggers;
	}
	
	public List<Level> getLevels() {
		return levels;
	}

	public void setLevels(List<Level> levels) {
		this.levels = levels;
	}

	public String getFilterexp() {
		return filterexp;
	}

	public void setFilterexp(String filterexp) {
		this.filterexp = filterexp;
	}
	
	public String saveLoggersLevels(){
		logger.log(Level.INFO, "save logger levels fired up");
		return "logging.jsp";
	}

}
