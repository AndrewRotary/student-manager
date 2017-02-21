<%--
  Created by IntelliJ IDEA.
  User: student
  Date: 2/14/2017
  Time: 9:23 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Title</title>
    <%@include file="/WEB-INF/view/themplate/header.jsp"%>
</head>
<body>
<div class="container wr-main">
    <h1><c:out value="${student.firstName} ${student.lastName}"></c:out> - student subscription</h1>
    <form action="${pageContext.request.contextPath}/editLibrarystatus" method="post" commandName="editLibraryStatus">
        <input type="hidden" name="id" value="<c:out value="${student.librarySubscription.id}"></c:out>"/>
        <div class="form-group">
            <label for="">Status</label>
            <select name="status" id="" class="select-student">
                <c:forEach items="${statuses}" var="status">
                    <option value="<c:out value="${status}"/>"><c:out value="${status}"></c:out> </option>
                </c:forEach>
            </select>
        </div>
        <div class="form-group">
            <label>Start Date: </label> <input type="date" class="input-student form-control"  path="" name="startDate"/>
        </div>
        <div class="form-group">
            <label>End Date: </label> <input type="date" class="input-student form-control"  path="" name="endDate"/>
        </div>
        <input type="submit" value="Save" class="btn-student mt-25">
    </form>
</div>

</body>
</html>
