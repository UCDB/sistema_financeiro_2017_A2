package model;

public class Funcionario {
	private Integer id;
	private String nome,endereco,cpf,rg,telefone,cep,email,infoAdc,contato;
	
	public Funcionario(String nome, String endereco, String cpf, String rg, String telefone, String cep, String email, String infoAdc, String contato ){
		this.nome 		= nome;
		this.endereco 	= endereco;
		this.cpf 		= cpf;
		this.rg 		= rg;
		this.telefone 	= telefone;
		this.cep 		= cep;
		this.email 		= email;
		this.infoAdc 	= infoAdc;
		this.contato 	= contato;
	}
	public Integer getId() {return id;}
	
	public void setId(Integer id) {this.id = id;}

	public String getNome() {return nome;}
	
	public void setNome(String nome) {this.nome = nome;}
	
	public String getEndereco() {return endereco;}
	
	public void setEndereco(String endereco) {this.endereco = endereco;}
	
	public String getCpf() {return cpf;}
	
	public void setCpf(String cpf) {this.cpf = cpf;}
	
	public String getRg() {	return rg;}
	
	public void setRg(String rg) {this.rg = rg;}
	
	public String getTelefone() {return telefone;}
	
	public void setTelefone(String telefone) {this.telefone = telefone;}
	
	public String getCep() {return cep;}
	
	public void setCep(String cep) {this.cep = cep;}
	
	public String getEmail() {return email;}
	
	public void setEmail(String email) {this.email = email;}
	
	public String getInfoAdc() {return infoAdc;}
	
	public void setInfoAdc(String infoAdc) {this.infoAdc = infoAdc;}
	
	public String getContato() {return contato;}
	
	public void setContato(String contato) {this.contato = contato;}
}