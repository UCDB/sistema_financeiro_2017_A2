package repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.MedidaProduto;

public class MedidaProdutoRepositoryBanco {
private Connection conexao = ConexaoFactory.criarConexao();
	public void cadastrar(MedidaProduto medpro){
		String sql = "insert into medidaproduto  values (default,?)";
		
		
		try {
			PreparedStatement ps = conexao.prepareStatement(sql);
			
			ps.setString(1, medpro.getDescricao());
			
			ps.execute();
		} catch (SQLException e){
			e.printStackTrace();
		}
	}
	public Object buscarTodos() {
		List<MedidaProduto> lista = new ArrayList<>();
		
		try {
			String sql = "Select * from medida_produto order by id_medidaproduto";
			PreparedStatement prepareStatement = conexao.prepareStatement(sql);
			ResultSet result = prepareStatement.executeQuery();
			
			while (result.next()) {
				int id = result.getInt("id");
				String descricao = result.getString("descricao");
				
				MedidaProduto m = new MedidaProduto();
				m.setId_medidaproduto(id);
				m.setDescricao(descricao);
				
				lista.add(m);
				
			}
		} catch (SQLException e){
			e.printStackTrace();
		}
		return lista;
		
		
	}
	public MedidaProduto buscarPorId(Integer id) {
		try {
			String sql = "select * from medida_produto where id=?";
			PreparedStatement prepareStatement = conexao.prepareStatement(sql);
			prepareStatement.setInt(1, id);
			ResultSet result = prepareStatement.executeQuery();
			
			if (result.next()) {
				Integer id_medidaproduto = result.getInt("id");
				String descricao = result.getString("descricao");
				
				MedidaProduto m = new MedidaProduto();
				m.setId_medidaproduto(id_medidaproduto);
				m.setDescricao(descricao);
				
				return m;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	public void alterar(MedidaProduto m) {
		String sql = "update medidaproduto set descricao=? where id_medidaproduto=?";
		
		try{
			PreparedStatement ps = conexao.prepareStatement(sql);
			ps.setString(1,m.getDescricao());
			ps.setInt(2,m.getId_medidaproduto());
			
			ps.execute();
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
