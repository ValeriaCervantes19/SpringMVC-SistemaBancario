package com.sistemabancario.persistence.impl;

import java.util.List;
import java.util.Map;
import java.util.Set;
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
import com.sistemabancario.model.Banco;
import com.sistemabancario.model.Cliente;
import com.sistemabancario.model.Cuenta;

@Component
public class CuentaDao extends Thread implements GenericDao{

	private List<Integer>llavesCuenta= new ArrayList<Integer>();
	private List<Cuenta>listaCuentas = new ArrayList<Cuenta>();
	
	public List<Cuenta> getListaCuentas() {
		return listaCuentas;
	}

	public void setListaCuentas(List<Cuenta> listaCuentas) {
		this.listaCuentas = listaCuentas;
	}

	@Override
	public List<Cuenta> findAll() {
		ConnectionFactory connectionFactory = new ConnectionFactory();
		Connection connection = connectionFactory.connectPg();
		//cuentas.clear();
		List<Cuenta> cuentas = new ArrayList<Cuenta>();
		if(connection!=null) {
			String query = "SELECT * from cuenta";
			ResultSet rsCuentas=connectionFactory.executeSQLSelect(query);
			if(rsCuentas!=null) {
				//next extrae un lemento del ResultSet y verifica que haya elemento siguiente 
				try {
					while(rsCuentas.next()) {
						Cuenta cuenta = new Cuenta();
						//obtiene el primer id de la tabla banco
						cuenta.setIdCuenta(rsCuentas.getInt("idCuenta"));
						cuenta.setIdCliente(rsCuentas.getInt("idCliente"));
						cuenta.setNumCuenta(rsCuentas.getInt("numCuenta"));
						cuenta.setMonto(rsCuentas.getFloat("monto"));
						cuentas.add(cuenta);
					}
				}catch(SQLException exception) {
					exception.printStackTrace();
				}
			}
		}
		return cuentas;
	}
	
	@Override
	public Boolean update(Object entity) {
		String query="";
		Cuenta cuenta = (Cuenta)entity;
		ConnectionFactory connectionFactory = new ConnectionFactory();
		Connection connection = connectionFactory.connectPg();
		if(connection!=null) {
			query="UPDATE cuenta SET \"numCuenta\"="+cuenta.getNumCuenta()+", monto="+cuenta.getMonto()+", \"idCuenta\"="+cuenta.getIdCuenta()+", \"idCliente\"="+cuenta.getIdCliente()+" WHERE \"idCuenta\"="+cuenta.getIdCuenta();
		}
		return connectionFactory.executeSQL(query);
	}

	@Override
	public Boolean create(Object entity) {
		String query="";
		ConnectionFactory connectionFactory = new ConnectionFactory();
		Connection connection = connectionFactory.connectPg();
		if(connection!=null) {
			Cuenta cuenta = (Cuenta)entity;
			query = "INSERT INTO cuenta(\"numCuenta\", monto, \"idCuenta\",\"idCliente\") VALUES ("+cuenta.getNumCuenta()+","+cuenta.getMonto()+","+cuenta.getIdCuenta()+","+cuenta.getIdCliente()+")";
		}
		return connectionFactory.executeSQL(query);
	}

	@Override
	public Boolean delete(Object entity) {
		String query="";
		Cuenta cuenta =(Cuenta)entity;
		ConnectionFactory connectionFactory = new ConnectionFactory();
		Connection connection = connectionFactory.connectPg();
		if(connection!=null) {
			query="DELETE FROM cuenta WHERE \"idCuenta\"="+cuenta.getIdCuenta();
		}
		return connectionFactory.executeSQL(query);
	}

