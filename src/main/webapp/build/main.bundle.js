"use strict";

var xhttp = new XMLHttpRequest();
var path_principal = "";
var id_sendo_alterado = "";

function acessa_modulo(caminho) {
	id_sendo_alterado = "";

	xhttp.onreadystatechange = function () {
		document.getElementById("conteudo_menu").innerHTML = this.responseText;
	};
	xhttp.open("GET", "./conteudo/" + caminho + "/index.html", true);
	xhttp.send();

	$(".menu_superior").removeClass("active");
	document.getElementById(caminho).setAttribute("class", "active");

	path_principal = caminho;
	carregar_tabela_principal();
}

function acessa_sub_modulo(caminho) {
	xhttp.onreadystatechange = function () {
		document.getElementById("conteudo_submenu").innerHTML = this.responseText;
	};
	xhttp.open("GET", "./conteudo/" + path_principal + "/" + caminho + ".html", true);
	xhttp.send();
}

function carregar_tabela_principal() {
	/*
 xhttp.onreadystatechange = function() {
        if (this.readyState == 4 && this.status == 200) {
        	console.log(this.responseText);
        	// manipular this.responseText que possui o jSon com os resultados
        }
    };	
     xhttp.open("GET", "/sistema_financeiro_2017_A/"+path_principal+"?id=all", true);
 xhttp.send(); 
 */
}

var objeto = new function () {
	this.create = function () {
		var nome_razao = document.getElementById("nome_razao").value;
		var endereco = document.getElementById("endereco").value;
		var telefone = document.getElementById("telefone").value;
		var email = document.getElementById("email").value;
		var cpf_cnpj = document.getElementById("cpf_cnpj").value;
		var rg_ie = document.getElementById("rg_ie").value;
		var cep = document.getElementById("cep").value;
		var contato = document.getElementById("contato").value;
		var info_add = document.getElementById("info_add").value;

		if (nome_razao != '' && endereco != '' && telefone != '' && email != '' && cpf_cnpj != '' && rg_ie != '' && cep != '' && contato != '' && info_add != '') {
			xhttp.open("POST", "/sistema_financeiro_2017_A/" + path_principal, true);
			xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
			xhttp.send("nome=" + nome_razao + "&endereco=" + endereco + "&telefone=" + telefone + "&email=" + email + "&cpf=" + cpf_cnpj + "&rg=" + rg_ie + "&cep=" + cep + "&contato=" + contato + "&info_add=" + info_add);

			alert(path_principal + " cadastrado com sucesso!"); // retorno ao usuário
			acessa_modulo(path_principal); // volta pra index principal do modulo ativo
		} else {
			alert("Para prosseguir é obrigatório preencher todos os campos!");
		}
	};

	this.update = function (identificador) {
		if (id_sendo_alterado != "") {
			// PRONTO
			var nome_razao = document.getElementById("nome_razao").value;
			var endereco = document.getElementById("endereco").value;
			var telefone = document.getElementById("telefone").value;
			var email = document.getElementById("email").value;
			var cpf_cnpj = document.getElementById("cpf_cnpj").value;
			var rg_ie = document.getElementById("rg_ie").value;
			var cep = document.getElementById("cep").value;
			var contato = document.getElementById("contato").value;
			var info_add = document.getElementById("info_add").value;

			xhttp.open("PUT", "/sistema_financeiro_2017_A/" + path_principal, true);
			xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
			xhttp.send("id=" + id_sendo_alterado + "nome=" + nome_razao + "&endereco=" + endereco + "&telefone=" + telefone + "&email=" + email + "&cpf=" + cpf_cnpj + "&rg=" + rg_ie + "&cep=" + cep + "&contato=" + contato + "&info_add=" + info_add);

			id_sendo_alterado = "";

			acessa_modulo(path_principal);
		} else {
			// FAZENDO
			id_sendo_alterado = identificador;

			var retorno = "";

			xhttp.onreadystatechange = function () {
				if (this.readyState == 4 && this.status == 200) {
					retorno = this.responseText;console.log(this.responseText);
				}
			};

			xhttp.open("GET", "/sistema_financeiro_2017_A/" + path_principal + "?id=" + identificador, true);
			xhttp.send();

			acessa_sub_modulo('update');

			console.log("Retorno: " + retorno);
		}
	};

	this.remove = function (identificador) {
		if (confirm("Deseja realmente remover esse " + path_principal)) {
			xhttp.open("DELETE", "/sistema_financeiro_2017_A/" + path_principal + "?id=" + identificador, true);
			xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
			xhttp.send("id=" + identificador);
			acessa_modulo(path_principal); // mudar por remoção de linha somente - remover tr tabela
		}
	};
}();

