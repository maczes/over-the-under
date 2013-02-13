<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.logging.Logger"  %> 
<%@ page import="java.util.logging.Level"  %>
<%@ page import="java.util.Set"  %> 

<%-- <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix ="c" %> --%> <!-- THIS DOeS NOT WORK!!! -->
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core"%>
  

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Log settings page</title>
</head>
<body>
<%
	Logger loggingpagelogger = Logger.getLogger("logging.jsp");
%>

 	<form id="submitForm" method="POST" action="">  
	<table>
		
		<c:forEach var="logger" items="${loggers}">
			<tr>
				<td><c:out value="${logger.name}"/></td> <%-- remember to add c:out !!! --%>
				<td>
				<c:choose>
					<c:when test="${not empty logger.level}"><c:out value="${logger.level}"/></c:when>
   					<%-- <c:otherwise>Not set</c:otherwise>  --%>
   				</c:choose>
				</td>
				<td>
					<select name='levelSelect'>
					    <c:forEach items="${levels}" var="level">
					    <c:choose>
							<c:when test="${logger.level == level}">
								<option value="${level}" selected>
									<c:out value="${level}"/>
								</option>
							</c:when>
							<c:when test="${logger.level != level}">
		   					    <option value="${level}">
	<%-- 	   						<c:choose>
		   							<c:when test="${empty logger.level}">
		   								<c:out value="Not set"/>
		   							</c:when>
		   							<c:otherwise> --%>
										<c:out value="${level}"/>
									<%-- </c:otherwise> 
								</c:choose> --%>
								</option>
		   					</c:when> 
		   				</c:choose>
					    </c:forEach>
					</select>
				</td>
			</tr>
		</c:forEach>
	</table>
	 <input type="Submit" name="cmdSet" value="Set">
	</form>
	<BR>
	
<%@include file="/WEB-INF/page/loggingUtil.jsp" %>
</body>
</html>