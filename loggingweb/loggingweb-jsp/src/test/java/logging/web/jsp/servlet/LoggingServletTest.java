package logging.web.jsp.servlet;

import java.io.IOException;
import java.util.logging.LogManager;
import java.util.logging.Logger;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import logging.web.jsp.servlet.LoggingServlet;
import logging.web.util.LoggersFilter;
import logging.web.util.handler.DataHandler;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class LoggingServletTest {

	LoggingServlet loggingServlet;
	DataHandler dataHandler;
	
	@Mock HttpServletRequest requestMock;
	@Mock HttpServletResponse responseMock;
	@Mock ServletContext servletContextMock;
	private LogManager logManager;
	private Logger log1 = Logger.getLogger("log1");
	private Logger log2 = Logger.getLogger("log2");
	private Logger log3 = Logger.getLogger("log3");
	
	private LoggersFilter loggersFilter = new LoggersFilter("log");
	
	public LoggingServletTest() {
		MockitoAnnotations.initMocks(this);
		
		loggingServlet = new LoggingServlet();
		dataHandler = new DataHandler();
		loggingServlet.dataHandler = dataHandler;
		loggingServlet.loggersFilter = loggersFilter;
	}
	
	@Before
	public void setup(){
		logManager = LogManager.getLogManager();
		logManager.addLogger(log1);
		logManager.addLogger(log2);
		logManager.addLogger(log3);
		
		loggingServlet.dataHandler.setLoggersForLogManager(logManager);
	}
	
	@Ignore
	@Test
	public void shouldSortLoggers() throws ServletException, IOException{
		loggingServlet.doGet(requestMock, responseMock);
	}
}
