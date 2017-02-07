package br.com.alura.hsql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;


public class TestaRemocao {
	public static void main(String[] args) throws SQLException {
		Connection cn = new ConnectionPool().getConnection();	
		Statement st = cn.createStatement();
		
		st.execute("delete from Produto where id > 3");
		int count = st.getUpdateCount();
		System.out.println(count + " linhas atualizadas");
		
		cn.close();
	}
}
