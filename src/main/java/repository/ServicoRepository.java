package repository;

import model.Servico;

public interface ServicoRepository {

	public void cadastrar(Servico serv);
	
	public Servico buscarPorId(Integer id);

}
