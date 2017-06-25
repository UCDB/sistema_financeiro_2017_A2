let xhttp = new XMLHttpRequest();
var path_principal = "";
var id_sendo_alterado = "";

function acessa_modulo(caminho){
	id_sendo_alterado = "";

	path_principal = caminho;

	xhttp.onreadystatechange = function() { document.getElementById("conteudo_menu").innerHTML = this.responseText; };
    xhttp.open("GET", "./conteudo/"+caminho+"/index.html", false);
    xhttp.send();
	
	carrega_tabela_principal();

    $(".menu_superior").removeClass("active");
	document.getElementById(caminho).setAttribute("class", "active");	
}

function acessa_sub_modulo(caminho){
	xhttp.onreadystatechange = function() { document.getElementById("conteudo_submenu").innerHTML = this.responseText; };
    xhttp.open("GET", "./conteudo/"+path_principal+"/"+caminho+".html", false);
    xhttp.send();

    xhttp.onreadystatechange = function() {
        if (this.readyState == 4 && this.status == 200) {
    		elementos = this.responseText.split(',');
			elementos.forEach(function(e){
				var campo_valor = e.split(':');
				if( campo_valor[0] === "id" ){
					// não preciso fazer nada pois ja tenho o ID
				}else if( campo_valor[0] === "nomerazao" ){
					document.getElementById("nome_razao").value = campo_valor[1];
					console.log("Nome Razao: "+campo_valor[1]);
				}else if( campo_valor[0] === "endereco" ){
					document.getElementById("endereco").value = campo_valor[1];
				}else if( campo_valor[0] === "telefone" ){
					document.getElementById("telefone").value = campo_valor[1];
				}else if( campo_valor[0] === "email" ){
					document.getElementById("email").value = campo_valor[1];
				}else if( campo_valor[0] === "cpfcnpj" ){
					document.getElementById("cpf_cnpj").value = campo_valor[1];
				}else if( campo_valor[0] === "rgie" ){
					document.getElementById("rg_ie").value = campo_valor[1];
				}else if( campo_valor[0] === "cep" ){
					document.getElementById("cep").value = campo_valor[1];
				}else if( campo_valor[0] === "contato" ){
					document.getElementById("contato").value = campo_valor[1];
				}else if( campo_valor[0] === "infoadd" ){
					document.getElementById("info_add").value = campo_valor[1];
				}
				document.getElementById("btn_alterar").setAttribute('onclick', 'objeto.update('+id_sendo_alterado+')');
			});
        }
    };
    xhttp.open("GET", "/sistema_financeiro_2017_A/"+path_principal+"?id="+id_sendo_alterado, true);
	xhttp.send();
}

function carrega_tabela_principal(){	
	xhttp.onreadystatechange = function() {
        if (this.readyState == 4 && this.status == 200) {
        	var indice = 0;
        	var aux = this.responseText.replace('[{','');
				aux = aux.replace('}]','');

			if( aux.indexOf('},{') == -1 ){ // string de jSon
				// fazer ainda
			}else{ // lista de jSon
				var conteudo_tabela = "";
				let elementos_internos = "";
				elementos = aux.split('},{');
				elementos.forEach(function(e){
					console.log("Elemento: "+e);
					let id_da_vez = "";
					elementos_internos = e.split(',');
					elementos_internos.forEach(function(ei){
						var campo_valor = ei.split(':');
						if( campo_valor[0] === "id" ){
							conteudo_tabela += '<tr id="'+campo_valor[1]+'">';
							id_da_vez = campo_valor[1];
						}else if( campo_valor[0] === "nomerazao" ){
							conteudo_tabela += '<td>'+campo_valor[1]+'</td>';
						}else if( campo_valor[0] === "email" ){
							conteudo_tabela += '<td>'+campo_valor[1]+'</td>';
						}else if( campo_valor[0] === "telefone" ){
							conteudo_tabela += '<td align="center">'+campo_valor[1]+'</td>';
						}
					}); // fim foreach elementos internos
					conteudo_tabela += '<td align="center"> <img title="Alterar Cliente" src="media/icons/update.png" width="30" height="30" onclick="objeto.update('+id_da_vez+')"> <img title="Excluir Cliente" src="media/icons/trash.png" width="30" height="30" onclick="objeto.remove('+id_da_vez+')"> </td>';
					conteudo_tabela += '</tr>';
					indice++;
				}); // fim foreach elementos
				document.getElementById("corpo_tabela_index").innerHTML = conteudo_tabela;
			} // fim else
        }
    };
    xhttp.open("GET", "/sistema_financeiro_2017_A/"+path_principal+"?id=all", true);
	xhttp.send();
}

