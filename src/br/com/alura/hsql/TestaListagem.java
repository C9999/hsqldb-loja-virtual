package br.com.alura.hsql;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TestaListagem {

	public static void main(String[] args) throws SQLException {
		ConnectionPool database = new ConnectionPool();
		
		
		for(int i = 0; i < 100; i++){
			Connection connection = new ConnectionPool().getConnection();
			Statement statement = connection.createStatement();
			boolean resultado = statement.execute("select * from Produto");

			ResultSet resultSet = statement.getResultSet();
			while(resultSet.next()){

				String id = resultSet.getString("id");
				System.out.println(id);

				String nome = resultSet.getString("nome");
				System.out.println(nome);

				String descricao = resultSet.getString("descricao");
				System.out.println(descricao);
			}
			resultSet.close();
			statement.close();
			connection.close();
		}

	}

}
