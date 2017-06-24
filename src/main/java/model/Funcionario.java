package model;

public class Funcionario {
	private Integer id;
	private String nome, endereco, telefone, email, cpf, rg, cep, contato, info_add;

	public Funcionario(String nome, String endereco, String telefone, String email, String cpf, String rg, String cep, String contato, String info_add ){
		this.nome 		= nome;
		this.endereco 	= endereco;
		this.telefone 	= telefone;
		this.email 		= email;
		this.cpf 		= cpf;
		this.rg 		= rg;
		this.cep 		= cep;
		this.contato 	= contato;
		this.info_add 	= info_add;
	}

	/* Getters */
	
	public Integer getId() 						{return id;}
	public String getNome() 					{return nome;}
	public String getEndereco() 				{return endereco;}
	public String getTelefone() 				{return telefone;}
	public String getEmail() 					{return email;}
	public String getCpf() 						{return cpf;}
	public String getRg() 						{return rg;}
	public String getCep() 						{return cep;}
	public String getContato() 					{return contato;}
	public String getInfoAdd() 					{return info_add;}

	/* Setters */
	
	public void setId(Integer id) 				{this.id = id;}
	public void setNome(String nome) 			{this.nome = nome;}
	public void setEndereco(String endereco) 	{this.endereco = endereco;}
	public void setTelefone(String telefone) 	{this.telefone = telefone;}
	public void setEmail(String email) 			{this.email = email;}
	public void setCpf(String cpf) 				{this.cpf = cpf;}
	public void setRg(String rg) 				{this.rg = rg;}
	public void setCep(String cep) 				{this.cep = cep;}
	public void setContato(String contato) 		{this.contato = contato;}
	public void setInfoAdd(String info_add) 	{this.info_add = info_add;}
}