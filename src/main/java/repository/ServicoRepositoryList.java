package repository;

import java.util.ArrayList;
import java.util.List;

import model.Servico;

public class ServicoRepositoryList implements ServicoRepository{
	
	private int id=0;
	private List<Servico> lista = new ArrayList<>();
	
	@Override
	public void cadastrar(Servico serv) {
		serv.setId_funcionario(id++);		
		lista.add(serv);
	}

	@Override
	public Servico buscarPorId(Integer id) {
		for (int i = 0; i < lista.size(); i++){
			if (lista.get(i).getId_funcionario().equals(id)){
				return lista.get(i);
			}
		}
		return null;
	}

}
