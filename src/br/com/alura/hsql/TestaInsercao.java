package br.com.alura.hsql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TestaInsercao {
	public static void main(String[] args) throws SQLException {
		String nome = "Chargers";
		String descricao = "Tela de '19 polegadas";

		//if(nome.equals("Chargers")) {throw new IllegalArgumentException("Problema ocorrido");}
		
		Connection cn = new ConnectionPool().getConnection();
		cn.setAutoCommit(false);
		
		String sql = "insert into Produto(nome, descricao) values(?, ?)";
		PreparedStatement st = cn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
		
		try{
			adiciona("J5", "Celular Android", st);
			cn.commit();
			adiciona("Chargers", "Carregador portatil", st);
			cn.commit();
		}catch(Exception ex){
			ex.printStackTrace();
			cn.rollback();
			System.out.println("Rollback efetuado");
		}
		st.close();		
		cn.close();
	}

	private static void adiciona(String nome, String descricao, PreparedStatement st) throws SQLException {
		st.setString(1, nome);
		st.setString(2, descricao);
				
		boolean resultado = st.execute();
		System.out.println(resultado);
		
		ResultSet resulSet = st.getGeneratedKeys();
		while(resulSet.next()){
			String id =resulSet.getString("id");
			System.out.println(id + " gerado");
		}
		resulSet.close();
	}
}
