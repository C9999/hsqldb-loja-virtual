package br.com.alura.hsql.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.alura.hsql.modelo.Categoria;
import br.com.alura.hsql.modelo.Produto;

public class CategoriasDAO {
	
	private final Connection con;

	public CategoriasDAO(Connection con){
		this.con = con;	
	}

	public List<Categoria> lista() throws SQLException{
		System.out.println("Executando uma Query no Banco");
		List<Categoria> categorias = new ArrayList<Categoria>();
		
		String sql = "select * from Categoria";
		try(PreparedStatement stmt = con.prepareStatement(sql)){
			stmt.execute();
			try(ResultSet rs = stmt.getResultSet()){
				while(rs.next()){
					int id = rs.getInt("id");
					String nome = rs.getString("nome");
					Categoria categoria = new Categoria(id, nome);
					categorias.add(categoria);
				}
			}
		}		
		return categorias;
	}

	public List<Categoria> listaComProdutos() throws SQLException{
		System.out.println("Executando uma Query no Banco");
		List<Categoria> categorias = new ArrayList<Categoria>();
		Categoria ultima = null;
		
		//String sql = "select * from Categoria as c join Produto as p on p.categoria_id = c.id ";
		String sql = "select c.id as c_id, c.nome as c_nome, p.id as p_id, p.nome as p_nome, p.descricao as p_descricao from Categoria as c join Produto as p on p.categoria_id = c.id";
		 
		try(PreparedStatement stmt = con.prepareStatement(sql)){
			stmt.execute();
			try(ResultSet rs = stmt.getResultSet()){
				while(rs.next()){
					int id = rs.getInt("c_id");
					String nome = rs.getString("c_nome");
					if(ultima == null || !ultima.getNome().equals(nome)){
						Categoria categoria = new Categoria(id, nome);
						categorias.add(categoria);
						ultima = categoria;
					}
					int idDoProduto = rs.getInt("p_id");
					String nomeDoProduto = rs.getString("p_nome");
					String descricaoDoProduto = rs.getString("p_descricao");
					Produto p = new Produto(nomeDoProduto, descricaoDoProduto);
					p.setId(idDoProduto);
					ultima.adiciona(p);
				}
			}
		}		
		return categorias;
	}
}

