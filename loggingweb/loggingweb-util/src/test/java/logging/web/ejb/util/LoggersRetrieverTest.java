package logging.web.ejb.util;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.SortedSet;
import java.util.logging.LogManager;
import java.util.logging.Logger;

import org.junit.Before;
import org.junit.Test;


public class LoggersRetrieverTest {
	private static final String FILTER_EXPRESSION = "my.loggers";
	
	private Logger log1 = Logger.getLogger(FILTER_EXPRESSION + "log1");
	private Logger log2 = Logger.getLogger(FILTER_EXPRESSION + "log2");
	private Logger log3 = Logger.getLogger("log2");
	
	LogManager logManager;
	LoggersRetriever loggersRetriever;
	
	public LoggersRetrieverTest() {
		loggersRetriever = new LoggersRetriever();
	}
	
	@Before
	public void setup(){
		logManager = LogManager.getLogManager();
		logManager.addLogger(log1);
		logManager.addLogger(log2);
		logManager.addLogger(log3);
	}
	
	@Test
	public void shouldRetrieveLoggers(){
		SortedSet<Logger> retrieveLoggers = loggersRetriever.retrieveLoggers(logManager);
		
		assertNotNull(retrieveLoggers);
		assertTrue(retrieveLoggers.size() > 0);
	}
}
