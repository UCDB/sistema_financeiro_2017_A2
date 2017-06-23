package model;

public class Servico {
	
	private Integer id_servico;	
	private String descricao;
	private Double valorservico;
	private Double valorminimo;
	private Double valormaximo;
	private Integer id_tiposervico;
	private Integer id_funcionario;
	
	public Servico(){}

	public Servico(String descricao, Double valorservico, Double valorminimo, Double valormaximo,
			Integer id_tiposervico, Integer id_funcionario) {
		super();
		this.descricao = descricao;
		this.valorservico = valorservico;
		this.valorminimo = valorminimo;
		this.valormaximo = valormaximo;
		this.id_tiposervico = id_tiposervico;
		this.id_funcionario = id_funcionario;
	}

	public Integer getId_servico() {
		return id_servico;
	}

	public void setId_servico(Integer id_servico) {
		this.id_servico = id_servico;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Double getValorservico() {
		return valorservico;
	}

	public void setValorservico(Double valorservico) {
		this.valorservico = valorservico;
	}

	public Double getValorminimo() {
		return valorminimo;
	}

	public void setValorminimo(Double valorminimo) {
		this.valorminimo = valorminimo;
	}

	public Double getValormaximo() {
		return valormaximo;
	}

	public void setValormaximo(Double valormaximo) {
		this.valormaximo = valormaximo;
	}

	public Integer getId_tiposervico() {
		return id_tiposervico;
	}

	public void setId_tiposervico(Integer id_tiposervico) {
		this.id_tiposervico = id_tiposervico;
	}

	public Integer getId_funcionario() {
		return id_funcionario;
	}

	public void setId_funcionario(Integer id_funcionario) {
		this.id_funcionario = id_funcionario;
	}	
	

	
	
	
	
	
}
