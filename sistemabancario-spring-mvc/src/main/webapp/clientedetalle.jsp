<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="com.sistemabancario.model.Cliente" %>
<%@ page import="com.sistemabancario.model.Banco" %>
<%@ page import="com.sistemabancario.model.Cuenta" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Detalles del Cliente</title>
</head>
<body>
	<h1>DETALLES DEL CLIENTE</h1>
	<br>
	<a href="ClienteController?action=consulta">Regresar a la página anterior</a><br>
	<h4>Filtrar cuentas</h4>
	<%
		Cliente cliente = (Cliente)request.getAttribute("cliente");
		Banco banco = (Banco)request.getAttribute("banco");	
	%>
		<form action="CuentaController" method="POST">
			<table>
				<tr>
					<td><label for="idCuenta">ID CUENTA</label></td>
					<td><input type="text" name="idCuenta"/></td>
				</tr>
				<tr>
					<td><label for="monto">MONTO</label></td>
					<td><input type="text" name="monto"/></td>
				</tr>
				<tr>
					<td><label for="numCuenta">NUM CUENTA</label></td>
					<td><input type="text" name="numCuenta"/></td>
				</tr>
				<tr>
					<td><input type="hidden" name="action" value="filter"/></td>
					<td><input type="submit" value="Filtrar cuentas"/></td>
				</tr>
			</table>
			<input type="hidden" name="idCliente" value="<% out.write(""+cliente.getIdCliente()); %>"/>
<%-- 			<input type="hidden" name="nombre" value="<% out.write(""+cliente.getNombre()); %>"/> --%>
<%-- 			<input type="hidden" name="aPaterno" value="<% out.write(""+cliente.getaPaterno()); %>"/> --%>
<%-- 			<input type="hidden" name="aMaterno" value="<% out.write(""+cliente.getaMaterno()); %>"/> --%>
<%-- 			<input type="hidden" name="idBanco" value="<% out.write(""+banco.getIdBanco()); %>"/> --%>
<%-- 			<input type="hidden" name="direccion" value="<% out.write(""+banco.getDireccion()); %>"/> --%>
<%-- 			<input type="hidden" name="nombreBanco" value="<% out.write(""+banco.getNombre()); %>"/> --%>
		</form>
		
		<center>
		<table border="2">
			<tr>
				<th>ID CLIENTE</th>
				<th>NOMBRE CLIENTE</th>
				<th>APELLIDO PATERNO</th>
				<th>APELLIDO MATERNO</th>
				<th>ID BANCO</th>
				<th>NOMBRE BANCO</th>
				<th>DIRECCIÓN BANCO</th>
			</tr>
			<!-- <a href="ClienteController?idCliente<%// out.write(""+idCliente); %>&action=getInfo"></a> -->
			<tr>
				<td>
					<%
					out.write(""+cliente.getIdCliente());
					%>
				</td>
				<td>
					<%
					out.write(cliente.getNombre());
					%>
				</td>
				<td>
					<% 
					out.write(cliente.getaPaterno());
					%>
				</td>
				<td>
					<% 
					out.write(cliente.getaMaterno());
					%>
				</td>
				<td>
					<%
					out.write(""+banco.getIdBanco());
					%>
				</td>
				<td>
					<%
					out.write(banco.getNombre());
					%>
				</td>
				<td>
					<%
					out.write(banco.getDireccion());
					%>
				</td>
			</tr>
		</table>
		</center>
		<br>
		
		<center>
		<table border="2">
		<tr>
		<th>ID CUENTA</th>
		<th>NUM CUENTA</th>
		<th>MONTO</th>
		<th>ID CLIENTE</th>
		</tr>
		<%
			if (request.getAttribute("listaCuentas") != null) {
				List<Cuenta> cuentas = (List<Cuenta>)request.getAttribute("listaCuentas");
				for (Cuenta cuenta : cuentas) {
		%>
		<tr>
			<td>
			<%
				out.write(""+cuenta.getIdCuenta());
			%>
			</td>
			<td>
			<%
				out.write(""+cuenta.getNumCuenta());
			%>
			</td>
			<td>
			<%
				out.write(""+cuenta.getMonto());
			%>
			</td>
			<td>
			<%
				out.write(""+cuenta.getIdCliente());
			%>
			</td>
		</tr>
		<%
				}
			}
		%>
		</table>
		</center>
</body>
</html>