<%--
  Created by IntelliJ IDEA.
  User: student
  Date: 2/15/2017
  Time: 1:56 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Title</title>
    <%@include file="/WEB-INF/view/themplate/header.jsp" %>
</head>
<body>
<div class="container wr-main">
    <h1>Add marks </h1>
    <form action="${pageContext.request.contextPath}/addMark" method="post" commandName="editLibraryStatus">
        <input type="hidden" name="id" value="<c:out value="${student.id}"></c:out>"/>
        <div class="form-group">
            <label for="">Discipline</label>
            <select name="discipline" id="" class="select-student">
                <c:forEach items="${disciplines}" var="discipline">
                    <option value="<c:out value="${discipline.id}"/>"><c:out
                            value="${discipline.title}"></c:out></option>
                </c:forEach>
            </select>
        </div>
        <div class="form-group">
            <label for="">Profesor</label>
            <select name="professors" id="" class="select-student">
                <c:forEach items="${professors}" var="professor">
                    <option value="<c:out value="${professor.id}"/>"><c:out
                            value="${professor.firstName} ${professor.lastName}"></c:out></option>
                </c:forEach>
            </select>
        </div>
        <div class="form-group">
            <label>Mark: </label> <input type="text" class="input-student form-control" path="" name="mark"/>
        </div>

        <input type="submit" value="Add" class="btn-student mt-25">
    </form>
</div>

</body>
</html>
