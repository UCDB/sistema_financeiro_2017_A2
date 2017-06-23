package model;

public class Produto {
	private Integer id_produto;
	private String descricao;
	private String codbarras;
	private Integer id_fornecedor;
	private Double precocusto;
	private Double precovenda;
	private Double precominvenda;
	private Double precomaxvenda;
	private Double comissaovenda;
	private Double qtdestoque;
	private Double qtdminestoque;
	private Double altura;
	private Double peso;
	private Double largura;
	private Double profundidade;
	private Integer id_medidaproduto;
	private Integer id_tipoproduto;
	private Integer id_funcionario;
	private String validade;
	
	public Produto(){}

	public Produto(String descricao, String codbarras, Integer id_fornecedor, Double precocusto,
			Double precovenda, Double precominvenda, Double precomaxvenda, Double comissaovenda, Double qtdestoque,
			Double qtdminestoque, Double altura, Double peso, Double largura, Double profundidade,
			Integer id_medidaproduto, Integer id_tipoproduto, Integer id_funcionario, String validade) {				
		this.descricao = descricao;
		this.codbarras = codbarras;
		this.id_fornecedor = id_fornecedor;
		this.precocusto = precocusto;
		this.precovenda = precovenda;
		this.precominvenda = precominvenda;
		this.precomaxvenda = precomaxvenda;
		this.comissaovenda = comissaovenda;
		this.qtdestoque = qtdestoque;
		this.qtdminestoque = qtdminestoque;
		this.altura = altura;
		this.peso = peso;
		this.largura = largura;
		this.profundidade = profundidade;
		this.id_medidaproduto = id_medidaproduto;
		this.id_tipoproduto = id_tipoproduto;
		this.id_funcionario = id_funcionario;
		this.validade = validade;
	}

	public Integer getId_produto() {
		return id_produto;
	}

	public void setId_produto(Integer id_produto) {
		this.id_produto = id_produto;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getCodbarras() {
		return codbarras;
	}

	public void setCodbarras(String codbarras) {
		this.codbarras = codbarras;
	}

	public Integer getId_fornecedor() {
		return id_fornecedor;
	}

	public void setId_fornecedor(Integer id_fornecedor) {
		this.id_fornecedor = id_fornecedor;
	}

	public Double getPrecocusto() {
		return precocusto;
	}

	public void setPrecocusto(Double precocusto) {
		this.precocusto = precocusto;
	}

	public Double getPrecovenda() {
		return precovenda;
	}

	public void setPrecovenda(Double precovenda) {
		this.precovenda = precovenda;
	}

	public Double getPrecominvenda() {
		return precominvenda;
	}

	public void setPrecominvenda(Double precominvenda) {
		this.precominvenda = precominvenda;
	}

	public Double getPrecomaxvenda() {
		return precomaxvenda;
	}

	public void setPrecomaxvenda(Double precomaxvenda) {
		this.precomaxvenda = precomaxvenda;
	}

	public Double getComissaovenda() {
		return comissaovenda;
	}

	public void setComissaovenda(Double comissaovenda) {
		this.comissaovenda = comissaovenda;
	}

	public Double getQtdestoque() {
		return qtdestoque;
	}

	public void setQtdestoque(Double qtdestoque) {
		this.qtdestoque = qtdestoque;
	}

	public Double getQtdminestoque() {
		return qtdminestoque;
	}

	public void setQtdminestoque(Double qtdminestoque) {
		this.qtdminestoque = qtdminestoque;
	}

	public Double getAltura() {
		return altura;
	}

	public void setAltura(Double altura) {
		this.altura = altura;
	}

	public Double getPeso() {
		return peso;
	}

	public void setPeso(Double peso) {
		this.peso = peso;
	}

	public Double getLargura() {
		return largura;
	}

	public void setLargura(Double largura) {
		this.largura = largura;
	}

	public Double getProfundidade() {
		return profundidade;
	}

	public void setProfundidade(Double profundidade) {
		this.profundidade = profundidade;
	}

	public Integer getId_medidaproduto() {
		return id_medidaproduto;
	}

	public void setId_medidaproduto(Integer id_medidaproduto) {
		this.id_medidaproduto = id_medidaproduto;
	}

	public Integer getId_tipoproduto() {
		return id_tipoproduto;
	}

	public void setId_tipoproduto(Integer id_tipoproduto) {
		this.id_tipoproduto = id_tipoproduto;
	}

	public Integer getId_funcionario() {
		return id_funcionario;
	}

	public void setId_funcionario(Integer id_funcionario) {
		this.id_funcionario = id_funcionario;
	}

	public String getValidade() {
		return validade;
	}

	public void setValidade(String validade) {
		this.validade = validade;
	}
	
	

	
	
	

	

	
	
	
}
