package model;

public class Fornecedor {
	private	Integer id;
	private String nome_razao, endereco, telefone, email, cpf_cnpj, rg_ie, cep, contato, info_add;

	public Fornecedor(String nome_razao, String endereco, String telefone, String email, String cpf_cnpj, String rg_ie, String cep, String contato, String info_add) {
		this.nome_razao	= nome_razao;
		this.endereco 	= endereco;
		this.telefone 	= telefone;
		this.email 		= email;
		this.cpf_cnpj 	= cpf_cnpj;
		this.rg_ie 		= rg_ie;
		this.cep 		= cep;
		this.contato 	= contato;
		this.info_add 	= info_add;
	}	

	//Getters

	public Integer getId() 						{return id;}
	public String getNomeRazao() 				{return nome_razao;}
	public String getEndereco() 				{return endereco;}
	public String getTelefone() 				{return telefone;}
	public String getEmail() 					{return email;}
	public String getCpfCnpj() 					{return cpf_cnpj;}
	public String getRgIe() 					{return rg_ie;	}
	public String getCep() 						{return cep;}
	public String getContato() 					{return contato;}
	public String getInfoAdd() 					{return info_add;}
	
	//Setters

	public void setId(Integer id) 				{this.id = id;}
	public void setNomeRazao(String nome_razao) {this.nome_razao = nome_razao;}
	public void setEndereco(String endereco) 	{this.endereco = endereco;}
	public void setTelefone(String telefone) 	{this.telefone = telefone;}
	public void setEmail(String email) 			{this.email = email;}
	public void setCpfCnpj(String cpf_cnpj) 	{this.cpf_cnpj = cpf_cnpj;}
	public void setRgIe(String rg_ie) 			{this.rg_ie = rg_ie;}
	public void setCep(String cep) 				{this.cep = cep;}
	public void setContato(String contato) 		{this.contato = contato;}
	public void setInfoAdd(String info_add) 	{this.info_add = info_add;}
}