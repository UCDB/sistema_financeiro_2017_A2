package repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import model.Servico;

public class ServicoRepositoryBanco {

	private Connection conexao = ConexaoFactory.criarConexao();
	
	public void cadastrar(Servico serv) {
		String sql = "insert into servico (descricao,valorservico,valorminimo,valormaximo,id_tiposervico,id_funcionario) values (?,?,?,?,?,?)";

		try {
			PreparedStatement ps = conexao.prepareStatement(sql);						
			ps.setString(1, serv.getDescricao());
			//ps.setString(3, serv.getTipo());
			ps.setDouble(2, serv.getValorservico());
			ps.setDouble(3, serv.getValorminimo());
			ps.setDouble(4, serv.getValormaximo());			
			ps.setInt(5, serv.getId_tiposervico());	
			ps.setInt(6, serv.getId_funcionario());
			
			ps.execute();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	public void alterar(Servico serv) {
		String sql = "update servico set id_servico=?,descricao=?,tipo=?,valorServico=?,valorMax=?,valorMin=? where id=?";

		try {
			PreparedStatement ps = conexao.prepareStatement(sql);
			ps.setInt(1, serv.getId_servico());
			ps.setString(2, serv.getDescricao());			
			ps.setDouble(4, serv.getValorservico());
			ps.setDouble(5, serv.getValormaximo());
			ps.setDouble(6, serv.getValorminimo());

			ps.execute();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	public void excluir(int id) {
		try {
			String sql = "delete from servico where id=?";
			PreparedStatement prepareStatement = conexao.prepareStatement(sql);
			prepareStatement.setInt(1, id);
			prepareStatement.execute();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public List<Servico> buscarTodos() {

		List<Servico> lista = new ArrayList<>();

		try {			
			String sql = "select * from servico order by id_servico";
			
			PreparedStatement prepareStatement = conexao.prepareStatement(sql);
			ResultSet result = prepareStatement.executeQuery();

			while (result.next()) {			
				Integer id = result.getInt("id_servico");				
				String descricao = result.getString("descricao");					
				Double valorServico = Double.parseDouble(result.getString("valorservico"));
				Double valorMin = Double.parseDouble(result.getString("valorminimo"));
				Double valorMax = Double.parseDouble(result.getString("valormaximo"));				
				Integer id_tiposervico = result.getInt("id_tiposervico");
				Integer id_funcionario = result.getInt("id_funcionario");
				
				Servico servico = new Servico(descricao,valorServico,valorMin,valorMax,id_tiposervico,id_funcionario);
				servico.setId_servico(id);
				

				lista.add(servico);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return lista;
	}
	
	

}
