package repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.TipoServico;

public class TipoServicoRepositoryBanco {
	private static Connection conexao = ConexaoFactory.criarConexao();
	
	public void cadastrar(TipoServico tiposervico) {
		String sql = "insert into tipo_servico values (default,?)";

		try {
			PreparedStatement ps = conexao.prepareStatement(sql);			
			ps.setString(1, tiposervico.getDescricao());
			ps.execute();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void alterar(TipoServico tiposervico) {
		String sql = "update tipo_servico set descricao=? where id_tiposervico=?";
		try {
			PreparedStatement ps = conexao.prepareStatement(sql);
			
			ps.setString(1, tiposervico.getDescricao());
			ps.execute();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
	
	public void excluir(int id) {
		try {
			String sql = "delete from tipo_servico where id_tiposervico=?";
			PreparedStatement prepareStatement = conexao.prepareStatement(sql);
			prepareStatement.setInt(1, id);
			prepareStatement.execute();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static List<TipoServico> buscarTodos() {
		
		List<TipoServico> lista = new ArrayList<>();

		try {			
			String sql = "select * from tipo_servico order by id_tiposervico";
			PreparedStatement prepareStatement = conexao.prepareStatement(sql);
			ResultSet result = prepareStatement.executeQuery();

			while (result.next()) {			
				Integer id = result.getInt("id_tiposervico");				
				String descricao = result.getString("descricao");
				
				TipoServico tipoServico = new TipoServico(descricao);
				tipoServico.setId_tipoServico(id);

				lista.add(tipoServico);

			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return lista;
	}
}
