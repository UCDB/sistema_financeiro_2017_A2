package repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Produto;
import model.Servico;
import model.TipoDespesa;
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
			ps.setInt(2, prod.getId_tipoproduto());
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
				String descricao = result.getString("descricao");
				
				TipoProduto tipo_Produto = new TipoProduto();
				tipo_Produto.setDescricao(descricao);
				
				

				lista.add(tipo_Produto);

			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
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
