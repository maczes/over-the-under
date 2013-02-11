<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.logging.Logger" %> 
<%@ page import="java.util.logging.Level" %>
<%@ page import="java.util.Set" %> 
<%@ page import="logging.web.ejb.bean.DataHandler" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core"%>
  

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
<%  
	Logger pagelogger = Logger.getLogger("loggingUtil.jsp");
	int loggersAttrSize = 0;  
	int levelsAttrSize = 0;  
	
	@SuppressWarnings("unchecked")
	Set<Logger> loggersAttr = (Set<Logger>)request.getSession().getAttribute("loggers"); 
	Level[] levelsAttr =  (Level[])request.getSession().getAttribute("levels");

    if (loggersAttr != null){
    	loggersAttrSize = loggersAttr.size();
    	pagelogger.setLevel(Level.INFO);
    	pagelogger.log(Level.INFO, "jsp:logging: loggersAttr size: " + loggersAttrSize);
    }
    
    if (levelsAttr != null){
    	levelsAttrSize = levelsAttr.length;
    	pagelogger.log(Level.INFO, "jsp:logging: levelsAttr size: " + loggersAttrSize);  		
    }
%>  
 	
	<jsp:useBean id="dataHandler" class="logging.web.ejb.bean.DataHandler" scope="session"/> 

	all loggers size :<%= dataHandler.getLoggers().size() %>
	<BR>
	filtered loggers size :<%= loggersAttrSize %>
	<BR>	
	page logger level : <%= pagelogger.getLevel() %>
	<BR>
	all levels size : <%= levelsAttrSize %>
</body>
</html>