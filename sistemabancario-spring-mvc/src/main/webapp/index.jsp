<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="com.sistemabancario.model.Banco" %>
<%@ page import="com.sistemabancario.model.Cuenta" %>
<%@ page import="com.sistemabancario.model.Cliente" %>
<%@ page import="java.util.List" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Sistema bancario</title>
</head>
<body>
	<h1>Sistema Bancario</h1>
	<a href="cuenta/consultar" >Redireccionar a Servlet CUENTAS</a><br>
	<a href="cliente/consultar">Redireccionar a Servlet CLIENTES</a><br>
	<a href="banco/consultar">Redireccionar a Servlet BANCOS</a><br>
	<a href="cliente/infoGeneral">Detalles generales</a><br>
	<a href="cliente/form">Crear informacion general</a>
	
</body>
</html>