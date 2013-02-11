package logging.web.jsf.backend;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.html.HtmlSelectOneMenu;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.inject.Inject;

import logging.web.jsf.constants.Messages;
import logging.web.util.LoggersFilter;
import logging.web.util.LoggersPrinter;
import logging.web.util.handler.DataHandler;

@ManagedBean(name = "loggingBean")
@SessionScoped
public class LoggingBean implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger(LoggingBean.class.getName());
	private String emptyLevelMsg = Messages.EMPTY_LEVEL_MSG;
	
	@Inject
	DataHandler loggerHandler;
	@Inject
	LoggersPrinter loggersPrinter;
	
	private LoggersFilter loggersFilter;
	private List<Logger> loggers;
	private List<Level> levels;
	
	private String loggerFilterExpr = "logging";
	
    private DataModel<Logger> loggersDataModel = new ListDataModel<Logger>();
    private DataModel<Level> levelsDataModel = new ListDataModel<Level>();
    
    @PostConstruct
    protected void init(){
    	logger.log(Level.INFO, "post constructing LoggingBean");
    	
    	reloadData();
    	
    	levels = Arrays.asList(loggerHandler.getLevels());
    	
    	if (levels != null){
    		logger.log(Level.INFO, "setting up levels");
    		getLevelsDataModel().setWrappedData(levels);
    	}
    }

	private void reloadData() {
		loggersFilter = new LoggersFilter(loggerFilterExpr);
    	loggerHandler.filter(loggersFilter);
    	
    	logger.log(Level.INFO, "filtered loggers table size: " + loggerHandler.getLoggers().size());
    	
    	loggers = new ArrayList<Logger>(loggerHandler.getLoggers());
    	
    	if (loggers != null){
    		logger.log(Level.INFO, "setting up loggers");
    		getLoggersDataModel().setWrappedData(loggers);
    	}
	}

	public DataModel<Logger> getLoggersDataModel() {
		return loggersDataModel;
	}

	public void setLoggersDataModel(DataModel<Logger> loggersDataModel) {
		this.loggersDataModel = loggersDataModel;
	}
	
	public String saveLoggersLevels(){
		flushChanges();
		reloadData();
		return "logging.jsp";
	}
	
	private void flushChanges() {
		logger.log(Level.INFO, "flushing changes");
		loggersPrinter.printNamesAndLevels(loggers);
		loggerHandler.updateLoggers(loggers);
	}

	public DataModel<Level> getLevelsDataModel() {
		return levelsDataModel;
	}

	public void setLevelsDataModel(DataModel<Level> levelsDataModel) {
		this.levelsDataModel = levelsDataModel;
	}

	public String getEmptyLevelMsg() {
		return emptyLevelMsg;
	}

	public void setEmptyLevelMsg(String emptyLevelMsg) {
		//this.emptyLevelMsg = emptyLevelMsg;
	}

	public void loggerLevelChanged(ValueChangeEvent event){
		Level selected = null;
		if (event.getNewValue() != null){
			selected = (Level) event.getNewValue();
			HtmlSelectOneMenu source = (HtmlSelectOneMenu)event.getSource();
			logger.log(Level.INFO, "levelChanged: comp: " + source.toString());
		} 
		if (selected != null)
			logger.log(Level.INFO, "levelChanged: selected: " + selected);
	}
}
