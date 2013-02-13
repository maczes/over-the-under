package logging.web.util;

import java.util.Comparator;
import java.util.logging.Logger;

public class LoggersComparator implements Comparator<Logger> {
	@Override
	public int compare(Logger o1, Logger o2) {
		return o1.getName().compareTo(o2.getName());
	}
}
