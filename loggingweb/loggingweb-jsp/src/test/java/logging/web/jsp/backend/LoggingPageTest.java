package logging.web.jsp.backend;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.SortedSet;
import java.util.logging.Level;
import java.util.logging.Logger;

import logging.web.util.LoggersComparator;
import logging.web.util.handler.DataHandler;

import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.google.common.collect.Sets;


public class LoggingPageTest {
	private static final String LOGGER_NAME = "mylog";
	private LoggingPage page;
	private @Mock DataHandler dataHandlerMock;
	private @Mock Logger loggerMock;
	private @Mock LoggersComparator comparatorMock;
	private SortedSet<Logger> loggers;
	private Level[] levels = {Level.ALL, Level.CONFIG};
	
	public LoggingPageTest() {
		MockitoAnnotations.initMocks(this);
		loggers = Sets.newTreeSet(comparatorMock);
		loggers.add(loggerMock);
		
		when(dataHandlerMock.getLoggers()).thenReturn(loggers);
		when(dataHandlerMock.getLevels()).thenReturn(levels);
		when(loggerMock.getName()).thenReturn(LOGGER_NAME);
		
		page = new LoggingPage();
		
		page.setFilterexp(LOGGER_NAME);
		page.loggersHandler = dataHandlerMock;
	}
	
	@Test
	public void shouldContainInitializedLoggersList(){
		page.init();
		List<Logger> loggers2 = page.getLoggers();
		assertTrue(loggers2 != null);
		verify(dataHandlerMock).getLoggers();
	}
	
	@Test
	public void shouldContainInitializedLevelsList(){
		page.init();
		List<Level> levels = page.getLevels();
		assertTrue(levels != null);
		verify(dataHandlerMock).getLevels();
	}
	
}
