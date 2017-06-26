package model;

public class Fornecedor {
	private	Integer id;
	private String nome;
	private String endereco;
	private String cpf_cnpj;
	private String rg_ie;
	private String telefone;
	private String cep;
	private String contato;
	private String info;
	private String email;
	
	//Construtor

	public Fornecedor() {
		super();
	}
	public Fornecedor(String nome, String endereco, String cpf_cnpj, String rg_ie, String telefone, String cep,
			String contato, String info, String email) {
		super();
		this.nome = nome;
		this.endereco = endereco;
		this.cpf_cnpj = cpf_cnpj;
		this.rg_ie = rg_ie;
		this.telefone = telefone;
		this.cep = cep;
		this.contato = contato;
		this.info = info;
		this.email = email;
	}	
	
	//Getters

	public Integer getId() {return id;}
	public String getNome() {		return nome;	}
	public String getEndereco() {		return endereco;	}
	public String getCPF_CNPJ() {		return cpf_cnpj;	}
	public String getRG_IE() {		return rg_ie;	}
	public String getTelefone() {		return telefone;	}
	public String getCEP() {		return cep;	}
	public String getContato() {		return contato;	}
	public String getInfo() {		return info;	}
	public String getEmail() {		return email;	}
	
	//Setters

	public void setId(Integer id) {		this.id = id;	}
	public void setNome(String nome) {		this.nome = nome;	}
	public void setEndereco(String endereco) {		this.endereco = endereco;	}
	public void setCPF_CNPJ(String cPF_CNPJ) {		cpf_cnpj = cPF_CNPJ;	}
	public void setRG_IE(String rG_IE) {		rg_ie = rG_IE;	}
	public void setTelefone(String telefone) {		this.telefone = telefone;	}
	public void setCEP(String cep) {		this.cep = cep;	}
	public void setContato(String contato) {		this.contato = contato;	}
	public void setInfo(String info) {		this.info = info;	}
	public void setEmail(String email) {		this.email = email;	}
	
	
	

}
