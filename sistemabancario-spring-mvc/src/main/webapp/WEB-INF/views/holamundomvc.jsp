<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	${mensaje}
	
	<form:form method="POST" action="holaMundo/registrarUsuario" modelAttribute="usuarioEjemplo">
		Nombre:<form:input path="nombre" type="text"/>
		Apellido:<form:input path="apellido" type="text"/>
		Edad:<form:input path="edad" type="text"/>
		<input type="submit" value="enviar"/>
	</form:form>
	
	${usuario.nombre}
	${usuario.apellido}
	${usuario.edad}
	
	<c:forEach items="${clientesCollection}" var="clienteTemporal" >
	
	<tr>
	<td>${clienteTemporal.idCliente }</td>
	<td>${clienteTemporal.nombre}</td>
	<td>${clienteTemporal.apaterno} , ${clienteTemporal.amaterno }</td>
	<td>${clienteTemporal.edad}</td>
	<td>${clienteTemporal.banco.nombre}</td>
	</tr>
	
	</c:forEach>

</body>
</html>