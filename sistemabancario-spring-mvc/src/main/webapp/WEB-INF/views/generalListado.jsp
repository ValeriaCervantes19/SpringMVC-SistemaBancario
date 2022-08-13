<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>REGISTRADO</title>
</head>
<body>
	<h1>DETALLES GENERALES DEL REGISTRO</h1>
	<br>
	<h3>CLIENTE</h3>
	<br>
	<table border="2">
		<tr>
			<th>ID CLIENTE</th>
			<th>NOMBRE</th>
			<th>APELLIDO PATERNO</th>
			<th>APELLIDO MATERNO</th>
		</tr>
		<tr>
			<td>${dto.cliente.idCliente}</td>
			<td>${dto.cliente.nombre}</td>
			<td>${dto.cliente.aPaterno}</td>
			<td>${dto.cliente.aMaterno}</td>
		</tr>
	</table>
	<br>
	<h3>BANCO</h3>
	<br>
	<table border="2">
		<tr>
			<th>ID</th>
			<th>NOMBRE</th>
			<th>DIRECCION</th>
		</tr>
		<tr>
			<td>${dto.banco.idBanco}</td>
			<td>${dto.banco.nombre}</td>
			<td>${dto.banco.direccion}</td>
		</tr>
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
		<c:forEach items="${dto.cuentas}" var="cuentaTemporal" >
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