package logging.web.jsf.converter;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import logging.web.jsf.constants.Messages;

@FacesConverter("LevelConverter")
public class LevelConverter implements Converter {
	Logger logger = Logger.getLogger(LevelConverter.class.getName());
	
	@Override //this method is called in post processing rather
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String arg2) {
		
		logger.log(Level.FINEST, "converter: logger level : " + arg2);
		
		if (arg2.equals(Messages.EMPTY_LEVEL_MSG)){
			logger.log(Level.INFO, "converter: returning : null");
			return null;
		} else {
			Level level = Level.parse(arg2);
			logger.log(Level.INFO, "converter: returning : " + level);
			return level;
		} 
	}
	
	@Override //this method is used to display values into UI (pre processing)
	public String getAsString(FacesContext arg0, UIComponent arg1, Object arg2) {
		if (arg2 != null){
			logger.log(Level.FINEST, "converter: returning : as string: " + arg2.toString());
			return arg2.toString();
		} else {
			return Messages.EMPTY_LEVEL_MSG;
		}
	}

}
