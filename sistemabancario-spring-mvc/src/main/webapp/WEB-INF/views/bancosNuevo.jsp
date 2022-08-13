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
	<h1>BANCOS</h1>
	<br>
	
	<h3>CREAR BANCO</h3>
	<form:form method="POST" action="crear" modelAttribute="bancoCrear">
		Id banco:<form:input path="idBanco" type="text"/>
		Nombre:<form:input path="nombre" type="text"/>
		Direccion:<form:input path="direccion" type="text"/>
		<input type="submit" value="enviar"/>
	</form:form>
	<br>
	${bancoCrear.idBanco}
	${bancoCrear.nombre}
	${bancoCrear.direccion}
	<br>
	<h3>BORRAR BANCO</h3>
	<form:form method="POST" action="borrar" modelAttribute="bancoBorrar">
		Id banco:<form:input path="idBanco" type="text"/>
		<input type="submit" value="enviar"/>	
	</form:form>
	<br>
	<h3>ACTUALIZAR BANCO</h3>
	<form:form method="POST" action="actualizar" modelAttribute="bancoActualizar">
		Id banco:<form:input path="idBanco" type="text"/>
		Nombre:<form:input path="nombre" type="text"/>
		Direccion:<form:input path="direccion" type="text"/>
		<input type="submit" value="enviar"/>
	</form:form>
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
	
</body>
</html>