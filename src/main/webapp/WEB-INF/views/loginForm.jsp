<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" 
    prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="s"%>

<!DOCTYPE html>
<html>
<head>
 
 </style>
<!-- Url Relativa -->
<c:url value="/resources/css" var="cssPath" />
<link rel="stylesheet" href="${cssPath }/bootstrap.min.css">
<link rel="stylesheet" href="${cssPath }/bootstrap-theme.min.css">

<script src="http://code.jquery.com/jquery-1.9.1.js"></script>
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>

<script src="http://netdna.bootstrapcdn.com/bootstrap/3.0.0/js/bootstrap.min.js"></script>
<script src="http://code.jquery.com/ui/1.10.3/jquery-ui.js"></script>


<meta charset="UTF-8">
<title>Livros de java, Android, Iphone, PHP, Ruby e muito mais - Casa do código</title>
</head>
<body>


	<div class="container">
	<h1>Login da Casa do Código</h1>
	
	<form:form  servletRelativeAction="/login" method="post">
        <div class="form-group">
            <label>E-mail</label>
			<input name="username" type="text" class="form-control">
        </div>
        <div class="form-group">
            <label>Senha</label>
			<input name="password" type="text" class="form-control">
        </div>
        <button type="submit" class="btn btn-primary" style="margin-top:10px">
            Login
        </button>
	</form:form>
	</div>
</body>
</html>