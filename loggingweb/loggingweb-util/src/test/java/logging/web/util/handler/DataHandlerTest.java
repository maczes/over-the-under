package logging.web.util.handler;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import java.util.Comparator;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.logging.LogManager;
import java.util.logging.Logger;

import logging.web.util.LoggersRetriever;
import logging.web.util.handler.DataHandler;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

public class DataHandlerTest {

	DataHandler dataHandler;
	
	private static final String loggerName = "myAwsomeLogger";
	
	private Logger logger = Logger.getLogger(loggerName);
	private SortedSet<Logger> loggers = new TreeSet<Logger>(new Comparator<Logger>() {
		@Override
		public int compare(Logger o1, Logger o2) {
			return o1.getName().compareTo(o2.getName());
		}
	});
	
	@Mock
	LoggersRetriever loggersRetrieverMock; 
	
	public DataHandlerTest() {
		MockitoAnnotations.initMocks(this);
		dataHandler = new DataHandler();
		dataHandler.loggersRetriever = loggersRetrieverMock;
		loggers.add(logger);
	}
	
	@Before
	public void setup(){
		when(loggersRetrieverMock.retrieveLoggers(Mockito.any(LogManager.class))).thenReturn(loggers);
	}
	
	@Test
	public void atest(){
		LogManager logManager = LogManager.getLogManager();
		logManager.addLogger(logger);
		dataHandler.reloadData(logManager);
		
		assertTrue(dataHandler.getLoggers().contains(logger));
	}
	
	@Test
	public void shouldHave(){
		LogManager logManager = LogManager.getLogManager();
		logManager.addLogger(logger);
		dataHandler.reloadData(logManager);
		Set<Logger> loggers = dataHandler.getLoggers();
		
		assertTrue(loggers.contains(logger));
	}
	
	@Test
	public void shouldHave2(){
		LogManager logManager = LogManager.getLogManager();
		logManager.addLogger(logger);
		dataHandler.setLoggersForLogManager(logManager);
		
		assertTrue(dataHandler.getLoggers().contains(logger));
	}
}
