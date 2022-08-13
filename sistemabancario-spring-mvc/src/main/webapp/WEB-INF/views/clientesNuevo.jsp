<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>>Sistema bancario</title>
</head>
<body>
	<h1>CLIENTES</h1>
	<br>
	
	<!-- <a href="index">Regresar al incio</a><br>  -->
	
	<h3>CREAR CLIENTE</h3>
	<form:form method="POST" action="crear" modelAttribute="clienteCrear">
		Id Cliente:<form:input path="idCliente" type="text"/>
		Nombre:<form:input path="nombre" type="text"/>
		Apellido Paterno:<form:input path="aPaterno" type="text"/>
		Apellido Materno:<form:input path="aMaterno" type="text"/>
		Id Banco:<form:input path="idBanco" type="text"/>
		<input type="submit" value="enviar"/>
	</form:form>
	<br>
	
	<h3>ACTUALIZAR CLIENTE</h3>
	<form:form method="POST" action="actualizar" modelAttribute="clienteActualizar">
		Id Cliente:<form:input path="idCliente" type="text"/>
		Nombre:<form:input path="nombre" type="text"/>
		Apellido Paterno:<form:input path="aPaterno" type="text"/>
		Apellido Materno:<form:input path="aMaterno" type="text"/>
		Id Banco:<form:input path="idBanco" type="text"/>
		<input type="submit" value="enviar"/>
	</form:form>
	<br>
	
	<h3>BORRAR CLIENTE</h3>
	<form:form method="POST" action="borrar" modelAttribute="clienteBorrar">
		Id Cliente:<form:input path="idCliente" type="text"/>
		<input type="submit" value="enviar"/>
	</form:form>
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
</body>
</html>