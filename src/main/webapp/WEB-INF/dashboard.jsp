<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="./css/style.css">
<title>Dashboard</title>
</head>
<body>
	<div class="header">
		<h1>Welcome <c:out value="${currentUser.firstName }"/>!</h1>
	</div>
		<form id="logoutForm" method="POST" action="/logout">
        	<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        	<input id="submit" type="submit" value="Logout!" />
    	</form>
	<div>
		<h3>Here are some of the events in your state:</h3>
		<table>
		  <tr>
		    <th>Name</th>
		    <th>Date</th>
		    <th>Location</th>
		    <th>Host</th>
		    <th>Action/Status</th>
		  </tr>
		  <c:forEach items="${events }" var="event" varStatus="loop">
		  <tr>
		    <td><a href="/event/${event.id }"><c:out value="${event.name }"/></a></td>
		    <td><fmt:formatDate value="${event.date}" pattern="MM-dd-yyyy"/></td>
		    <td><c:out value="${event.location }"/></td>
		    <td><c:out value="${event.host }"/></td>
		    <c:choose>
		    <c:when test= "${event.host == currentUser.firstName }">
		    <td><a href="/delete/event/${event.id }">Delete</a> | <a href="/edit/event/${event.id }">Edit</a></td>
		    </c:when>
		    <c:when test = "${event.user[loop.index].firstName == currentUser.firstName}">
		    	<td>Cancel</td>
		    </c:when>
		    <c:otherwise><td><a href="/join/event">Join Event</a></td></c:otherwise>
		    </c:choose>
		  </tr>
		  </c:forEach>
		</table>
	</div>
	<div>
		<h3>Here are some of the events in other states</h3>
		<table>
		  <tr>
		    <th>Name</th>
		    <th>Date</th>
		    <th>Location</th>
		    <th>Host</th>
		    <th>Action</th>
		  </tr>
		  <c:forEach items="${allEvents }" var="all" varStatus="loop">
		  <tr>
		    <td><a href="/event/${all.id }"><c:out value="${all.name }"/></a></td>
		    <td><fmt:formatDate value="${all.date}" pattern="MM-dd-yyyy"/></td>
		    <td><c:out value="${all.location }"/></td>
		    <td><c:out value="${all.host }"/></td>
		    <c:choose>
		    <c:when test= "${all.host == currentUser.firstName }">
		    <td><a href="/delete/event/${all.id }">Delete</a> | <a href="/edit/event/${all.id }">Edit</a></td>
		    </c:when>
		    <c:otherwise><td><a href="/join/event/${all.id }/${currentUser.id}">Join Event</a></td></c:otherwise>
		    </c:choose>
		  </tr>
		  </c:forEach>
		</table>
	</div>
	<div>
	<c:if test="${errorMessage != null}">
        <c:out value="${errorMessage}"></c:out>
    </c:if>
    	<h3>Create an Event:</h3>
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
		<form:form method="POST" action="/add/event/${currentUser.id }" modelAttribute="new">
        	<p>
            	<form:label path="name">Event Name:</form:label>
            	<form:errors path="name"/>
            	<form:input path="name"/>
        	</p>
        	<p>
            	<form:label path="date">Last Name:</form:label>
            	<form:errors path="date"/>
            	<form:input type="date" path="date"/>
        	</p>
        	<form:hidden value="${currentUser.firstName }" path="host"/>
        	<p>
            	<form:label path="location">Location:</form:label>
            	<form:input path="location"/>
            	<form:errors path="location"/>
        	<form:select path="state">
				<option value="AL">AL</option>
				<option value="AK">AK</option>
				<option value="AR">AR</option>	
				<option value="AZ">AZ</option>
				<option value="CA">CA</option>
				<option value="CO">CO</option>
				<option value="CT">CT</option>
				<option value="DC">DC</option>
				<option value="DE">DE</option>
				<option value="FL">FL</option>
				<option value="GA">GA</option>
				<option value="HI">HI</option>
				<option value="IA">IA</option>	
				<option value="ID">ID</option>
				<option value="IL">IL</option>
				<option value="IN">IN</option>
				<option value="KS">KS</option>
				<option value="KY">KY</option>
				<option value="LA">LA</option>
				<option value="MA">MA</option>
				<option value="MD">MD</option>
				<option value="ME">ME</option>
				<option value="MI">MI</option>
				<option value="MN">MN</option>
				<option value="MO">MO</option>	
				<option value="MS">MS</option>
				<option value="MT">MT</option>
				<option value="NC">NC</option>	
				<option value="NE">NE</option>
				<option value="NH">NH</option>
				<option value="NJ">NJ</option>
				<option value="NM">NM</option>			
				<option value="NV">NV</option>
				<option value="NY">NY</option>
				<option value="ND">ND</option>
				<option value="OH">OH</option>
				<option value="OK">OK</option>
				<option value="OR">OR</option>
				<option value="PA">PA</option>
				<option value="RI">RI</option>
				<option value="SC">SC</option>
				<option value="SD">SD</option>
				<option value="TN">TN</option>
				<option value="TX">TX</option>
				<option value="UT">UT</option>
				<option value="VT">VT</option>
				<option value="VA">VA</option>
				<option value="WA">WA</option>
				<option value="WI">WI</option>	
				<option value="WV">WV</option>
				<option value="WY">WY</option>
		</form:select>
		</p>
        	<input type="submit" value="Register!"/>
    	</form:form>
	</div>

</body>
</html>