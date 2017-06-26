package repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Fornecedor;

public class FornecedorRepositoryBanco implements FornecedorRepository{
	Connection conexao = ConexaoFactory.criarConexao();

	@Override
	public void cadastrar(Fornecedor fornecedor) {
		String sql = "INSERT INTO fornecedor (nome,endereco,cpf_cnpj,rg_ie,telefone,cep,contato,info,email)"
				+ "				VALUES (?,?,?,?,?,?,?,?,?)";
		
		try{
			PreparedStatement ps = conexao.prepareStatement(sql);
			 ps.setString(1, fornecedor.getNome());
			 ps.setString(2, fornecedor.getEndereco());
			 ps.setString(3, fornecedor.getCPF_CNPJ());
			 ps.setString(4, fornecedor.getRG_IE());
			 ps.setString(5, fornecedor.getTelefone());
			 ps.setString(6, fornecedor.getCEP());
			 ps.setString(7, fornecedor.getContato());
			 ps.setString(8, fornecedor.getInfo());
			 ps.setString(9, fornecedor.getEmail());
			 
			 ps.execute();
			 
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	@Override
	public List<Fornecedor> buscarTodos() {
		
		List<Fornecedor> lista = new ArrayList<>();
		
		try {
			String sql = "SELECT * FROM fornecedor"
					+ "			ORDER BY name";
			PreparedStatement ps = conexao.prepareStatement(sql);
			ResultSet result = ps.executeQuery();
			do {
				Integer id = result.getInt("id");
				String nome = result.getString("nome");
				String endereco = result.getString("endereco");
				String cpf_cnpj = result.getString("cpf_cnpj");
				String rg_ie = result.getString("rg_ie");
				String telefone = result.getString("telefone");
				String cep = result.getString("cep");
				String contato = result.getString("contato");
				String informacao = result.getString("informacao");
				String email = result.getString("email");
				
				ps.executeQuery();
				
			} while (result.next());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return lista;
	}

	@Override
	public void alterar(Fornecedor fornecedor) {
		String sql = "UPDATE fornecedor SET nome=?, endereco=?, cpf_cnpj=?, rg_ie=?, telefone=?, cep=?, contato=?, info=?, email=?"
				+ "			WHERE id=?";
		try {
			PreparedStatement ps = conexao.prepareStatement(sql);
			 ps.setString(1, fornecedor.getNome());
			 ps.setString(2, fornecedor.getEndereco());
			 ps.setString(3, fornecedor.getCPF_CNPJ());
			 ps.setString(4, fornecedor.getRG_IE());
			 ps.setString(5, fornecedor.getTelefone());
			 ps.setString(6, fornecedor.getCEP());
			 ps.setString(7, fornecedor.getContato());
			 ps.setString(8, fornecedor.getInfo());
			 ps.setString(9, fornecedor.getEmail());
			 ps.setInt(10, fornecedor.getId());
			 
			 ps.execute();
			 
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	@Override
	public Fornecedor buscarPorId(Integer id) {
		try {
			String sql = "SELECT * FROM fornecedor"
					+ "			WHERE id=?";
			PreparedStatement ps = conexao.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet result = ps.executeQuery();
			
			if(result.next()){
				int id_forn = result.getInt("id");
				String nome = result.getString("nome");
				String endereco = result.getString("endereco");
				String cpf_cnpj = result.getString("cpf_cnpj");
				String rg_ie = result.getString("rg_ie");
				String telefone = result.getString("telefone");
				String cep = result.getString("cep");
				String contato = result.getString("contato");
				String informacao = result.getString("informacao");
				String email = result.getString("email");
				
				Fornecedor f = new Fornecedor();
				f.setId(id_forn);
				f.setNome(nome);
				f.setEndereco(endereco);
				f.setCPF_CNPJ(cpf_cnpj);
				f.setRG_IE(rg_ie);
				f.setTelefone(telefone);
				f.setCEP(cep);
				f.setContato(contato);
				f.setInfo(informacao);
				f.setEmail(email);
				
				return f;
				
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void excluir(int id) {
		try {
			String sql = "DELETE FROM fornecedor"
					+ "			WHERE id=?";
			PreparedStatement ps = conexao.prepareStatement(sql);
			ps.setInt(1, id);
			ps.execute();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	@Override
	public void salvar(Fornecedor fornecedor) {
		if (fornecedor.getId() != null) {
			cadastrar(fornecedor);
		} else {
			alterar(fornecedor);
		}
	}

	
}
