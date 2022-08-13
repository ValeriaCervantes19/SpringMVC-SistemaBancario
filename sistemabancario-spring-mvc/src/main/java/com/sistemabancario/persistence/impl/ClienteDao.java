package com.sistemabancario.persistence.impl;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.sistemabancario.persistence.ConnectionFactory;
import com.sistemabancario.persistence.GenericDao;
import com.sistemabancario.model.Cliente;
import com.sistemabancario.model.Cuenta;

@Component
public class ClienteDao implements GenericDao{
	
	@Override
	//no debería de ser un Set?
	public List<Cliente> findAll() {
		List<Cliente> clientes = new ArrayList<Cliente>(); 
		/*Cliente cliente1 = new Cliente(1111,"Valeria", null);
		Cliente cliente2 = new Cliente(2222,"Juan", null);
		clientes.add(cliente1);
		clientes.add(cliente2);
		return clientes;
		*/
		ConnectionFactory connectionFactory = new ConnectionFactory();
		Connection connection = connectionFactory.connectPg();
		//List<Cliente> clientes = new ArrayList<Cliente>();
		clientes.clear();
		if(connection!=null) {
			String query = "SELECT * from cliente";
			ResultSet rsClientes = connectionFactory.executeSQLSelect(query);
			if(rsClientes!=null) {
				try {
					while(rsClientes.next()) {
						Cliente cliente = new Cliente();
						cliente.setIdCliente(rsClientes.getInt("idCliente"));
						cliente.setIdBanco(rsClientes.getInt("idBanco"));
						cliente.setNombre(rsClientes.getString("nombre"));
						cliente.setaPaterno(rsClientes.getString("aPaterno"));
						cliente.setaMaterno(rsClientes.getString("aMaterno"));
						clientes.add(cliente);
					}
				}catch(SQLException exception) {
					exception.printStackTrace();
				}
			}
		}
		return clientes;
	}
	
	@Override
	public Boolean update(Object entity) {
		String query ="";
		Cliente cliente = (Cliente) entity;
		ConnectionFactory  connectionFactory = new ConnectionFactory();
		Connection connection = connectionFactory.connectPg();
		if(connection!=null) {
			query="UPDATE cliente SET \"idCliente\"="+cliente.getIdCliente()+", nombre='"+cliente.getNombre()+"', \"aPaterno\"='"+cliente.getaPaterno()+"', \"aMaterno\"='"+cliente.getaMaterno()+"', \"idBanco\"="+cliente.getIdBanco()+" WHERE \"idCliente\"="+cliente.getIdCliente();
		}
		return connectionFactory.executeSQL(query);
	}

	@Override
	public Boolean create(Object entity) {
		String query ="";
		ConnectionFactory connectionFactory = new ConnectionFactory();
		Connection connection = connectionFactory.connectPg();
		if(connection!=null) {
			Cliente cliente = (Cliente)entity;
			query = "INSERT INTO cliente (\"idCliente\", nombre, \"aPaterno\", \"aMaterno\", \"idBanco\") VALUES ("+cliente.getIdCliente()+",'"+cliente.getNombre()+"','"+cliente.getaPaterno()+"','"+cliente.getaMaterno()+"',"+cliente.getIdBanco()+")";
		}
		return connectionFactory.executeSQL(query);
	}

	@Override
	public Boolean delete(Object entity) {
		String query="";
		Cliente cliente = (Cliente)entity;
		ConnectionFactory connectionFactory = new ConnectionFactory();
		Connection connection = connectionFactory.connectPg();
		if(connection!=null) {
			query ="DELETE FROM cliente WHERE \"idCliente\"="+cliente.getIdCliente();
		}
		return connectionFactory.executeSQL(query);
	}

	@Override
	public Object findById(Object id) {
		Integer idCliente = (Integer)id;
		String query = "";
		Cliente cliente = null;
		ConnectionFactory connectionFactory = new ConnectionFactory();
		Connection connection = connectionFactory.connectPg();
		if(connection!=null) {
			query = "SELECT * FROM cliente WHERE \"idCliente\"="+idCliente;
			ResultSet rsCliente = connectionFactory.executeSQLSelect(query);
			try {
				while(rsCliente.next()) {
					cliente = new Cliente();
					cliente.setIdCliente(rsCliente.getInt("idCliente"));
					cliente.setNombre(rsCliente.getString("nombre"));
					cliente.setaPaterno(rsCliente.getString("aPaterno"));
					cliente.setaMaterno(rsCliente.getString("aMaterno"));
					cliente.setIdBanco(rsCliente.getInt("idBanco"));
				}
			}catch(SQLException exception) {
				exception.printStackTrace();
			}
		}
		return cliente;
	}

	@Override
	public List<? extends Object> findByExample(Map<String, Object> conditions) {
		String query ="";
		List<Cliente> clientes = new ArrayList<Cliente>(); 
		//clientes.clear();
		ConnectionFactory connectionFactory = new ConnectionFactory();
		Connection connection = connectionFactory.connectPg();
		Integer contador=0;
		ResultSet rsClientes = null;
		if(connection!=null) {
			if(!conditions.isEmpty()) {
				query="SELECT * FROM cliente WHERE ";
				for(Entry entry:conditions.entrySet()) {
					contador++;
					query+="\""+entry.getKey()+"\"";
					query+=" = ";
					query+="'"+entry.getValue()+"'";
					if(contador<conditions.entrySet().size()) {
						query+=" AND ";
					}
				}
			}
			else {
				query="SELECT * FROM cliente";
			}
			rsClientes = connectionFactory.executeSQLSelect(query);
			//para retrasar la página 
			try {
				TimeUnit.SECONDS.sleep(3);
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
			
			try {
				while(rsClientes.next()) {
					Cliente cliente = new Cliente();
					cliente.setIdCliente(rsClientes.getInt("idCliente"));
					cliente.setIdBanco(rsClientes.getInt("idBanco"));
					cliente.setNombre(rsClientes.getString("nombre"));
					cliente.setaPaterno(rsClientes.getString("aPaterno"));
					cliente.setaMaterno(rsClientes.getString("aMaterno"));
					clientes.add(cliente);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return clientes;
	}
	
	public Object lastElement() {
		String query="";
		ConnectionFactory connectionFactory= new ConnectionFactory();
		Connection connection = connectionFactory.connectPg();
		Cliente cliente=null;
		if(connection!=null) {
			query="SELECT * FROM cliente WHERE \"idCliente\"=(SELECT MAX(\"idCliente\") from cliente)";
			ResultSet rsCliente = connectionFactory.executeSQLSelect(query);
			try {
				while(rsCliente.next()) {
					cliente = new Cliente();
					cliente.setIdCliente(rsCliente.getInt("idCliente"));
					cliente.setNombre(rsCliente.getString("nombre"));
					cliente.setaPaterno(rsCliente.getString("aPaterno"));
					cliente.setaMaterno(rsCliente.getString("aMaterno"));
					cliente.setIdBanco(rsCliente.getInt("idBanco"));
				}
			}catch(SQLException exception) {
				exception.printStackTrace();
			}
		}
		return cliente;
	}
}
