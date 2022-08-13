<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Detalles generales</title>
</head>
<body>
	<h1>DETALLES GENERALES</h1>
	<br>
	<h3>Filtrar clientes</h3>
	<br>
	<form:form method="POST" action="filter" modelAttribute="clienteFiltrar">
		Id Cliente:<form:input path="idCliente" type="text"/>
		Nombre:<form:input path="nombre" type="text"/>
		Apellido Paterno:<form:input path="aPaterno" type="text"/>
		Apellido Materno:<form:input path="aMaterno" type="text"/>
		<input type="submit" value="enviar"/>
	</form:form>
	<br>
	<h3>CLIENTES</h3>
	<br>
	<table border="2">
		<tr>
			<th>ID CLIENTE</th>
			<th>NOMBRE</th>
			<th>APELLIDO PATERNO</th>
			<th>APELLIDO MATERNO</th>
		</tr>
		<c:forEach items="${listaClientes}" var="clienteTemporal" >
			<tr>
				<td>${clienteTemporal.idCliente}</td>
				<td>${clienteTemporal.nombre}</td>
				<td>${clienteTemporal.aPaterno}</td>
				<td>${clienteTemporal.aMaterno}</td>
			</tr>
		</c:forEach>
	</table>
	<br>
	<h3>BANCOS</h3>
	<br>
	<table border="2">
		<tr>
			<th>ID</th>
			<th>NOMBRE</th>
			<th>DIRECCION</th>
		</tr>
		<c:forEach items="${listaBancos}" var="bancoTemporal">
			<tr>
				<td>${bancoTemporal.idBanco}</td>
				<td>${bancoTemporal.nombre}</td>
				<td>${bancoTemporal.direccion}</td>
			</tr>
		</c:forEach>
	</table>
	<br>
	<h3>CUENTAS</h3>
	<br>
	<table border="2">
		<tr>
			<th>ID</th>
			<th>NÚMERO DE CUENTA</th>
			<th>MONTO</th>
			<th>ID CLIENTE</th>
		</tr>
		<c:forEach items="${listaCuentas}" var="cuentaTemporal" >
			<tr>
				<td>${cuentaTemporal.idCuenta}</td>
				<td>${cuentaTemporal.numCuenta}</td>
				<td>${cuentaTemporal.monto}</td>
				<td>${cuentaTemporal.idCliente}</td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>