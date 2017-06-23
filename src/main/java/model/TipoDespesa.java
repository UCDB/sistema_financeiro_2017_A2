package model;

public class TipoDespesa {
	public Integer id_tipoDespesa;
	public String descricao;
	
	public TipoDespesa(String descricao) {
	
		this.descricao=descricao;
	}
	public TipoDespesa() {
		// TODO Auto-generated constructor stub
	}
	public Integer getId_tipoDespesa() {
		return id_tipoDespesa;
	}
	public void setId_tipoDespesa(Integer id_tipoDespesa) {
		this.id_tipoDespesa = id_tipoDespesa;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	
}
