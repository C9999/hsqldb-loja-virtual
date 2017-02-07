package br.com.alura.hsql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.com.alura.hsql.modelo.Categoria;
import br.com.alura.hsql.modelo.Produto;

public class ProdutosDAO {
	private final Connection con;
	
	public ProdutosDAO(Connection con) {
		this.con = con;
	}
	public void salva(Produto produto) throws SQLException {
		String sql = "insert into Produto(nome, descricao) values(?,?)";
		try(PreparedStatement stmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
			stmt.setString(1, produto.getNome());
			stmt.setString(2, produto.getDescricao());
			stmt.execute();
			
			try(ResultSet rs = stmt.getGeneratedKeys()){
				if(rs.next()){
					int id = rs.getInt("id");
					produto.setId(id);
				}
			}
		}
	}
	public List<Produto> lista() throws SQLException {
		List<Produto> produtos = new ArrayList<Produto>();
		String sql = "select * from Produto";
		try(PreparedStatement stmt = con.prepareStatement(sql)){
			stmt.execute();
			
			trasformaResultadoEmProdutos(produtos, stmt);
		}
		return produtos;
	}
	public List<Produto> busca(Categoria categoria) throws SQLException{
		System.out.println("Executando uma Query no Banco");
		List<Produto> produtos = new ArrayList<Produto>();
		String sql = "select * from Produto where categoria_id = ?";
		try(PreparedStatement stmt = con.prepareStatement(sql)){
			stmt.setInt(1, categoria.getId());
			stmt.execute();
			
			trasformaResultadoEmProdutos(produtos, stmt);
		}
		return produtos;	
	}
	private void trasformaResultadoEmProdutos(List<Produto> produtos, PreparedStatement stmt) throws SQLException {
		try(ResultSet rs = stmt.getResultSet()){
			while(rs.next()){
				int id = rs.getInt("id");
				String nome = rs.getString("nome");
				String descricao = rs.getString("descricao");
				Produto produto = new Produto(nome, descricao);
				produto.setId(id);
				produtos.add(produto);
			}
		}
	}
	

}
