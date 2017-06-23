package model;



public class Caixa {
	private Integer id_caixa;
	private String data;
	private String descricao;
	private Double valor;
	private Boolean status;
	private Integer formapagamento,id_tipodespesa,id_cliente,id_fornecedor;
	
	
	public Caixa(){}

	public Caixa(String data, String descricao, Double valor, Boolean status, Integer formapagamento, Integer id_tipodespesa,
			Integer id_cliente, Integer id_fornecedor) {
		super();
		this.data = data;
		this.descricao = descricao;
		this.valor = valor;
		this.status = status;
		this.formapagamento = formapagamento;
		this.id_tipodespesa = id_tipodespesa;
		this.id_cliente = id_cliente;
		this.id_fornecedor = id_fornecedor;
	}

	public Integer getId_caixa() {
		return id_caixa;
	}

	public void setId_caixa(Integer id_caixa) {
		this.id_caixa = id_caixa;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	public Integer getFormapagamento() {
		return formapagamento;
	}

	public void setFormapagamento(Integer formapagamento) {
		this.formapagamento = formapagamento;
	}

	public Integer getId_tipodespesa() {
		return id_tipodespesa;
	}

	public void setId_tipodespesa(Integer id_tipodespesa) {
		this.id_tipodespesa = id_tipodespesa;
	}

	public Integer getId_cliente() {
		return id_cliente;
	}

	public void setId_cliente(Integer id_cliente) {
		this.id_cliente = id_cliente;
	}

	public Integer getId_fornecedor() {
		return id_fornecedor;
	}

	public void setId_fornecedor(Integer id_fornecedor) {
		this.id_fornecedor = id_fornecedor;
	}





	

	

}
