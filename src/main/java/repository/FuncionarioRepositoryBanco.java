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
		String sql = "INSERT INTO funcionario (nome, endereco, telefone, email, cpf, rg, cep, contato, info_add)"
				   + "VALUES (?,?,?,?,?,?,?,?,?)";

		try {
			PreparedStatement ps = conexao.prepareStatement(sql);

			ps.setString(1, func.getNome());
			ps.setString(2, func.getEndereco());
			ps.setString(3, func.getTelefone());
			ps.setString(4, func.getEmail());
			ps.setString(5, func.getCpf());
			ps.setString(6, func.getRg());
			ps.setString(7, func.getCep());
			ps.setString(8, func.getContato());
			ps.setString(9, func.getInfoAdd());

			ps.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void alterar(Funcionario func) {
		String sql = "UPDATE funcionario "
				   + "SET nome = ?, endereco = ?, telefone = ?, email = ?, cpf = ?, rg = ?, cep = ?, contato = ?, info_add = ? "
				   + "WHERE id = ?";

		try {
			PreparedStatement ps = conexao.prepareStatement(sql);
			ps.setString(1, func.getNome());
			ps.setString(2, func.getEndereco());
			ps.setString(3, func.getTelefone());
			ps.setString(4, func.getEmail());
			ps.setString(5, func.getCpf());
			ps.setString(6, func.getRg());
			ps.setString(7, func.getCep());
			ps.setString(8, func.getContato());
			ps.setString(9, func.getInfoAdd());
			ps.setInt(10, func.getId());

			ps.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void excluir(int id) {
		try {
			String sql = "DELETE FROM funcionario WHERE id = "+id;
			PreparedStatement prepareStatement = conexao.prepareStatement(sql);
			prepareStatement.execute();
		} catch (SQLException e) {
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
				String telefone = result.getString("telefone");
				String email 	= result.getString("email");
				String cpf 		= result.getString("cpf");
				String rg 		= result.getString("rg");
				String cep 		= result.getString("cep");
				String contato 	= result.getString("contato");
				String info_add	= result.getString("info_add");

				Funcionario func = new Funcionario(nome,endereco,telefone,email,cpf,rg,cep,contato,info_add);
				func.setId(id);
				lista.add(func);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lista;
	}

	public Funcionario buscarPorId(Integer id) {
		try {
			String sql = "SELECT * FROM funcionario WHERE id = "+id;
			PreparedStatement prepareStatement = conexao.prepareStatement(sql);
			ResultSet result = prepareStatement.executeQuery();

			if (result.next()) {
				int id_func		= result.getInt("id");
				String nome 	= result.getString("nome");
				String endereco = result.getString("endereco");
				String telefone = result.getString("telefone");
				String email 	= result.getString("email");
				String cpf 		= result.getString("cpf");
				String rg 		= result.getString("rg");
				String cep 		= result.getString("cep");
				String contato 	= result.getString("contato");
				String info_add 	= result.getString("info_add");

				Funcionario func = new Funcionario(nome,endereco,telefone,email,cpf,rg,cep,contato,info_add);
				func.setId(id_func);

				return func;
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}
}