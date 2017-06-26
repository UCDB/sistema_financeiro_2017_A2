package repository;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Cliente;
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
		String sql = "update servico set descricao=?,valorServico=?,valorminimo=?,valormaximo=?,id_tiposervico=?,id_funcionario=? where id_servico=?";

		try {
			PreparedStatement ps = conexao.prepareStatement(sql);
			
			ps.setString(1, serv.getDescricao());			
			ps.setDouble(2, serv.getValorservico());
			ps.setDouble(3, serv.getValorminimo());
			ps.setDouble(4, serv.getValormaximo());
			ps.setInt(5, serv.getId_tiposervico());
			ps.setInt(6, serv.getId_funcionario());
			ps.setInt(7, serv.getId_servico());
			
			ps.execute();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	public void excluir(int id) {
		try {
			String sql = "DELETE FROM servico WHERE id_servico = "+id;
			PreparedStatement prepareStatement = conexao.prepareStatement(sql);
			prepareStatement.execute();			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public List<Servico> buscarTodos() {

		List<Servico> lista = new ArrayList<>();

		try {			
			String sql = "select * from servico order by descricao";			
				
			PreparedStatement prepareStatement = conexao.prepareStatement(sql);
			ResultSet result = prepareStatement.executeQuery();

			while (result.next()) {			
				Integer id_servico = result.getInt("id_servico");				
				String descricao = result.getString("descricao");					
				Double valorServico = Double.parseDouble(result.getString("valorservico"));
				Double valorMin = Double.parseDouble(result.getString("valorminimo"));
				Double valorMax = Double.parseDouble(result.getString("valormaximo"));				
				Integer id_tiposervico = result.getInt("id_tiposervico");
				Integer id_funcionario = result.getInt("id_funcionario");
				Servico servico = new Servico(descricao,valorServico,valorMin,valorMax,id_tiposervico,id_funcionario);
				servico.setId_servico(id_servico);

				lista.add(servico);

			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return lista;
	}
	
	public Servico buscarPorId(Integer id) {
		try {
			String sql = "SELECT * FROM servico WHERE id_servico = "+id;
			PreparedStatement prepareStatement = conexao.prepareStatement(sql);
			ResultSet result = prepareStatement.executeQuery();
			if( result.next() ){
				String descricao = result.getString("descricao");					
				Double valorServico = Double.parseDouble(result.getString("valorservico"));
				Double valorMin = Double.parseDouble(result.getString("valorminimo"));
				Double valorMax = Double.parseDouble(result.getString("valormaximo"));				
				Integer id_tiposervico = result.getInt("id_tiposervico");
				Integer id_funcionario = result.getInt("id_funcionario");
				Servico servico = new Servico(descricao,valorServico,valorMin,valorMax,id_tiposervico,id_funcionario);
				
				servico.setId_servico(id);
				return servico;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	

}