var objeto = new function(){
	this.create = function(){
		var nome_razao	= document.getElementById("nome_razao").value;
		var endereco 	= document.getElementById("endereco").value;
		var telefone 	= document.getElementById("telefone").value;
		var email 		= document.getElementById("email").value;
		var cpf_cnpj	= document.getElementById("cpf_cnpj").value;
		var rg_ie 		= document.getElementById("rg_ie").value;
		var cep 		= document.getElementById("cep").value;
		var contato 	= document.getElementById("contato").value;
		var info_add 	= document.getElementById("info_add").value;

		if( nome_razao != '' && endereco != '' && telefone != '' && email != '' && cpf_cnpj != '' && rg_ie != '' && cep != '' && contato != '' && info_add != '' ){
			xhttp.open("POST", "/sistema_financeiro_2017_A/"+path_principal, true);
			xhttp.setRequestHeader("Content-type","application/x-www-form-urlencoded");
			xhttp.send("nome_razao="+nome_razao+"&endereco="+endereco+"&telefone="+telefone+"&email="+email+"&cpf_cnpj="+cpf_cnpj+"&rg_ie="+rg_ie+"&cep="+cep+"&contato="+contato+"&info_add="+info_add);

			alert(path_principal+" cadastrado com sucesso!"); // retorno ao usuário
			acessa_modulo(path_principal); // volta pra index principal do modulo ativo
		}else{
			alert("Para prosseguir é obrigatório preencher todos os campos!");
		}
	}

	this.update = function(identificador){
		if( id_sendo_alterado != "" ){
			var nome_razao	= document.getElementById("nome_razao").value;
			var endereco 	= document.getElementById("endereco").value;
			var telefone 	= document.getElementById("telefone").value;
			var email 		= document.getElementById("email").value;
			var cpf_cnpj	= document.getElementById("cpf_cnpj").value;
			var rg_ie 		= document.getElementById("rg_ie").value;
			var cep 		= document.getElementById("cep").value;
			var contato 	= document.getElementById("contato").value;
			var info_add 	= document.getElementById("info_add").value;

			if( nome_razao != '' && endereco != '' && telefone != '' && email != '' && cpf_cnpj != '' && rg_ie != '' && cep != '' && contato != '' && info_add != '' ){
				xhttp.open("PUT", "/sistema_financeiro_2017_A/"+path_principal+"?id="+id_sendo_alterado+"&nome_razao="+nome_razao+"&endereco="+endereco+"&telefone="+telefone+"&email="+email+"&cpf_cnpj="+cpf_cnpj+"&rg_ie="+rg_ie+"&cep="+cep+"&contato="+contato+"&info_add="+info_add, true);
				xhttp.setRequestHeader("Content-type","application/x-www-form-urlencoded");
				xhttp.send();

				id_sendo_alterado = "";
				acessa_modulo(path_principal);
			}else{
				alert("Para prosseguir é obrigatório preencher todos os campos!");
			}
		}else{
			id_sendo_alterado = identificador;
			acessa_sub_modulo('update');
		}
	}

	this.remove = function(identificador){
		if(confirm("Deseja realmente remover esse "+path_principal)){
			xhttp.open("DELETE", "/sistema_financeiro_2017_A/"+path_principal+"?id="+identificador, true);
			xhttp.setRequestHeader("Content-type","application/x-www-form-urlencoded");
			xhttp.send()
			acessa_modulo(path_principal); // mudar por remoção de linha somente - remover tr tabela
		}
	}
}