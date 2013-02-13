<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.logging.Logger" %> 
<%@ page import="java.util.logging.Level" %>
<%@ page import="java.util.Set" %> 
<%@ page import="java.util.Enumeration" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
<%
	Logger pagelogger = Logger.getLogger("logging.web.jsp.loggingStat.jsp");
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
	<jsp:useBean id="LoggingPage" class="logging.web.jsp.backend.LoggingPage" scope="session"/>
	
	all loggers size :<%= LoggingPage.getLoggers().size() %>
	<BR/>
	filtered loggers size :<%= loggersAttrSize %>
	<BR/>	
	page logger level : <%= pagelogger.getLevel() %>
	<BR/>
	all levels size : <%= levelsAttrSize %>
	<BR/><BR/>
	<BR/><BR/><BR/><BR/><BR/><BR/>
	request data :<BR/>
	<table width="30%" border="0" align="left" >
		<tr bgcolor="#f0fff">
			<th>Param Name</th>
			<th>Param Value(s)</th>
		</tr>
		<%
			Enumeration<String> paramNames = request.getParameterNames();

			while (paramNames.hasMoreElements()) {
				String paramName =  paramNames.nextElement();
				out.print("<tr><td>" + paramName + "</td>\n");
				String paramValue = request.getParameter(paramName);
				out.println("<td> " + paramValue + "</td></tr>\n");
			}
		%>
	</table>
	
	<table width="50%" border="0" align="bottom">
		<tr bgcolor="#f0fff">
			<th>Attr Name</th>
			<th>Attr Value(s)</th>
		</tr>
		<%
			Enumeration<String> attrNames = request.getAttributeNames();

			while (attrNames.hasMoreElements()) {
				String attrName =  attrNames.nextElement();
				out.print("<tr><td>" + attrName + "</td>\n");
				String paramValue = request.getParameter(attrName);
				out.println("<td> " + paramValue + "</td></tr>\n");
			}
		%>
	</table>
<BR/>
	session data :<BR/>
	<table width="60%" border="0" align="left">
		<tr bgcolor="#f0fff" >
			<th>Attr Name</th>
			<th>Attr Value(s)</th>
		</tr>
		<%
			Enumeration<String> sessNames = session.getAttributeNames();

			while (sessNames.hasMoreElements()) {
				String sessName =  sessNames.nextElement();
				out.print("<tr><td>" + sessName + "</td>\n");
				String paramValue = request.getParameter(sessName);
				out.println("<td> " + paramValue + "</td></tr>\n");
			}
		%>
	</table>
</body>
</html>