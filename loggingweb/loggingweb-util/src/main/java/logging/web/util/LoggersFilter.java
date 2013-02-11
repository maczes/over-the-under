package logging.web.util;

import java.util.Comparator;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.logging.Logger;

public class LoggersFilter {
	private String filterExpression;
	
	@SuppressWarnings("unused")
	private LoggersFilter(){};
	
	public LoggersFilter(String filterExpression){
		this.setFilterExpression(filterExpression);
	}

	public String getFilterExpression() {
		return filterExpression;
	}

	public void setFilterExpression(String filterExpression) {
		this.filterExpression = filterExpression;
	}
	
	public SortedSet<Logger> filter(SortedSet<Logger> loggers){
		SortedSet<Logger> result = new TreeSet<Logger>(new Comparator<Logger>() {
			@Override
			public int compare(Logger o1, Logger o2) {
				return o1.getName().compareTo(o2.getName());
			}
		});
		for(Logger logger : loggers){
			if (logger.getName().startsWith(this.filterExpression)){
				result.add(logger);
			}
		}
		return result;
	}	
}