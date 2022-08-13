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
	<h1>DAR DE ALTA</h1>
	<br>
	<form:form method="POST" action="crearGeneral" modelAttribute="generalCrear">
		<h4>Informacion del cliente</h4>
		Nombre:<form:input path="cliente.nombre" type="text"/>
		Apellido Paterno:<form:input path="cliente.aPaterno" type="text"/>
		Apellido Materno:<form:input path="cliente.aMaterno" type="text"/>
		<br>
		<h4>Informacion del banco</h4>
		Nombre:<form:input path="banco.nombre" type="text"/>
		Direccion:<form:input path="banco.direccion" type="text"/>
		<br>
		<h4>Información de cuentas</h4>
		Num Cuenta:<form:input path="cuentas[0].numCuenta" type="text"/>
		Monto:<form:input path="cuentas[0].monto" type="text"/>
		<br>
		Num Cuenta:<form:input path="cuentas[1].numCuenta" type="text"/>
		Monto:<form:input path="cuentas[1].monto" type="text"/>
		<br>
		Num Cuenta:<form:input path="cuentas[2].numCuenta" type="text"/>
		Monto:<form:input path="cuentas[2].monto" type="text"/>
		<input type="submit" value="enviar"/>
	</form:form>
</body>
</html>