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
		String sql = "INSERT INTO cliente (nome_razao,endereco,telefone,email,cpf_cnpj,rg_ie,cep,contato,info_add)"
				   + "VALUES ('"+cl.getNomeRazao()+"','"+cl.getEndereco()+"','"+cl.getTelefone()+"','"+cl.getEmail()+"','"+cl.getCpfCnpj()+"','"+cl.getRgIe()+"','"+cl.getCep()+"','"+cl.getContato()+"','"+cl.getInfoAdd()+"')";		
		
		try {
			PreparedStatement ps = conexao.prepareStatement(sql);
			ps.execute();			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void alterar(Cliente cl){
		String sql = "UPDATE cliente "
				   + "SET nome_razao = ?, endereco = ?, telefone = ?, email = ?, cpf_cnpj = ?, rg_ie = ?, cep = ?, contato = ?, info_add = ? "
				   + "WHERE id=?";
		try {
			PreparedStatement ps = conexao.prepareStatement(sql);
			ps.setString(1, cl.getNomeRazao());
			ps.setString(2, cl.getEndereco());
			ps.setString(3, cl.getTelefone());
			ps.setString(4, cl.getEmail());
			ps.setString(5, cl.getCpfCnpj());
			ps.setString(6, cl.getRgIe());
			ps.setString(7, cl.getCep());
			ps.setString(8, cl.getContato());
			ps.setString(9, cl.getInfoAdd());
			ps.setInt(10, cl.getId());
			
			ps.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void excluir(int id) {
		try {
			String sql = "DELETE FROM cliente WHERE id = "+id;
			PreparedStatement prepareStatement = conexao.prepareStatement(sql);
			prepareStatement.execute();			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public List<Cliente> buscarTodos() {
		List<Cliente> lista = new ArrayList<>();

		try {
			String sql = "SELECT * FROM cliente ORDER BY nome_razao";
			PreparedStatement prepareStatement = conexao.prepareStatement(sql);
			ResultSet result = prepareStatement.executeQuery();

			while (result.next()) {
				int id 				= result.getInt("id");
				String nome_razao 	= result.getString("nome_razao");
				String endereco 	= result.getString("endereco");
				String telefone 	= result.getString("telefone");
				String email 		= result.getString("email");
				String cpf_cnpj 	= result.getString("cpf_cnpj");
				String rg_ie 		= result.getString("rg_ie");
				String cep 			= result.getString("cep");
				String contato		= result.getString("contato");
				String info_add		= result.getString("info_add");
				
				Cliente cl = new Cliente(nome_razao,endereco,telefone,email,cpf_cnpj,rg_ie,cep,contato,info_add);
				cl.setId(id);
				lista.add(cl);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lista;
	}

	public Cliente buscarPorId(Integer id) {
		try {
			String sql = "SELECT * FROM cliente WHERE id = "+id;
			PreparedStatement prepareStatement = conexao.prepareStatement(sql);
			ResultSet result = prepareStatement.executeQuery();
			if( result.next() ){
				String nome_razao 	= result.getString("nome_razao");
				String endereco 	= result.getString("endereco");
				String telefone 	= result.getString("telefone");
				String email 		= result.getString("email");
				String cpf_cnpj 	= result.getString("cpf_cnpj");
				String rg_ie 		= result.getString("rg_ie");
				String cep 			= result.getString("cep");
				String contato		= result.getString("contato");
				String info_add		= result.getString("info_add");

				Cliente cl = new Cliente(nome_razao,endereco,telefone,email,cpf_cnpj,rg_ie,cep,contato,info_add);
				cl.setId(id);
				return cl;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}