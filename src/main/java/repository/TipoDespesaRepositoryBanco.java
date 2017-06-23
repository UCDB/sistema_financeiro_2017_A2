package repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.TipoDespesa;

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
			String sql = "select * from tipo_despesa order by id_tipodespesa";
			PreparedStatement prepareStatement = conexao.prepareStatement(sql);
			ResultSet result = prepareStatement.executeQuery();

			while (result.next()) {
				
				Integer id = result.getInt("id_tipodespesa");				
				String descricao = result.getString("descricao");
				
				TipoDespesa tipoDespesa = new TipoDespesa(descricao);
				tipoDespesa.setId_tipoDespesa(id);

				lista.add(tipoDespesa);

			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return lista;
	}
}
