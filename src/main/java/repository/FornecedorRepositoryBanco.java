package repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Fornecedor;

public class FornecedorRepositoryBanco{
	private Connection conexao = ConexaoFactory.criarConexao();

	public void cadastrar(Fornecedor fornecedor) {
		String sql = "INSERT INTO fornecedor (nome_razao, endereco, telefone, email, cpf_cnpj, rg_ie, cep, contato, info_add)"
				   + "VALUES (?,?,?,?,?,?,?,?,?)";

		try{
			PreparedStatement ps = conexao.prepareStatement(sql);
			ps.setString(1, fornecedor.getNomeRazao());
			ps.setString(2, fornecedor.getEndereco());
			ps.setString(3, fornecedor.getTelefone());
			ps.setString(4, fornecedor.getEmail());
			ps.setString(5, fornecedor.getCpfCnpj());
			ps.setString(6, fornecedor.getRgIe());
			ps.setString(7, fornecedor.getCep());
			ps.setString(8, fornecedor.getContato());
			ps.setString(9, fornecedor.getInfoAdd());

			ps.execute();
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	public List<Fornecedor> buscarTodos() {
		List<Fornecedor> lista = new ArrayList<>();

		try {
			String sql = "SELECT * FROM fornecedor ORDER BY nome_razao";
			PreparedStatement ps = conexao.prepareStatement(sql);
			ResultSet result = ps.executeQuery();

			while (result.next()) {
				int id 				= result.getInt("id");
				String nome_razao	= result.getString("nome_razao");
				String endereco 	= result.getString("endereco");
				String telefone 	= result.getString("telefone");
				String email 		= result.getString("email");
				String cpf_cnpj 	= result.getString("cpf_cnpj");
				String rg_ie 		= result.getString("rg_ie");
				String cep 			= result.getString("cep");
				String contato 		= result.getString("contato");
				String info_add 	= result.getString("info_add");

				Fornecedor forn = new Fornecedor(nome_razao,endereco,telefone,email,cpf_cnpj,rg_ie,cep,contato,info_add);
				forn.setId(id);
				lista.add(forn);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return lista;
	}

	public void alterar(Fornecedor fornecedor) {
		String sql = "UPDATE fornecedor "
				   + "SET nome_razao = ?, endereco = ?, telefone=?, email = ?, cpf_cnpj = ?, rg_ie = ?, cep = ?, contato = ?, info_add = ?"
				   + "WHERE id=?";
		
		try {
			PreparedStatement ps = conexao.prepareStatement(sql);
			 ps.setString(1, fornecedor.getNomeRazao());
			 ps.setString(2, fornecedor.getEndereco());
			 ps.setString(3, fornecedor.getTelefone());
			 ps.setString(4, fornecedor.getEmail());
			 ps.setString(5, fornecedor.getCpfCnpj());
			 ps.setString(6, fornecedor.getRgIe());
			 ps.setString(7, fornecedor.getCep());
			 ps.setString(8, fornecedor.getContato());
			 ps.setString(9, fornecedor.getInfoAdd());
			 ps.setInt(10, fornecedor.getId());

			 ps.execute();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	public Fornecedor buscarPorId(Integer id) {
		try {
			String sql = "SELECT * FROM fornecedor WHERE id = "+id;
			PreparedStatement ps = conexao.prepareStatement(sql);
			ResultSet result = ps.executeQuery();
			
			if(result.next()){
				int id_forn 		= result.getInt("id");
				String nome_razao 	= result.getString("nome_razao");
				String endereco 	= result.getString("endereco");
				String telefone 	= result.getString("telefone");
				String email 		= result.getString("email");
				String cpf_cnpj 	= result.getString("cpf_cnpj");
				String rg_ie 		= result.getString("rg_ie");
				String cep 			= result.getString("cep");
				String contato 		= result.getString("contato");
				String info_add 	= result.getString("info_add");

				Fornecedor f = new Fornecedor(nome_razao, endereco, telefone, email, cpf_cnpj, rg_ie, cep, contato, info_add);
				f.setId(id_forn);

				return f;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public void excluir(int id) {
		try {
			String sql = "DELETE FROM fornecedor WHERE id = "+id;
			PreparedStatement ps = conexao.prepareStatement(sql);
			ps.execute();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}	
}