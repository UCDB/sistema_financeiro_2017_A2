package repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Funcionario;

public class FuncionarioRepositoryBanco {
private Connection conexao = ConexaoFactory.criarConexao();
	
	public void cadastrar(Funcionario func) {
		String sql = "INSERT INTO funcionario (nome,endereco,cpf,rg,telefone,cep,email,info_add,contato) values (?,?,?,?,?,?,?,?,?)";

		try {
			PreparedStatement ps = conexao.prepareStatement(sql);

			ps.setString(1, func.getNome());
			ps.setString(2, func.getEndereco());
			ps.setString(3, func.getCpf());
			ps.setString(4, func.getRg());
			ps.setString(5, func.getTelefone());
			ps.setString(6, func.getCep());
			ps.setString(7, func.getEmail());
			ps.setString(8, func.getInfoAdc());
			ps.setString(9, func.getContato());
			
			ps.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void alterar(Funcionario func) {
		String sql = "UPDATE funcionario SET nome=?,endereco=?,cpf=?,rg=?,telefone=?,cep=?,email=?,info_add=?,contato=? WHERE id = ?";

		try {
			PreparedStatement ps = conexao.prepareStatement(sql);
			ps.setString(1, func.getNome());
			ps.setString(2, func.getEndereco());
			ps.setString(3, func.getCpf());
			ps.setString(4, func.getRg());
			ps.setString(5, func.getTelefone());
			ps.setString(6, func.getCep());
			ps.setString(7, func.getEmail());
			ps.setString(8, func.getInfoAdc());
			ps.setString(9, func.getContato());
			ps.setInt(10, func.getId());

			ps.execute();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	public void excluir(int id) {
		try {
			String sql = "DELETE FROM funcionario WHERE id = ?";
			PreparedStatement prepareStatement = conexao.prepareStatement(sql);
			prepareStatement.setInt(1, id);
			prepareStatement.execute();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public List<Funcionario> buscarTodos() {
		List<Funcionario> lista = new ArrayList<>();

		try {
			String sql = "SELECT * FROM funcionario ORDER BY nome";
			PreparedStatement prepareStatement = conexao.prepareStatement(sql);
			ResultSet result = prepareStatement.executeQuery();

			while (result.next()) {
				int id 			= result.getInt("id");
				String nome 	= result.getString("nome");
				String endereco = result.getString("endereco");
				String cpf 		= result.getString("cpf");
				String rg 		= result.getString("rg");
				String telefone = result.getString("telefone");
				String cep 		= result.getString("cep");
				String email 	= result.getString("email");
				String infoAdc 	= result.getString("info_add");
				String contato 	= result.getString("contato");

				Funcionario func = new Funcionario(nome,endereco,cpf,rg,telefone,cep,email,infoAdc,contato);
				func.setId(id);
				lista.add(func);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return lista;
	}

	public Funcionario buscarPorId(Integer id) {
		try {

			String sql = "SELECT * FROM funcionario WHERE id = ?";
			PreparedStatement prepareStatement = conexao.prepareStatement(sql);
			prepareStatement.setInt(1, id);
			ResultSet result = prepareStatement.executeQuery();

			if (result.next()) {
				int idFunc 		= result.getInt("id_funcionario");
				String nome 	= result.getString("nome");
				String endereco = result.getString("endereco");
				String cpf 		= result.getString("cpf");
				String rg 		= result.getString("rg");
				String telefone = result.getString("telefone");
				String cep 		= result.getString("cep");
				String email 	= result.getString("email");
				String infoAdc 	= result.getString("info_add");
				String contato 	= result.getString("contato");

				Funcionario func = new Funcionario(nome,endereco,cpf,rg,telefone,cep,email,infoAdc,contato);
				func.setId(idFunc);

				return func;
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}
}