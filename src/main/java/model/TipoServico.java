package model;

public class TipoServico {
	public Integer id_tipoServico;
	public String descricao;	
	
	
	public TipoServico(String descricao) {		
		this.descricao=descricao;
	}
	public TipoServico() {
		// TODO Auto-generated constructor stub
	}
	public Integer getId_tipoServico() {
		return id_tipoServico;
	}
	public void setId_tipoServico(Integer id_tipoServico) {
		this.id_tipoServico = id_tipoServico;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
}
