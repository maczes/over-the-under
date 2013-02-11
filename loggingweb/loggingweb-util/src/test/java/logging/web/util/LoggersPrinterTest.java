package logging.web.util;

import java.util.Comparator;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.logging.Logger;

import logging.web.util.LoggersPrinter;

import org.junit.Before;
import org.junit.Test;


public class LoggersPrinterTest {
	
	private LoggersPrinter loggersPrinter;
	private Logger log3 = Logger.getLogger("log3");
	private SortedSet<Logger> loggers = new TreeSet<Logger>(new Comparator<Logger>() {
		@Override
		public int compare(Logger o1, Logger o2) {
			return o1.getName().compareTo(o2.getName());
		}
	});
	
	public LoggersPrinterTest() {
		loggersPrinter = new LoggersPrinter();
	}
	
	@Before
	public void setup(){
		loggers.add(log3);
	}
	
	@Test
	public void shouldPrint(){
		loggersPrinter.printNames(loggers);
	}

}
