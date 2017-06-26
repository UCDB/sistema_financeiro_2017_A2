package repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Cliente;

public class ClienteRepositoryBanco {

	private Connection conexao = ConexaoFactory.criarConexao();
	
	public void cadastrar(Cliente cl) {
		String sql = "insert into cliente (id,nome,endereco,cpf,rg,telefone,cep,contato,info,email) values (?,?,?,?,?,?,?,?,?,?)";

		try {
			PreparedStatement ps = conexao.prepareStatement(sql);
			ps.setInt(1, cl.getId());
			ps.setString(2, cl.getNome());
			ps.setString(3, cl.getEndereco());
			ps.setString(4, cl.getCpf());
			ps.setString(5, cl.getRg());
			ps.setString(6, cl.getTelefone());
			ps.setString(7, cl.getCep());
			ps.setString(8, cl.getContato());
			ps.setString(9, cl.getInfo());
			ps.setString(10, cl.getEmail());
			
			ps.execute();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	public void alterar(Cliente cl){
		String sql = "update cliente set nome=?,endereco=?,cpf=?,rg=?,telefone=?,cep=?,contato=?,info=?,email=? where id=?";

		try {
			PreparedStatement ps = conexao.prepareStatement(sql);
			ps.setString(1, cl.getNome());
			ps.setString(2, cl.getEndereco());
			ps.setString(3, cl.getCpf());
			ps.setString(4, cl.getRg());
			ps.setString(5, cl.getTelefone());
			ps.setString(6, cl.getCep());
			ps.setString(7, cl.getContato());
			ps.setString(8, cl.getInfo());
			ps.setString(9, cl.getEmail());

			ps.execute();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void excluir(int id) {
		try {
			String sql = "delete from cliente where id=?";
			PreparedStatement prepareStatement = conexao.prepareStatement(sql);
			prepareStatement.setInt(1, id);
			prepareStatement.execute();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

public List<Cliente> buscarTodos() {
		List<Cliente> lista = new ArrayList<>();

		try {
			String sql = "Select * from cliente order by nome";
			PreparedStatement prepareStatement = conexao.prepareStatement(sql);
			ResultSet result = prepareStatement.executeQuery();

			while (result.next()) {
				Integer id = result.getInt("id");
				String nome = result.getString("nome");
				String endereco = result.getString("endereco");
				String cpf = result.getString("cpf");
				String rg = result.getString("rg");
				String telefone = result.getString("telefone");
				String cep = result.getString("cep");
				String contato= result.getString("contato");
				String info	= result.getString("info");
				String email = result.getString("email");
				
				prepareStatement.executeQuery();
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return lista;
	}
	
	
	public Cliente buscarPorId(Integer id) {
		try {

			String sql = "select * from cliente where id=?";
			PreparedStatement prepareStatement = conexao.prepareStatement(sql);
			prepareStatement.setInt(1, id);
			ResultSet result = prepareStatement.executeQuery();

			if (result.next()) {
				int idCl = result.getInt("id");
				String nome = result.getString("nome");
				String endereco = result.getString("endereco");
				String cpf = result.getString("cpf");
				String rg = result.getString("rg");
				String telefone = result.getString("telefone");
				String cep = result.getString("cep");
				String contato= result.getString("contato");
				String info	= result.getString("info");
				String email = result.getString("email");
				
				Cliente cl = new Cliente();
				
				cl.setId(idCl);
				cl.setNome(nome);
				cl.setEndereco(endereco);
				cl.setCpf(cpf);
				cl.setRg(rg);
				cl.setTelefone(telefone);
				cl.setCep(cep);
				cl.setContato(contato);
				cl.setInfo(info);
				cl.setEmail(email);

				return cl;

			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}

	
}
