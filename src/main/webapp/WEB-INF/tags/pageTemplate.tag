<%@ tag language="java" pageEncoding="ISO-8859-1"%>
<%@ attribute name="titulo" required="true" %>
<%@ attribute name="bodyClass"  required="false"%>]
<%@ attribute name="extraScripts" fragment="true"%>

<!doctype html>
<html lang="pt-BR">
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
	<meta name="viewport" content="width=device-width">
	<link rel="shortcut icon" href="//cdn.shopify.com/s/files/1/0155/7645/t/189/assets/favicon.ico?1215741171247150012" type="image/ico"/>
	<link href="https://plus.google.com/108540024862647200608" rel="publisher">
	<link rel="alternate" type="application/json+oembed" href="http://www.casadocodigo.com.br/products/livro-javaee.oembed" />

	<script type="text/javascript" src="//cdn.shopify.com/s/assets/themes_support/ga_urchin_forms-0826cdefee6590321eb5c0c435eeebf7a8425a5493a9e95059c40e07e069ad52.js"></script>


	<!-- inject:css -->
	<link rel="stylesheet" href="//cdn.shopify.com/s/files/1/0155/7645/t/189/assets/style.css?1215741171247150012">
	<!-- endinject -->

	<link rel="canonical" href="http://www.casadocodigo.com.br/products/livro-javaee" />
	
	<title>${titulo } - Casa do Código</title>


</head>

<body class="${bodyClass }">

<jsp:invoke fragment="extraScripts"/>

 <%@include file="/WEB-INF/views/cabecalho.jsp" %>

<jsp:doBody/> 

 <%@include file="/WEB-INF/views/rodape.jsp" %>
 