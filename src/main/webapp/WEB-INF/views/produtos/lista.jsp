<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" 
    prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>

    
<!DOCTYPE html>
<html>
<head>
<!-- Url Relativa -->
<c:url value="/resources/css" var="cssPath" />
<link rel="stylesheet" href="${cssPath }/bootstrap.min.css">
<link rel="stylesheet" href="${cssPath }/bootstrap-theme.min.css">
<!-- Latest compiled and minified JavaScript -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js" integrity="sha384-0mSbJDEHialfmuBBQP6A4Qrprq5OVfW37PRR3j5ELqxss1yVqOtnepnHVP9aJ7xS" crossorigin="anonymous"></script>
<meta charset="UTF-8">
<link rel="stylesheet" href="http://netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css" />
<link rel="stylesheet" href="http://netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap-theme.min.css" />
<link rel="stylesheet" href="http://code.jquery.com/ui/1.10.3/themes/smoothness/jquery-ui.css" />

<script src="http://code.jquery.com/jquery-1.9.1.js"></script>
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>

<script src="http://netdna.bootstrapcdn.com/bootstrap/3.0.0/js/bootstrap.min.js"></script>
<script src="http://code.jquery.com/ui/1.10.3/jquery-ui.js"></script>

 
<title>Livros de java, Android, Iphone, PHP, Ruby e muito mais - Casa do código</title>
</head>
<body>

<nav class = "navbar navbar-default" role = "navigation">
   
   <div class = "navbar-header">
      <a class = "navbar-brand" href = "#">Casa do código</a>
   </div>
   
   <div>
      <ul class = "nav navbar-nav">
       
		
         <li class = "dropdown">
            <a href = "#" class = "dropdown-toggle" data-toggle = "dropdown">
               Produtos 
               <b class = "caret"></b>
            </a>
            
            <ul class = "dropdown-menu">
               <li><a href = "${s:mvcUrl('PC#listar').build() }">Produtos</a></li>
               <li><a href = "${s:mvcUrl('PC#form').build() }">Cadastro Produtos</a></li>
            </ul>
         </li>
         
         <li class="nav navbar-nav">
         	<ul class="nav navbar-nav navbar-right">
         		  <li><a href = "#"> 
         		  Olá	<sec:authentication property="principal.username"/>
         		  </a></li>
         	</ul>
         </li>
         
        <%--   <li class="nav navbar-nav">
         	<ul class="nav navbar-nav navbar-right">
         		  <li><a href = "#"> 
         		  <sec:authentication property="principal" var="usuario"/>
         		  Usuário: ${usuario.username }
         		  </a></li>
         	</ul>
         </li> --%> 
			
      </ul>
   </div>
   
</nav>
 	
 		<div class="container">
	   		<h1>Lista de Produtos</h1>
		  
		  <div class="form-group">
		   		
		   		<div>${sucesso }</div>
		   		<div>${falha }</div>
			   		 <table class="table-bordered table-striped table-hover">
			   			<tr>
			   				<th>Titulo</th>
			   				<th>Descrição</th>
			   				<th>Páginas</th>
			   			</tr>
			   			<c:forEach items="${produtos }" var="produto">
			   			<tr>
			   				<td>
			   				<a href="${s:mvcUrl('PC#detalhe').arg(0,produto.id).build() }">${produto.titulo }</a>
			   				</td>
			   				<td>${produto.descricao }</td>
			   				<td>${produto.paginas }</td>
			   			</tr>			
			   			</c:forEach>
			   			</table>
			   			
			   	</div>
   		</div>
   	
</body>
</html>