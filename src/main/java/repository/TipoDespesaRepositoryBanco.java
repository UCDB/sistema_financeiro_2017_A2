package repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.TipoDespesa;
import model.TipoServico;

public class TipoDespesaRepositoryBanco {
	private static Connection conexao = ConexaoFactory.criarConexao();

	public static void cadastrar(TipoDespesa tipodespesa) {
		String sql = "insert into tipo_despesa values (default,?)";

		try {
			PreparedStatement ps = conexao.prepareStatement(sql);			
			ps.setString(1, tipodespesa.getDescricao());
			ps.execute();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void alterar(TipoDespesa tipodespesa) {
		String sql = "update tipo_despesa set descricao=? where id_tipodespesa=?";
		try {
			PreparedStatement ps = conexao.prepareStatement(sql);
			
			ps.setString(1, tipodespesa.getDescricao());
			ps.setInt(2, tipodespesa.getId_tipoDespesa());
			
			ps.execute();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public void excluir(int id) {
		try {
			String sql = "delete from tipo_despesa where id_tipodespesa=?";
			PreparedStatement prepareStatement = conexao.prepareStatement(sql);
			prepareStatement.setInt(1, id);
			prepareStatement.execute();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public List<TipoDespesa> buscarTodos() {
		List<TipoDespesa> lista = new ArrayList<>();

		try {			
			String sql = "select * from tipo_servico order by id_tipodespesa";
			PreparedStatement prepareStatement = conexao.prepareStatement(sql);
			ResultSet result = prepareStatement.executeQuery();

			while (result.next()) {
				
				Integer id_tipoDespesa = result.getInt("id_tipodespesa");				
				String descricao = result.getString("descricao");
				
				TipoDespesa tipo_Despesa = new TipoDespesa(descricao);
				

				lista.add(tipo_Despesa);

			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return lista;
	}
}
