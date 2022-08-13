package com.sistemabancario.persistence.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sistemabancario.model.Banco;
import com.sistemabancario.model.Cliente;
import com.sistemabancario.persistence.GenericDao;
import com.sistemabancario.persistence.ConnectionFactory;

@Component
public class BancoDao implements GenericDao {
	
	@Override
	public List<Banco> findAll() {
		List<Banco> bancos = new ArrayList<Banco>();
		/*//como tomar el set de clientes ya creado
		Banco banco1 = new Banco("Santander", "Periférico Sur", 1110, null);
		Banco banco2 = new Banco("Bancomer", "8 de Julio", 2220, null);
		bancos.add(banco1);
		bancos.add(banco2);
		*/
		ConnectionFactory connectionFactory = new ConnectionFactory();
		Connection connection = connectionFactory.connectPg();
		bancos.clear();
		//List<Banco> bancos = new ArrayList<Banco>();
		if(connection!=null) {
			String query = "SELECT * from banco";
			ResultSet rsBancos=connectionFactory.executeSQLSelect(query);
			if(rsBancos!=null) {
				//next extrae un lemento del ResultSet y verifica que haya elemento siguiente 
				try {
					while(rsBancos.next()) {
						Banco banco= new Banco();
						//obtiene el primer id de la tabla banco
						banco.setIdBanco(rsBancos.getInt("idBanco"));
						banco.setNombre(rsBancos.getString("nombre"));
						banco.setDireccion(rsBancos.getString("direccion"));
						bancos.add(banco);
					}
				}catch(SQLException exception) {
					exception.printStackTrace();
				}
			}
		}
		return bancos;
	}

	@Override
	public Boolean update(Object entity) {
		String query="";
		Banco banco = (Banco)entity;
		ConnectionFactory connectionFactory = new ConnectionFactory();
		Connection connection = connectionFactory.connectPg();
		if(connection!=null) {
			query="UPDATE banco SET \"idBanco\"="+banco.getIdBanco()+", nombre='" +banco.getNombre()+"', direccion='"+banco.getDireccion()+"' WHERE \"idBanco\"="+banco.getIdBanco();
		}
		return connectionFactory.executeSQL(query);
	}

	@Override
	public Boolean create(Object entity) {
		String query="";
		ConnectionFactory connectionFactory = new ConnectionFactory();
		Connection connection = connectionFactory.connectPg();
		if(connection!=null) {
			Banco banco = (Banco)entity;
			query= "INSERT INTO banco (\"idBanco\", nombre, direccion) VALUES ("+banco.getIdBanco()+",'"+banco.getNombre()+"','"+banco.getDireccion()+"')";
		}
		return connectionFactory.executeSQL(query);
	}

	@Override
	public Boolean delete(Object entity) {
		String query="";
		Banco banco = (Banco)entity;
		ConnectionFactory connectionFactory = new ConnectionFactory();
		Connection connection = connectionFactory.connectPg();
		if(connection!=null) {
			query = "DELETE FROM banco WHERE \"idBanco\"= "+banco.getIdBanco();
		}
		return connectionFactory.executeSQL(query);
	}

	public Object findById(Object id) {
		Integer idBanco = (Integer)id;
		String query="";
		Banco banco = null;
		ConnectionFactory connectionFactory = new ConnectionFactory();
		Connection connection = connectionFactory.connectPg();
		if(connection!=null) {
			query="SELECT * FROM banco WHERE \"idBanco\"= "+idBanco;
			ResultSet rsBanco=connectionFactory.executeSQLSelect(query);
			try {
				while(rsBanco.next()) {
					banco = new Banco();
					banco.setIdBanco(rsBanco.getInt("idBanco"));
					banco.setNombre(rsBanco.getString("nombre"));
					banco.setDireccion(rsBanco.getString("direccion"));
				}
			}catch(SQLException exception) {
				exception.printStackTrace();
			}
		}
		return banco;
	}
	
	
	public List<? extends Object> findAllById(List<Integer> keys){
		String query = "";
		Integer contador=0;
		Banco banco = null;
		//bancos.clear();
		List<Banco> bancos = new ArrayList<Banco>();
		ConnectionFactory connectionFactory = new ConnectionFactory();
		Connection connection = connectionFactory.connectPg();
		if(connection!=null) {
			query = "SELECT * FROM banco WHERE \"idBanco\" in (";
			for(Integer id:keys) {
				query+=id;
				contador++;
				if(keys.size()>contador) {
					query+=",";
				}
				else {
					query+=")";
				}
			}
			ResultSet rsBancos = connectionFactory.executeSQLSelect(query);
			
			//para retrasar la página 
			try {
				TimeUnit.SECONDS.sleep(1);
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}

			
			try {
				while(rsBancos.next()) {
					banco = new Banco();
					banco.setIdBanco(rsBancos.getInt("idBanco"));
					banco.setNombre(rsBancos.getString("nombre"));
					banco.setDireccion(rsBancos.getString("direccion"));
					bancos.add(banco);
				}
			}catch(SQLException exception) {
				exception.printStackTrace();
			}
		}
		return bancos;
	}
	
	public List<? extends Object> findByExample(Map<String,Object> conditions){
		return null;
	}

	@Override
	public Object lastElement() {
		String query="";
		Banco banco=null;
		ConnectionFactory connectionFactory = new ConnectionFactory();
		Connection connection = connectionFactory.connectPg();
		if(connection!=null) {
			query="SELECT * FROM banco WHERE \"idBanco\"=(SELECT MAX(\"idBanco\") from banco)";
			ResultSet rsBancos = connectionFactory.executeSQLSelect(query);
			try {
				while(rsBancos.next()) {
					banco = new Banco();
					banco.setIdBanco(rsBancos.getInt("idBanco"));
					banco.setNombre(rsBancos.getString("nombre"));
					banco.setDireccion(rsBancos.getString("direccion"));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return banco;
	}

}
