package br.com.alura.hsql;
import java.sql.Connection;
import java.sql.SQLException;

public class TestaConexao {

	public static void main(String[] args) throws SQLException{
		//Connection connection = DriverManager.getConnection("jdbc:hsqldb:hsql://localhost/loja-virtual", "SA", "");
		Connection connection = new ConnectionPool().getConnection();
		
		System.out.println("Abrindo uma conexão com sucesso");
		connection.close();

	}

}
