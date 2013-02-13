package logging.web.jsp.servlet;

import java.io.IOException;
import java.util.SortedSet;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import logging.web.util.LoggersFilter;
import logging.web.util.LoggersPrinter;
import logging.web.util.handler.DataHandler;


//@WebServlet("/logging1/") //doesn't work! why?
public class LoggingServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger(LoggingServlet.class.getName());
	private static final String FILTER_EXPRESSION = "logging";
	
	@Inject
	DataHandler dataHandler;
	
	LoggersFilter loggersFilter = new LoggersFilter(FILTER_EXPRESSION);
	LoggersPrinter loggersPrinter = new LoggersPrinter();
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		logger.setLevel(Level.CONFIG); //just for test
		
		dataHandler.filter(loggersFilter);
		logger.log(Level.INFO, "TestServlet: loggers size: " + dataHandler.getLoggers().size());
		
		dataHandler.print(loggersPrinter);
		
		HttpSession session = request.getSession();
		session.setAttribute("loggers", dataHandler.getLoggers());
		//request.setAttribute("loggers", dataHandler.getLoggers());
		session.setAttribute("levels", dataHandler.getLevels());
		//request.setAttribute("levels", dataHandler.getLevels());
		
		getServletContext().getRequestDispatcher("/WEB-INF/page/logging1.jsp").forward(request, response);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		HttpSession session = request.getSession();
//		session.setAttribute("loggers", dataHandler.getLoggers());
		if (request.getAttribute("submitForm") != null){
		@SuppressWarnings("unchecked")
		SortedSet<Logger> so = (SortedSet<Logger>)request.getAttribute("submitForm");
		so.size();
		}
		getServletContext().getRequestDispatcher("/WEB-INF/page/logging1.jsp").forward(request, response);
	}

}