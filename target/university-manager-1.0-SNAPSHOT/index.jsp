<%@ page language="java" contentType="text/html; charset=US-ASCII" pageEncoding="US-ASCII"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<body>
<h2>Hello World!</h2>
asd
<c:out value="${student.id}"></c:out>
<c:forEach items="${studentList}" var="student" >
Item <c:out value="${student}"/><p>
    </c:forEach>
</body>
</html>
