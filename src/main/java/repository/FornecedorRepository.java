package repository;

import java.util.List;

import model.Fornecedor;

public interface FornecedorRepository {
	public void cadastrar(Fornecedor fornecedor) ;

	public List<Fornecedor> buscarTodos() ;

	public void alterar(Fornecedor fornecedor) ;
	
	public void salvar(Fornecedor fornecedor) ;

	public Fornecedor buscarPorId(Integer id) ;
	
	public void excluir(int id) ;
}
