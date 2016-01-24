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

<nav class = "navbar navbar-default" role = "navigation">
   
   <div class = "navbar-header">
      <a class = "navbar-brand" href = "#">Casa do código</a>
   </div>
   
   <div>
      <ul class = "nav navbar-nav">
       
			
         <li class = "dropdown">
            <a href = "#" class = "dropdown-toggle" data-toggle = "dropdown">
               Java 
               <b class = "caret"></b>
            </a>
            
            <ul class = "dropdown-menu">
               <li><a href = "${s:mvcUrl('PC#listar').build() }">Produtos</a></li>
               <li><a href = "${s:mvcUrl('PC#form').build() }">Cadastro Produtos</a></li>
            </ul>
            
         </li>
			
      </ul>
   </div>
   
</nav>
		
	<div class="container">
	<h1>Cadastro de Produto</h1>
	
	<form:form  action="${s:mvcUrl('PC#gravar').build()}" method="post"
	commandName="produto" enctype="multipart/form-data"  >
        <div class="form-group">
            <label>Título</label>
            <form:input path="titulo" cssClass="form-control"/>
            <form:errors path="titulo" />
        </div>
        <div>
            <label>Descrição</label>
            <form:textarea rows="10" cols="20" path="descricao"  cssClass="form-control"/>
            <form:errors path="descricao" />
        </div>
        <div>
            <label>Páginas</label> 
            <form:input type="text" path="paginas" cssClass="form-control" />
            <form:errors path="paginas" />
        </div>

        <div>
            <label>Data de lançamento</label>
             <form:input path="dataLancamento"  cssClass="form-control"/>
            <form:errors path="dataLancamento" />
        </div>
         

        <c:forEach items="${tipos}" var="tipoPreco" varStatus="status">
            <div>
                <label>${tipoPreco}</label> 
                <form:input
                    path="precos[${status.index}].valor" cssClass="form-control" />
                 <form:input type="hidden"
                    path="precos[${status.index}].tipo" value="${tipoPreco}"  cssClass="form-control"  />
            </div>
        </c:forEach>
        
        <div>
        	<label>Sumário</label>
        	<input name="sumario" type="file" class="form-control">
        </div>

        <button type="submit" class="btn btn-primary">
            Cadastrar
        </button>
	</form:form>
	</div>
</body>
</html>