	@Override
	public Object findById(Object id) {
		Integer idCuenta = (Integer)id;
		String query="";
		Cuenta cuenta=null;
		ConnectionFactory connectionFactory = new ConnectionFactory();
		Connection connection = connectionFactory.connectPg();
		if(connection!=null) {
			query="SELECT * FROM cuenta WHERE \"idCuenta\"="+idCuenta;
			ResultSet rsCuenta = connectionFactory.executeSQLSelect(query);
			try {
				while(rsCuenta.next()) {
					cuenta = new Cuenta();
					cuenta.setIdCuenta(rsCuenta.getInt("idCuenta"));
					cuenta.setNumCuenta(rsCuenta.getInt("numCuenta"));
					cuenta.setMonto(rsCuenta.getFloat("monto"));
					cuenta.setIdCliente(rsCuenta.getInt("idCliente"));
				}
			} catch (SQLException exception) {
				exception.printStackTrace();
			}
		}
		return cuenta;
	}
	
	@Override
	public List<? extends Object> findByExample(Map<String, Object> conditions) {
		String query ="";
		//cuentas.clear();
		List<Cuenta> cuentas = new ArrayList<Cuenta>(); 
		ConnectionFactory connectionFactory = new ConnectionFactory();
		Connection connection = connectionFactory.connectPg();
		Integer contador=0;
		
		if(connection!=null) {
			query="SELECT * FROM cuenta WHERE ";
			for(Entry entry:conditions.entrySet()) {
				contador++;
				query+="\""+entry.getKey()+"\"";
				query+=" = ";
				query+=entry.getValue();
				if(contador<conditions.entrySet().size()) {
					query+=" AND ";
				}
			}
			ResultSet rsCuentas = connectionFactory.executeSQLSelect(query);
			try {
				while(rsCuentas.next()) {
					Cuenta cuenta = new Cuenta();
					cuenta.setIdCuenta(rsCuentas.getInt("idCuenta"));
					cuenta.setNumCuenta(rsCuentas.getInt("numCuenta"));
					cuenta.setMonto(rsCuentas.getFloat("monto"));
					cuenta.setIdCliente(rsCuentas.getInt("idCliente"));
					cuentas.add(cuenta);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return cuentas;
	}
	
	public void asignarLlaves(List<Integer>keys) {
		llavesCuenta=keys;
	}
	
	public void run() {
		listaCuentas=findAllById(llavesCuenta);
	}
	
	public List<Cuenta> findAllById(List<Integer>keys){
		String query = "";
		Integer contador=0;
		//cuentas.clear();
		List<Cuenta> cuentas = new ArrayList<Cuenta>();
		ConnectionFactory connectionFactory = new ConnectionFactory();
		Connection connection = connectionFactory.connectPg();
		if(connection!=null) {
			query = "SELECT * FROM cuenta WHERE \"idCliente\" in (";
			for(Integer idCliente: keys) {
				query+= idCliente;
				contador++;
				if(keys.size()>contador) {
					query+=",";
				}
				else {
					query+=")";
				}
			}
			ResultSet rsCuentas = connectionFactory.executeSQLSelect(query);
			
			//para retrasar la página 
			try {
				TimeUnit.SECONDS.sleep(3);
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}

			
			try {
				while(rsCuentas.next()) {
					Cuenta cuenta = new Cuenta();
					cuenta.setIdCuenta(rsCuentas.getInt("idCuenta"));
					cuenta.setNumCuenta(rsCuentas.getInt("numCuenta"));
					cuenta.setMonto(rsCuentas.getFloat("monto"));
					cuenta.setIdCliente(rsCuentas.getInt("idCliente"));
					cuentas.add(cuenta);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return cuentas;
	}

	@Override
	public Object lastElement() {
		String query="";
		Cuenta cuenta=null;
		ConnectionFactory connectionFactory = new ConnectionFactory();
		Connection connection = connectionFactory.connectPg();
		if(connection!=null) {
			query="SELECT * FROM cuenta WHERE \"idCuenta\"=(SELECT MAX(\"idCuenta\") from cuenta)";
			ResultSet rsCuenta = connectionFactory.executeSQLSelect(query);
			try {
				while(rsCuenta.next()) {
					cuenta = new Cuenta();
					cuenta.setIdCuenta(rsCuenta.getInt("idCuenta"));
					cuenta.setNumCuenta(rsCuenta.getInt("numCuenta"));
					cuenta.setMonto(rsCuenta.getFloat("monto"));
					cuenta.setIdCliente(rsCuenta.getInt("idCliente"));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return cuenta;
	}
}
