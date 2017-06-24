package model;

public class Fornecedor {
	private	Integer id;
	private String razao_social, endereco, telefone, email, cnpj, ie, cep, contato, info_add;

	public Fornecedor(String razao_social, String endereco, String telefone, String email, String cnpj, String ie, String cep, String contato, String info_add) {
		this.razao_social	= razao_social;
		this.endereco 		= endereco;
		this.telefone 		= telefone;
		this.email 			= email;
		this.cnpj 			= cnpj;
		this.ie 			= ie;
		this.cep 			= cep;
		this.contato 		= contato;
		this.info_add 		= info_add;
	}	

	//Getters

	public Integer getId() 							{return id;}
	public String getRazaoSocial() 					{return razao_social;}
	public String getEndereco() 					{return endereco;}
	public String getTelefone() 					{return telefone;}
	public String getEmail() 						{return email;}
	public String getCnpj() 						{return cnpj;}
	public String getIe() 							{return ie;	}
	public String getCep() 							{return cep;}
	public String getContato() 						{return contato;}
	public String getInfoAdd() 					{return info_add;}
	
	//Setters

	public void setId(Integer id) 					{this.id = id;}
	public void setRazaoSocial(String razao_social) {this.razao_social = razao_social;}
	public void setEndereco(String endereco) 		{this.endereco = endereco;}
	public void setTelefone(String telefone) 		{this.telefone = telefone;}
	public void setEmail(String email) 				{this.email = email;}
	public void setCnpj(String cnpj) 				{this.cnpj = cnpj;}
	public void setIe(String ie) 					{this.ie = ie;}
	public void setCep(String cep) 					{this.cep = cep;}
	public void setContato(String contato) 			{this.contato = contato;}
	public void setInfoAdd(String info_add) 		{this.info_add = info_add;}
}