<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<link type="text/css"
    href="css/jquery-ui.min.css" rel="stylesheet" />
<script type="text/javascript" src="js/jquery-1.7.1.min.js"></script>
<script type="text/javascript" src="js/jquery-ui-1.8.18.custom.min.js"></script>
<title>Add new user</title>
</head>
<body>
    <script>
        $(function() {
            $("#calPicker").datepicker();
        });
    </script>

    <form method="POST" action='UserController' name="frmAddUser" enctype="multipart/form-data">
        User ID : <input type="text" readonly="readonly" name="userid"
            value="<c:out value="${users.userId}" />" /> <br /> 
        User Image:
        			<input type="file" name="userIamge" value="<c:out value="${users.userIamge}" />" /><br/>
        First Name : <input
            type="text" name="firstName"
            value="<c:out value="${users.firstName}" />" /> <br /> 
        Last Name : <input
            type="text" name="lastName"
            value="<c:out value="${users.lastName}" />" /> <br /> 
        DOB : <input id="calPicker"
            type="text" name="dob"
            value="<fmt:formatDate pattern="MM/dd/yyyy" value="${users.dateOfBirth}" />" /> <br /> 
        Email : <input type="text" name="email"
            value="<c:out value="${users.userEmail}" />" /> <br /> <input
            type="submit" value="Submit" />
    </form>
</body>
</html>