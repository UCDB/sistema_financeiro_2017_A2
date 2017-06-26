package repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Caixa;
import model.Cliente;


public class CaixaRepositoryBanco {

	private Connection conexao = ConexaoFactory.criarConexao();

	public void cadastrar(Caixa caixa) {
		String sql = "insert into caixa values (default,?,?,?,?,?,?,?,?)";

		try {
			PreparedStatement ps = conexao.prepareStatement(sql);
			ps.setInt(5, caixa.getFormapagamento());
			ps.setInt(7, caixa.getId_cliente());
			ps.setInt(6, caixa.getId_tipodespesa());
			ps.setDouble(3, caixa.getValor());
			ps.setBoolean(4, caixa.getStatus());
			ps.setString(2, caixa.getDescricao());
			ps.setString(1, caixa.getData());
			ps.setInt(8, caixa.getId_fornecedor());
			
			ps.execute();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void alterar(Caixa caixa) {
		String sql = "update caixa set data=?,descricao=?, valor=?, status=?, formapagamento=?, id_tipodespesa=?, id_cliente=?, id_fornecedor=? where id_caixa=?";

		try {
			PreparedStatement ps = conexao.prepareStatement(sql);
			ps.setString(1, caixa.getData());
			ps.setString(2, caixa.getDescricao());
			ps.setDouble(3, caixa.getValor());
			ps.setBoolean(4, caixa.getStatus());
			ps.setInt(5, caixa.getFormapagamento());
			ps.setInt(6, caixa.getId_tipodespesa());
			ps.setInt(7, caixa.getId_cliente());
			ps.setInt(8, caixa.getId_caixa());
			ps.setInt(9, caixa.getId_fornecedor());

			ps.execute();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void excluir(int id) {
		try {
			String sql = "DELETE FROM caixa WHERE id_caixa = "+id;
			PreparedStatement prepareStatement = conexao.prepareStatement(sql);
			prepareStatement.execute();			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public List<Caixa> buscarTodos() {

		List<Caixa> lista = new ArrayList<>();

		try {			
			String sql = "select * from caixa order by descricao";
			
			PreparedStatement prepareStatement = conexao.prepareStatement(sql);
			ResultSet result = prepareStatement.executeQuery();

			while (result.next()) {
				Integer id_caixa = result.getInt("id_caixa");
				String data = result.getString("data");
				String descricao = result.getString("descricao");
				Double valor = (result.getDouble("valor"));
				Boolean status = result.getBoolean("status");
				Integer formapagamento = result.getInt("formapagamento");
				Integer id_tipodespesa = result.getInt("id_tipodespesa");
				Integer id_cliente = result.getInt("id_cliente");
				Integer id_fornecedor = result.getInt("id_fornecedor");
				
				Caixa caix = new Caixa(data,descricao,valor,status,formapagamento,id_tipodespesa,id_cliente,id_fornecedor);
				caix.setId_caixa(id_caixa);

				lista.add(caix);

			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return lista;
	}
	
	public Caixa buscarPorId(Integer id) {
		try {
			String sql = "SELECT * FROM caixa WHERE id = "+id;
			PreparedStatement prepareStatement = conexao.prepareStatement(sql);
			ResultSet result = prepareStatement.executeQuery();
			if( result.next() ){
				String data = result.getString("data");
				String descricao = result.getString("descricao");
				Double valor = (result.getDouble("valor"));
				Boolean status = result.getBoolean("status");
				Integer formapagamento = result.getInt("formapagamento");
				Integer id_tipodespesa = result.getInt("id_tipodespesa");
				Integer id_cliente = result.getInt("id_cliente");
				Integer id_fornecedor = result.getInt("id_fornecedor");
				
				Caixa caix = new Caixa(data,descricao,valor,status,formapagamento,id_tipodespesa,id_cliente,id_fornecedor);
				caix.setId_caixa(id);
				return caix;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
}
