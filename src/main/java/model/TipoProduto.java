package model;

public class TipoProduto {
	private Integer id_tipoproduto;
	private String descricao;
	
	public TipoProduto(String descricao) {
		
		this.descricao = descricao;
	}
	
	public TipoProduto(){}

	public Integer getId_tipoproduto() {
		return id_tipoproduto;
	}

	public void setId_tipoproduto(Integer id_tipoproduto) {
		this.id_tipoproduto = id_tipoproduto;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	};
	
	
	
}
