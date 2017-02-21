<%--
  Created by IntelliJ IDEA.
  User: student
  Date: 2/14/2017
  Time: 11:25 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <%@include file="/WEB-INF/view/themplate/header.jsp"%>
</head>

<body>
<div class="container wr-main">
    <h1>Edit Student: <c:out value="${student.firstName} ${student.lastName}"></c:out></h1>
<form action="${pageContext.request.contextPath}/editStudent" method="post" commandName="editStudent" enctype="multipart/form-data">
    <div class="row">
        <div class="col-xs-6">
            <input type="hidden" name="id" value="<c:out value="${student.id}"></c:out>"/>
            <div class="form-group ">
                <label>First Name: </label> <input class="input-student form-control" path="person.firstName" name="firstName"
                                                   value="<c:out value="${student.firstName}"/>"/>
            </div>
            <div class="form-group ">
                <label>last Name: </label> <input class="input-student form-control" path="person.lastName" name="lastName"
                                                  value="<c:out value="${student.lastName}"/>"/>
            </div>
            <div class="form-group ">
                <label>Date of Birth: </label> <input class="input-student form-control" type="date" path="person.dob" name="dob"
                                                      value="<c:out value="${student.dob}"/>"/>
            </div>
            <div class="form-group ">
                <c:choose>
                    <c:when test="${student.gender == 'Male'}">
                        <label for="radio" class="radio-l"> <input type="radio" CLASS="radio" id="radio" path="person.gender" name="gender" value="Male" checked>Male</label>
                        <label for="radio" class="radio-l"> <input type="radio" CLASS="radio" id="radio" path="person.gender" name="gender" value="Female">Female</label>
                    </c:when>
                    <c:otherwise>
                        <label for="radio" class="radio-l"> <input type="radio" CLASS="radio" id="radio" path="person.gender" name="gender" value="Male">Male</label>
                        <label for="radio" class="radio-l"> <input type="radio" CLASS="radio" id="radio" path="person.gender" name="gender" value="Female"
                                              checked>Female</label>
                    </c:otherwise>
                </c:choose>
            </div>
            <div class="form-group ">
                <input type="hidden" name="idAddress" value="<c:out value="${student.address.id}"></c:out>"/>
                <label>Country: </label> <input class="input-student form-control" path="person.address.country" name="country"
                                                value="<c:out value="${student.address.country}"/>"/>
            </div>
        </div>
        <div class="col-xs-6">
            <div class="form-group ">
                <label>City: </label> <input class="input-student form-control" path="person.address.city" name="city"
                                             value="<c:out value="${student.address.city}"/>"/>
            </div>
            <div class="form-group ">
                <label>Addres: </label> <input class="input-student form-control" path="person.address.address" name="address"
                                               value="<c:out value="${student.address.address}"/>"/>
            </div>
            <div class="form-group ">
                <label>Phone(s): </label>
                <c:forEach items="${phones}" var="phone">
                    <select name="phoneType[]" id="" class="select-student">
                        <c:forEach items="${phoneType}" var="type">
                            <c:choose>
                                <c:when test="${phone.phoneType == type}">
                                    <option value="<c:out value="${type}"/>" selected><c:out value="${type}"/></option>
                                </c:when>
                                <c:otherwise>
                                    <option value="<c:out value="${type}"/>"><c:out value="${type}"/></option>
                                </c:otherwise>
                            </c:choose>
                        </c:forEach>
                    </select>
                    <label>Phone's number: </label>
                    <input hidden name="id_phone[]" placeholder="Phone number" value="<c:out value="${phone.id}"/>"/>
                    <input path="phone.number" name="phone[]" class="input-student form-control" placeholder="Phone number"
                           value="<c:out value="${phone.number}"/>"/>
                </c:forEach>
            </div>
            <div class="form-group ">
                <label>Group nr. </label>
                <select name="group" id="group" class="select-student">
                    <c:forEach items="${groups}" var="group">
                        <%--<option value="<c:out value="${group.id}"/>"><c:out value="${group.name}"/></option>--%>
                        <option value="<c:out value="${group.id}"/>" ${student.group.id == group.id ? "selected" : ""}>${group.name}</option>
                    </c:forEach>
                </select>
            </div>
        </div>
        <div class="fileUpload btn-student">
            <span>Upload</span>
            <input type="file" value="Upload Student image" name="file" class="upload">
            <input type="hidden" value="${student.imageAddress}" name="image-root">
        </div>
        <div class="form-group ">
            <input type="submit" value="submit" class="btn-student mt-25">
        </div>
    </div>
</form>
</div>
</body>
</html>
