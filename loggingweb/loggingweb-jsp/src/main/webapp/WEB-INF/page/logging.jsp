<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.logging.Logger" %> 
<%@ page import="java.util.logging.Level" %>
<%@ page import="java.util.Set" %> 
<%@ page import="java.util.Enumeration" %>

<%-- <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix ="c" %> --%> <!-- THIS DOeS NOT WORK!!! -->
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core"%>
  

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Log settings page</title>
</head>
<body>

	<form id="submitForm" method="POST" action="#">  
	<table>
		
		<c:forEach var="logger" items="${LoggingPage.loggers}">
			<tr>
				<td><c:out value="${logger.name}"/></td> <%-- remember to add c:out !!! --%>
				<td>
				<c:choose>
					<c:when test="${not empty logger.level}">
						<c:out value="${logger.level}"/>
					</c:when>
   					<c:otherwise></c:otherwise>
   				</c:choose>
				</td>
				<td>
					<select name='levelSelect' id="levelSelect">
						<c:set var="isSelected" value="false" scope="request"/> 
							<!-- loop break stering variable (must break after filling combo
								 list in case of empty level.level -->
					    <c:forEach items="${LoggingPage.levels}" var="level">
					    <c:choose>
							<c:when test="${logger.level == level}">
								<option value="${level}" selected>
									<c:out value="${level}"/>
								</option>
								<c:set target="${logger}" property="level" value="${level}"/>
							</c:when>
							<c:when test="${not empty logger.level}">
								<option value="${level}"><c:out value="${level}"/></option>
							</c:when>
 							<c:otherwise>	
								<c:if test="${not isSelected}">
									<option value="${logger.level}" selected></option>
									<c:forEach items="${LoggingPage.levels}" var="levelas">
										<option value="${levelas}"><c:out value="${levelas}"/></option>
									</c:forEach>
									<c:set var="isSelected" value="true" scope="request"/>
								</c:if>
							</c:otherwise>
		   				</c:choose>
					    </c:forEach>
					</select>
				</td>
			</tr>
		</c:forEach>
	</table>
	 <input type="Submit" name="cmdSet" value="Submit">
	</form>
	<BR>
	
<%@include file="/WEB-INF/page/loggingStat.jsp" %> 
</body>
</html>