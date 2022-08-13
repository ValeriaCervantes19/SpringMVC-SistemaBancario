package com.sistemabancario.persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ConnectionFactory {
	
	private Connection connectionPg;
	private Statement statement; 
	
	public Connection connectPg() {
		
		try {
		//cargar el driver
			Class.forName("org.postgresql.Driver");
			
			
		//} catch(ClassNotFoundException exception) {
			//imprimir el error en consola, no termina el programa
		//	exception.printStackTrace();
		//}
		//configurar datos de conexión a la base de datos
		String url = "jdbc:postgresql://localhost:5432/tecgurus_basico";
		String usuario = "postgres";
		String password = "calabacita40";
		
		//obtener conexion
		//try {
			connectionPg = DriverManager.getConnection(url,usuario,password);
			statement = connectionPg.createStatement();
		}catch(SQLException | ClassNotFoundException exception) {
			exception.printStackTrace();
		}
		
		return connectionPg;
	}
	
	public Boolean disconnectPg() {
		
		Boolean isDisconnected = false;
		
		try {
		//si hay una conexion la cerramos
			if(connectionPg!=null) {
				connectionPg.close();
				isDisconnected = true;
			}
			else {
				isDisconnected = false;
			}
		} catch(SQLException exception) {
			exception.printStackTrace();
		}
		
		return isDisconnected;
	}
	
	public ResultSet executeSQLSelect(String sql) {
		//checar que es lo que recibimos
		System.out.println("query: "+sql);
		ResultSet result = null;
		try {
			result = statement.executeQuery(sql);
		} catch(SQLException exception) {
			exception.printStackTrace();
		}
		return result;
		
	}
	
	public Boolean executeSQL(String sql) {
		System.out.println("query: "+sql);
		Boolean result = false;
		try {
			result = statement.execute(sql);
		}catch(SQLException exception) {
			exception.printStackTrace();
		}
		return result;
	}
	
}
