package repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.TipoProduto;

public class TipoProdutoRepositoryBanco {

	private Connection conexao = ConexaoFactory.criarConexao();
	
	public void cadastrar(TipoProduto tprod) {
		String sql = "insert into tipo_produto values (default,?)";

		try {
			PreparedStatement ps = conexao.prepareStatement(sql);
			ps.setString(1, tprod.getDescricao());
			
			ps.execute();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	public void alterar(TipoProduto prod) {
		String sql = "update tipo_produto set descricao=? where id_tipoproduto=?";

		try {
			PreparedStatement ps = conexao.prepareStatement(sql);
			ps.setString(1, prod.getDescricao());

			ps.execute();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	public  List<TipoProduto> buscarTodos() {
		
		List<TipoProduto> lista = new ArrayList<>();

		try {			
			String sql = "select * from tipo_produto order by id_tipoproduto";
			PreparedStatement prepareStatement = conexao.prepareStatement(sql);
			ResultSet result = prepareStatement.executeQuery();

			while (result.next()) {
				Integer id = result.getInt("id_tipoproduto");
				String descricao = result.getString("descricao");
				
				TipoProduto tipoProduto = new TipoProduto();
				tipoProduto.setId_tipoproduto(id);
				tipoProduto.setDescricao(descricao);
				
				

				lista.add(tipoProduto);

			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return lista;
	}
	
	
	
	public void excluir(int id) {
		try {
			String sql = "delete from tipo_produto where id_tipoproduto=?";
			PreparedStatement prepareStatement = conexao.prepareStatement(sql);
			prepareStatement.setInt(1, id);
			prepareStatement.execute();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
