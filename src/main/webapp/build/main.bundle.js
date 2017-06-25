"use strict";

var xhttp = new XMLHttpRequest();
var path_principal = "";
var id_sendo_alterado = "";

function acessa_modulo(caminho) {
	id_sendo_alterado = "";

	path_principal = caminho;

	carrega_tabela_principal();

	xhttp.onreadystatechange = function () {
		document.getElementById("conteudo_menu").innerHTML = this.responseText;
	};
	xhttp.open("GET", "./conteudo/" + caminho + "/index.html", true);
	xhttp.send();

	$(".menu_superior").removeClass("active");
	document.getElementById(caminho).setAttribute("class", "active");
}

function acessa_sub_modulo(caminho) {
	xhttp.onreadystatechange = function () {
		document.getElementById("conteudo_submenu").innerHTML = this.responseText;
	};
	xhttp.open("GET", "./conteudo/" + path_principal + "/" + caminho + ".html", true);
	xhttp.send();
}

function carrega_tabela_principal() {
	xhttp.onreadystatechange = function () {
		if (this.readyState == 4 && this.status == 200) {
			//console.log(this.responseText);
			var aux = this.responseText.replace('[{', '');
			aux = aux.replace('}]', '');

			if (aux.indexOf('},{') != -1) {
				aux = aux.replace('},{', ',');
			}

			var indice = 0;
			var json_to_array = new Array();

			elementos = aux.split('","');

			elementos.forEach(function (e) {
				aux = e.replace('"', '');
				aux = aux.split(':');
				json_to_array[indice] = aux[1].replace('"', '');
				indice++;
				console.log(json_to_array);
			});
		}
	};
	xhttp.open("GET", "/sistema_financeiro_2017_A/" + path_principal + "?id=all", false);
	xhttp.send();
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
			acessa_sub_modulo('update');
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
