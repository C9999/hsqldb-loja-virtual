package br.com.alura.hsql.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import br.com.alura.hsql.ConnectionPool;
import br.com.alura.hsql.ProdutosDAO;
import br.com.alura.hsql.modelo.Produto;

public class TestaDAOdeProduto {

	public static void main(String[] args) throws SQLException {
		Produto mesa = new Produto("Mesa Vermelha", "Mesa com 4 pernas");

		try(Connection con = new ConnectionPool().getConnection()) {
			ProdutosDAO dao = new ProdutosDAO(con);
			dao.salva(mesa);
			
			List<Produto> produtos = dao.lista();
			//new ProdutosDAO(con).salva(mesa);
			for (Produto produto : produtos) {
				System.out.println("Existe o produto" +produto);
			}
		}
	}
}
