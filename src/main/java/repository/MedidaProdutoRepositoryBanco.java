package repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Medida_produto;

public class MedidaProdutoRepositoryBanco {
private Connection conexao = ConexaoFactory.criarConexao();
	public void cadastrar(Medida_produto medpro){
		String sql = "insert into medidaproduto  values (default,?)";
		
		
		try {
			PreparedStatement ps = conexao.prepareStatement(sql);
			
			ps.setString(1, medpro.getDescricao());
			
			ps.execute();
		} catch (SQLException e){
			e.printStackTrace();
		}
	}
	
	
	public List<Medida_produto> buscarTodos() {
		
		List<Medida_produto> lista = new ArrayList<>();
		
		try {
			String sql = "Select * from medidaproduto order by id_medidaproduto";
			PreparedStatement prepareStatement = conexao.prepareStatement(sql);
			ResultSet result = prepareStatement.executeQuery();
			
			while (result.next()) {
				int id = result.getInt("id_medidaproduto");
				String descricao = result.getString("descricao");
				
				Medida_produto medpro = new Medida_produto();
				medpro.setId_medidaproduto(id);
				medpro.setDescricao(descricao);
				
				lista.add(medpro);
				
			}
		} catch (SQLException e){
			e.printStackTrace();
		}
		return lista;
		
		
	}
	
	/*public List<Medida_produto> buscarPorId(int id_medidaproduto) {
		
		List<Medida_produto> lista = new ArrayList<>();
		
		try {
			String sql = "select * from medidaproduto where id_medidaproduto=?";
			PreparedStatement prepareStatement = conexao.prepareStatement(sql);
			prepareStatement.setInt(1, id_medidaproduto);
			ResultSet result = prepareStatement.executeQuery();
			
			if (result.next()) {
				int id = result.getInt("id");
				String descricao = result.getString("descricao");
				
				Medida_produto medpro = new Medida_produto();
				medpro.setId_medidaproduto(id);
				medpro.setDescricao(descricao);
				
				lista.add(medpro);
				return lista;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}*/
	
	
	public void alterar(Medida_produto m) {
		String sql = "update medida_produto set descricao=? where id_medidaproduto=?";
		
		try{
			PreparedStatement ps = conexao.prepareStatement(sql);
			ps.setString(1,m.getDescricao());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public void excluir(int id) {
		try {
			String sql = "delete from medida_produto where id_medidaproduto=?";
			PreparedStatement ps = conexao.prepareStatement(sql);
			ps.setInt(1,id);
			ps.execute();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
	}
}
