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
		String sql = "insert into funcionario (nome,endereco,cpf,rg,telefone,cep,email,infoAdc) values (?,?,?,?,?,?,?,?)";

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
			
			ps.execute();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	
	public void alterar(Funcionario func) {
		String sql = "update funcionario set nome=?,endereco=?,cpf=?,rg=?,telefone=?,cep=?,email=?,infoAdc=? where id_funcionario=?";

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
			ps.setInt(9, func.getId());

			ps.execute();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	public void excluir(int id) {
		try {
			String sql = "delete from funcionario where id_funcionario=?";
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
			String sql = "Select * from funcionario order by nome";
			PreparedStatement prepareStatement = conexao.prepareStatement(sql);
			ResultSet result = prepareStatement.executeQuery();

			while (result.next()) {
				int id = result.getInt("id");
				String nome = result.getString("nome");
				String endereco = result.getString("endereco");
				String cpf = result.getString("cpf");
				String rg = result.getString("rg");
				String telefone = result.getString("telefone");
				String cep = result.getString("cep");
				String email = result.getString("email");
				String infoAdc = result.getString("infoAdc");


				Funcionario f = new Funcionario();
				f.setId(id);
				f.setNome(nome);
				f.setEndereco(endereco);
				f.setCpf(cpf);
				f.setRg(rg);
				f.setTelefone(telefone);
				f.setCep(cep);
				f.setEmail(email);
				f.setInfoAdc(infoAdc);

				lista.add(f);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return lista;
	}
	
	
	public Funcionario buscarPorId(Integer id) {
		try {

			String sql = "select * from funcionario where id_funcionario=?";
			PreparedStatement prepareStatement = conexao.prepareStatement(sql);
			prepareStatement.setInt(1, id);
			ResultSet result = prepareStatement.executeQuery();

			if (result.next()) {
				int idFunc = result.getInt("id_funcionario");
				String nome = result.getString("nome");
				String endereco = result.getString("endereco");
				String cpf = result.getString("cpf");
				String rg = result.getString("rg");
				String telefone = result.getString("telefone");
				String cep = result.getString("cep");
				String email = result.getString("email");
				String infoAdc = result.getString("infoAdc");
				
				Funcionario f = new Funcionario();
				f.setId(idFunc);
				f.setNome(nome);
				f.setEndereco(endereco);
				f.setCpf(cpf);
				f.setRg(rg);
				f.setTelefone(telefone);
				f.setCep(cep);
				f.setEmail(email);
				f.setInfoAdc(infoAdc);

				return f;

			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}


}