var objetoServ = new function () {
	this.create = function () {
		var descricao = document.getElementById("descricao").value;
		var valorservico = document.getElementById("valorservico").value;
		var valorminimo = document.getElementById("valorminimo").value;
		var valormaximo = document.getElementById("valormaximo").value;
		var id_tiposervico = document.getElementById("id_tiposervico").value;
		var id_funcionario = document.getElementById("id_funcionario").value;

		if (descricao != '' && valorservico != '' && valorminimo != '' && valormaximo != '' && id_tiposervico != '' && id_funcionario != '') {

			xhttp.open("POST", "/sistema_financeiro_2017_A/" + path_principal, true);

			xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");

			xhttp.send("descricao=" + descricao + "&valorservico=" + valorservico + "&valorminimo=" + valorminimo + "&valormaximo=" + valormaximo + "&id_tiposervico=" + id_tiposervico + "&id_funcionario=" + id_funcionario);

			alert(path_principal + " cadastrado com sucesso!"); // retorno ao usuário
			acessa_modulo(path_principal); // volta pra index principal do modulo ativo
		} else {
			alert("Para prosseguir é obrigatório preencher todos os campos!");
		}
	};

	this.update = function (identificador) {
		if (id_sendo_alterado != "") {
			// PRONTO
			var descricao = document.getElementById("descricao").value;
			var valorservico = document.getElementById("valorservico").value;
			var valorminimo = document.getElementById("valorminimo").value;
			var valormaximo = document.getElementById("valormaximo").value;
			var id_tiposervico = document.getElementById("id_tiposervico").value;
			var id_funcionario = document.getElementById("id_funcionario").value;

			xhttp.open("PUT", "/sistema_financeiro_2017_A/" + path_principal, true);
			xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
			xhttp.send("descricao=" + descricao + "&valorservico=" + valorservico + "&valorminimo=" + valorminimo + "&valormaximo=" + valormaximo + "&id_tiposervico=" + id_tiposervico + "&id_funcionario=" + id_funcionario);
			//xhttp.send("id="+id_sendo_alterado+"nome="+nome_razao+"&endereco="+endereco+"&telefone="+telefone+"&email="+email+"&cpf="+cpf_cnpj+"&rg="+rg_ie+"&cep="+cep+"&contato="+contato+"&info_add="+info_add);

			id_sendo_alterado = "";

			acessa_modulo(path_principal);
		} else {
			// FAZENDO
			id_sendo_alterado = identificador;

			var retorno = "";

			xhttp.onreadystatechange = function () {
				if (this.readyState == 4 && this.status == 200) {
					retorno = this.responseText;console.log(this.responseText);
				}
			};

			xhttp.open("GET", "/sistema_financeiro_2017_A/" + path_principal + "?id=" + identificador, true);
			xhttp.send();

			acessa_sub_modulo('update');

			console.log("Retorno: " + retorno);
		}
	};

	this.remove = function (identificador) {
		if (confirm("Deseja realmente remover esse " + path_principal)) {
			xhttp.open("DELETE", "/sistema_financeiro_2017_A/" + path_principal + "?id=" + identificador, true);
			xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
			xhttp.send("id=" + identificador);
			acessa_modulo(path_principal); // mudar por remoção de linha somente - remover tr tabela
		}
	};
}();

var objetoCaixa = new function () {
	this.create = function () {
		var data = document.getElementById("data").value;
		var descricao = document.getElementById("descricao_caixa").value;
		var valor = document.getElementById("valor_caixa").value;
		var status = document.getElementById("status").value;
		var formapagamento = document.getElementById("formapagamento").value;
		var id_tipodespesa = document.getElementById("id_tipodespesa").value;
		var id_cliente = document.getElementById("id_cliente_caixa").value;
		var id_fornecedor = document.getElementById("id_fornecedor").value;

		if (data != '' && descricao != '' && valor != '' && status != '' && formapagamento != '' && id_tipodespesa != '' && id_cliente != '' && id_fornecedor != '') {
			xhttp.open("POST", "/sistema_financeiro_2017_A/" + path_principal, true);
			xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
			xhttp.send("data=" + data + "&descricao=" + descricao + "&valor=" + valor + "&status=" + status + "&formapagamento=" + formapagamento + "&id_tipodespesa=" + id_tipodespesa + "&id_cliente=" + id_cliente + "&id_fornecedor=" + id_fornecedor);

			alert(path_principal + " cadastrado com sucesso!"); // retorno ao usuário
			acessa_modulo(path_principal); // volta pra index principal do modulo ativo
		} else {
			alert("Para prosseguir é obrigatório preencher todos os campos!");
		}
	};

	this.update = function (identificador) {
		if (id_sendo_alterado != "") {
			// PRONTO
			var data = document.getElementById("data").value;
			var descricao = document.getElementById("descricao_caixa").value;
			var valor = document.getElementById("valor_caixa").value;
			var status = document.getElementById("status").value;
			var formapagamento = document.getElementById("formapagamento").value;
			var id_tipodespesa = document.getElementById("id_tipodespesa").value;
			var id_cliente = document.getElementById("id_cliente_caixa").value;
			var id_fornecedor = document.getElementById("id_fornecedor").value;

			xhttp.open("PUT", "/sistema_financeiro_2017_A/" + path_principal, true);
			xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
			xhttp.send("data=" + data + "&descricao=" + descricao + "&valor=" + valor + "&status=" + status + "&formapagamento=" + formapagamento + "&id_tipodespesa=" + id_tipodespesa + "&id_cliente=" + id_cliente + "&id_fornecedor=" + id_fornecedor);
			//	xhttp.send("id="+id_sendo_alterado+"nome="+nome_razao+"&endereco="+endereco+"&telefone="+telefone+"&email="+email+"&cpf="+cpf_cnpj+"&rg="+rg_ie+"&cep="+cep+"&contato="+contato+"&info_add="+info_add);

			id_sendo_alterado = "";

			acessa_modulo(path_principal);
		} else {
			// FAZENDO
			id_sendo_alterado = identificador;

			var retorno = "";

			xhttp.onreadystatechange = function () {
				if (this.readyState == 4 && this.status == 200) {
					retorno = this.responseText;console.log(this.responseText);
				}
			};

			xhttp.open("GET", "/sistema_financeiro_2017_A/" + path_principal + "?id=" + identificador, true);
			xhttp.send();

			acessa_sub_modulo('update');

			console.log("Retorno: " + retorno);
		}
	};

	this.remove = function (identificador) {
		if (confirm("Deseja realmente remover esse " + path_principal)) {
			xhttp.open("DELETE", "/sistema_financeiro_2017_A/" + path_principal + "?id=" + identificador, true);
			xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
			xhttp.send("id=" + identificador);
			acessa_modulo(path_principal); // mudar por remoção de linha somente - remover tr tabela
		}
	};
}();
