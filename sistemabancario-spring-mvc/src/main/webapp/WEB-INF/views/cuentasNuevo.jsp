<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Sistema bancario</title>
</head>
<body>
<h1>CUENTAS</h1>
	<br>
	<h3>CREAR CUENTA</h3>
	<form:form method="POST" action="crear" modelAttribute="cuentaCrear">
		Id Cuenta:<form:input path="idCuenta" type="text"/>
		Num Cuenta:<form:input path="numCuenta" type="text"/>
		Monto:<form:input path="monto" type="text"/>
		Id Cliente:<form:input path="idCliente" type="text"/>
		<input type="submit" value="enviar"/>
	</form:form>
	<br>
	
	<h3>ACTUALIZAR CUENTA</h3>
	<form:form method="POST" action="actualizar" modelAttribute="cuentaActualizar">
		Id Cuenta:<form:input path="idCuenta" type="text"/>
		Num Cuenta:<form:input path="numCuenta" type="text"/>
		Monto:<form:input path="monto" type="text"/>
		Id Cliente:<form:input path="idCliente" type="text"/>
		<input type="submit" value="enviar"/>
	</form:form>
	<br>
	
	<h3>BORRAR CUENTA</h3>
	<form:form method="POST" action="borrar" modelAttribute="cuentaBorrar">
		Id Cuenta:<form:input path="idCuenta" type="text"/>
		<input type="submit" value="enviar"/>
	</form:form>
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