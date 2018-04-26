<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="/css/style2.css">
<title><c:out value="${event.name }"/></title>
</head>
<body>
	<a href="/dashboard">Dashboard</a>
	<div>
		<h1><c:out value="${event.name }"/></h1>
		<p>Date: <fmt:formatDate value="${event.date}" pattern="MM-dd-yyyy"/></p>
		<p>Location: <c:out value="${event.location }, ${event.state }"/></p>
		<p>People who are attending this event: <c:out value="${event.user.size() }"/></p>
	</div>
	<div class = "table">
		<table class="table">
			<tr>
				<th>Name</th>
				<th>Location</th>
			</tr>
			<c:forEach items="${users }" var="user">
			<tr>
				<td><c:out value="${user.firstName } ${user.lastName }"/></td>
				<td><c:out value="${user.location }"/></td>
			</tr>
			</c:forEach>
		</table>
	</div>
	<div id="message">
		<c:forEach items="${messages }" var="message">
			<p><span><c:out value="${message.users[0].firstName} ${message.users[0].lastName }"/>:</span>  <c:out value="${message.comment }"/></p>
			====================
		</c:forEach>
	</div>
	<div class="form">
		<form:form method="POST" action="/message/new/${event.id }" modelAttribute="new">
			<p><form:textarea rows="4" cols="50" path="comment"/><br>
			<form:errors path="comment"/>
			<form:label path="comment">Add Message:</form:label>
			</p>
			<input type="submit" value="New Comment"/>
		</form:form>
		<div class="errs">
    		<% if(request.getAttribute("errs") != null) { %>
	  		<fieldset>
	  			<legend>Errors</legend>
	  			<c:forEach items="${errs}" var="err">
					<p><c:out value="${err.getDefaultMessage()}"/></p>
				</c:forEach>
	  		</fieldset>
	  	<% } %>
    </div>
	</div>
</body>
</html>