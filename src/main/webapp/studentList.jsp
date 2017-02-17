<%--
  Created by IntelliJ IDEA.
  User: student
  Date: 2/10/2017
  Time: 8:43 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--<%@ page language="java" contentType="text/html; charset=US-ASCII" pageEncoding="US-ASCII"%>--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Practica</title>
</head>
<%@include file="/WEB-INF/view/themplate/header.jsp" %>
<body>
<div class="col-xs-10 col-xs-offset-1 wr-main">
    <h1>Search Student by:</h1>
    <form action="${pageContext.request.contextPath}/StudentController" method="get" class="row">
        <div class="col-xs-6">
            <input type="hidden" name="id" value="<c:out value="${student.id}"></c:out>"/>
            <%--<div class="form-group ">--%>
                <%--<label>First Name: </label> <input class="input-student form-control" path="person.firstName" name="firstName"--%>
                                                   <%--value=""/>--%>
            <%--</div>--%>
            <div class="form-group ">
                <label>last Name: </label> <input class="input-student form-control" path="person.lastName" name="lastName"/>
            </div>
            <div class="form-group ">
                <label>Addres: </label> <input class="input-student form-control" path="" name="address"value=""/>
            </div>
            <div class="form-group ">
                <label>Group nr. </label>
                <select name="group" id="group" class="select-student">
                    <option disabled selected>none</option>
                    <c:forEach items="${groups}" var="group">
                        <%--<option value="<c:out value="${group.id}"/>"><c:out value="${group.name}"/></option>--%>
                        <option value="<c:out value="${group.id}"/>">${group.name}</option>
                    </c:forEach>
                </select>
            </div>
            <div class="form-group ">
                <label for="" class="mt-41">Gender: </label>
                 <input type="radio" CLASS="radio" id="radio" name="gender" value="Male"> Male
                <input type="radio" CLASS="radio" id="radio" name="gender" value="Female"> Female
                <input type="radio" CLASS="radio" id="radio" name="gender" value="All"> All
            </div>
            <a href="${pageContext.request.contextPath}/StudentController" class="btn-student mt-25">Reset</a>
        </div>
        <div class="col-xs-6">
            <div class="form-group ">
                <label>Date of Birth: </label> <input class="input-student form-control" type="date" path="person.dob" name="dob"
                                                      value=""/>
            </div>
            <div class="form-group ">
                <label>End Date </label> <input class="input-student form-control" type="date" path="person.dob" name="dob"
                                                value=""/>
            </div>
            <div class="form-group">
                <label for="">Discipline</label>
                <select name="discipline" id="" class="select-student">
                    <option selected disabled>none</option>
                    <c:forEach items="${disciplines}" var="discipline">
                        <option value="<c:out value="${discipline.id}"/>"><c:out value="${discipline.title}"></c:out> </option>
                    </c:forEach>
                </select>
            </div>
            <div class="form-group ">
                <label>Total Average: </label> <input class="input-student form-control" path="" name="average" value=""/>
            </div>
            <input type="submit" value="Search" name="search" class="btn-student mt-25">
        </div>
    </form>
    <div class="row">
    <h1 class="mt-41">Student List</h1>
    <form action="${pageContext.request.contextPath}/StudentController" method="post" commandName="deleteStudent">
    <table class="table-student table table-striped">
        <th>Delete</th>
        <th>Picture</th>
        <th>Name</th>
        <th>Birth Day</th>
        <th>Gender</th>
        <th>Address</th>
        <th>Phone</th>
        <th>Library</th>
        <th>Marks</th>
        <th>Action</th>
        <c:forEach items="${students}" var="student">
            <tr>
                <td><input type="checkbox" id="st-delete" name="delete[]" value="<c:out value="${student.id}"/>"></td>
                <td class="">
                    <img width="75" height="75" src="<c:out value="/resources/images/${student.imageAddress}"/>" alt="">
                </td>
                <td>
                    <c:out value="${student.firstName} ${student.lastName}"/>
                </td>
                <td>
                    <c:out value="${student.dob}"/>
                </td>
                <td>
                    <c:out value="${student.gender}"/>
                </td>
                <td>
                    <c:out value="${student.address.address}"/>
                </td>
                <td>
                    <c:forEach items="${student.phones}" var="phone">
                        <c:out value="${phone.number}"/>
                        <c:out value="${phone.phoneType}"/>
                    </c:forEach>
                </td>
                <td>
                    Status: <c:out value="${student.librarySubscription.status}"/> <a
                        href="${pageContext.request.contextPath}/editLibrarystatus?id=${student.id}"
                        class="btn-student"><i class="glyphicon glyphicon-pencil"></i></a>
                </td>
                <td>
                    <c:forEach items="${student.marks}" var="mark">
                        <div>
                            <c:out value="${mark.discipline.title} : "/>
                            <c:out value="${mark.mark}"/>
                        </div>
                    </c:forEach>
                </td>
                <td>
                    <a href="${pageContext.request.contextPath}/editStudent?id=${student.id}" class="btn-student"><i
                            class="glyphicon glyphicon-pencil"></i></a>
                    <a href="${pageContext.request.contextPath}/addMark?id=${student.id}" class="mt-20 btn-student">Add
                        Mark</a>
                </td>
            </tr>
        </c:forEach>
    </table>
        <div class="row">
            <div class="col-xs-6">
                <button type="submit" class="btn-delete btn-student mt-25">Deleete Student(s)</button>
            </div>
            <div class="col-xs-6">
                <a href="${pageContext.request.contextPath}/addStudentController" class="btn-student mt-25">Add student</a>
            </div>
        </div>
    </form>
    </div>
</div>
</body>
</html>
