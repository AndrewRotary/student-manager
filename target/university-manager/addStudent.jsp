<%--
  Created by IntelliJ IDEA.
  User: student
  Date: 2/13/2017
  Time: 2:16 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <%@include file="/WEB-INF/view/themplate/header.jsp" %>
</head>
<body>
<div class="container wr-main">
    <h1>Add Student</h1>
    <div class="row">
        <form action="${pageContext.request.contextPath}/addStudentController" method="post" commandName="addStudent" enctype="multipart/form-data">
            <div class="col-xs-6">
                <div class="form-group">
                    <label>First Name: </label> <input path="person.firstName" class="input-student form-control" name="firstName"/>
                </div>
                <div class="form-group">
                    <label>last Name: </label> <input path="person.lastName" class="input-student form-control" name="lastName"/>
                </div>
                <div class="form-group">
                    <label>Date of Birth: </label> <input type="date" path="person.dob" class="input-student form-control" name="dob"/>
                </div>
                <div class="form-group">
                    <label for="" class="radio-l"> <input type="radio" path="person.gender" CLASS="radio" name="gender" value="Male">male</label>
                    <label for="" class="radio-l"> <input type="radio" path="person.gender" CLASS="radio" name="gender" value="Female">Female</label>

                </div>
                <div class="form-group">
                    <label>Country: </label> <input path="person.address.country" class=" input-student form-control" name="country"/>
                </div>
            </div>
            <div class="col-xs-6">
                <div class="form-group">
                    <label>City: </label> <input path="person.address.city" class="input-student form-control" name="city"/>
                </div>
                <div class="form-group">
                    <label>Addres: </label> <input path="person.address.address" class="input-student form-control" name="address"/>
                </div>
                <div class="form-group">
                    <label>Phone(s): </label>
                    <select name="phoneType" id="" class="select-student">
                        <c:forEach items="${phoneType}" var="type">
                            <option value="<c:out value="${type}"/>"><c:out value="${type}"/></option>
                        </c:forEach>
                    </select>
                    <label>Phone's number: </label>
                    <input path="person.phone.number" class="input-student form-control" name="phone" placeholder="Phone number"/>
                </div>
                <div class="form-group">
                    <label>Group nr. </label>
                    <select name="group" id="group" class="select-student">
                        <c:forEach items="${groups}" var="group">
                            <option value="<c:out value="${group.id}"/>"><c:out value="${group.name}"/></option>
                        </c:forEach>
                    </select>
                </div>
            </div>
            <div class="fileUpload btn-student">
                <span>Upload</span>
                <input type="file" value="Upload Student image" name="file" class="upload">
            </div>
            <input type="submit" value="submit" class="btn-student mt-25">
        </form>
    </div>
</div>
</body>
</html>
