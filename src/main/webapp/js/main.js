var xhttp = new XMLHttpRequest();
var path_principal = "";
var id_sendo_alterado = "";

function acessa_modulo(caminho){
	// função preenche div conteudo_menu da index principal com dados do modulo acessado pelo usuário
	id_sendo_alterado = "";

	// atribuo a variavel abaixo o modulo acessado para possíveis acessos de sub módulos
	path_principal = caminho;

	xhttp.onreadystatechange = function() { document.getElementById("conteudo_menu").innerHTML = this.responseText; };
    xhttp.open("GET", "./conteudo/"+path_principal+"/index.html", false);
    xhttp.send();

	carrega_tabela_principal();

	// manipulo bg de menu ativo do topo, deixando demarcado somente ítem pertinente a modulo ativo
    $(".menu_superior").removeClass("active");
    $("#"+path_principal).addClass("active");
}

function acessa_sub_modulo(caminho){
	// função preenche div conteudo_submenu da index do modulo acessado pelo usuário com dados pertinentes ao read ou update
	xhttp.onreadystatechange = function() { document.getElementById("conteudo_submenu").innerHTML = this.responseText; };
    xhttp.open("GET", "./conteudo/"+path_principal+"/"+caminho+".html", false);
    xhttp.send();

    // abaixo carrego e preencho formulário de update com dados da tupla selecionada
    xhttp.onreadystatechange = function() {
        if (this.readyState == 4 && this.status == 200) {
    		elementos = this.responseText.split(','); // split quebra string no character das aspas transformando o mesmo em array!
			elementos.forEach(function(e){ // percorro array criada para preencher formulário de read ou update
				var campo_valor = e.split(':'); // quebro valor da posição atual do loop para poder comparar o campo e alocar valor no input correto.
				if( campo_valor[0] === "id" ){

					// não preciso fazer nada pois ja tenho o ID na variavel -> id_sendo_alterado

				}else if( campo_valor[0] === "nomerazao" ){
					
					document.getElementById("nome_razao").value = campo_valor[1];
					console.log("Nome Razao: "+campo_valor[1]);
				
				}else if( campo_valor[0] === "endereco" ){
					
					document.getElementById("endereco").value = campo_valor[1];
					console.log("Endereço: "+campo_valor[1]);
				
				}else if( campo_valor[0] === "telefone" ){
					
					document.getElementById("telefone").value = campo_valor[1];
					console.log("Telefone: "+campo_valor[1]);
				
				}else if( campo_valor[0] === "email" ){
					
					document.getElementById("email").value = campo_valor[1];
					console.log("E-mail: "+campo_valor[1]);
				
				}else if( campo_valor[0] === "cpfcnpj" ){
					
					document.getElementById("cpf_cnpj").value = campo_valor[1];
					console.log("CPF CNPJ: "+campo_valor[1]);
				
				}else if( campo_valor[0] === "rgie" ){
					
					document.getElementById("rg_ie").value = campo_valor[1];
					console.log("RG IE: "+campo_valor[1]);
				
				}else if( campo_valor[0] === "cep" ){
					
					document.getElementById("cep").value = campo_valor[1];
					console.log("CEP: "+campo_valor[1]);
				
				}else if( campo_valor[0] === "contato" ){
					
					document.getElementById("contato").value = campo_valor[1];
					console.log("Contato: "+campo_valor[1]);
				
				}else if( campo_valor[0] === "infoadd" ){
					
					document.getElementById("info_add").value = campo_valor[1];
					console.log("Info ADD: "+campo_valor[1]);
				
				}
				document.getElementById("btn_alterar").setAttribute('onclick', 'objeto.update('+id_sendo_alterado+')');
			});
        }
    };
    xhttp.open("GET", "/sistema_financeiro_2017_A/"+path_principal+"?id="+id_sendo_alterado, true);
	xhttp.send();
}

function carrega_tabela_principal(){	
	// função preenche div conteudo_submenu da index do modulo acessado pelo usuário com uma tabela contendo todos os registros do modulo ativo
	xhttp.onreadystatechange = function() {
        if (this.readyState == 4 && this.status == 200) {
        	var indice = 0;
        	var aux = this.responseText.replace('[{','');
				aux = aux.replace('}]','');

			var coluna_nome_razao = "";
			var coluna_email = "";
			var coluna_telefone = "";

			if( aux.indexOf('},{') == -1 ){ // string de jSon				
				// existe pelo menos um registro para ser visualizado
				var linha_tabela = "";
				
				elementos_internos = aux.split(',');

				var entrou = false;
				
				elementos_internos.forEach(function(ei){
					var campo_valor = ei.split(':');
					if( campo_valor[0] === "id" ){
						
						entrou = true;
						linha_tabela += '<tr id="linha_'+campo_valor[1]+'">';
						console.log("Criando TR Tabela com ID: "+campo_valor[1]);
						id_da_vez = campo_valor[1];
					
					}else if( campo_valor[0] === "nomerazao" ){
						
						entrou = true;
						coluna_nome_razao = '<td>'+campo_valor[1]+'</td>';
						console.log("Coluna Nome Razão: "+campo_valor[1]);
					
					}else if( campo_valor[0] === "email" ){
						
						entrou = true;
						coluna_email = '<td>'+campo_valor[1]+'</td>';
						console.log("Coluna E-mail: "+campo_valor[1]);
					
					}else if( campo_valor[0] === "telefone" ){
						
						entrou = true;
						coluna_telefone = '<td align="center">'+campo_valor[1]+'</td>';
						console.log("Coluna Telefone: "+campo_valor[1]);
					
					}
				}); // fim foreach elementos internos
				if( entrou ){ // passou pelo foreach
					//alert("if");
					linha_tabela += coluna_nome_razao+coluna_email+coluna_telefone;
					linha_tabela += '<td align="center"> <img title="Alterar '+path_principal+'" src="media/icons/update.png" width="30" height="30" onclick="objeto.update('+id_da_vez+')"> <img title="Excluir '+path_principal+'" src="media/icons/trash.png" width="30" height="30" onclick="objeto.remove('+id_da_vez+')"> </td>';
					linha_tabela += '</tr>';
				}else{ // nao entrou no foreach, linha_vazia
					//alert("else");
					// modulo linha_vazia, nenhum registro, mensagem de linha_vazia
					linha_tabela = '<tr ><td align="center" class="linha_vazia" colspan="4">Nenhum Registro Encontrado!</td></tr>';
				}
			}else{ // lista de jSon
				var linha_tabela 	= "";
				var elementos_internos 	= "";

				elementos = aux.split('},{');
				elementos.forEach(function(e){
					console.log("Elemento: "+e);

					id_da_vez = "";
					coluna_nome_razao = "";
					coluna_email = "";
					coluna_telefone = "";

					elementos_internos = e.split(',');

					elementos_internos.forEach(function(ei){
						var campo_valor = ei.split(':');
						if( campo_valor[0] === "id" ){
							
							linha_tabela += '<tr id="linha_'+campo_valor[1]+'">';
							console.log("Criando TR Tabela com ID: "+campo_valor[1]);
							id_da_vez = campo_valor[1];
						
						}else if( campo_valor[0] === "nomerazao" ){
							
							coluna_nome_razao = '<td>'+campo_valor[1]+'</td>';
							console.log("Coluna Nome Razão: "+campo_valor[1]);
						
						}else if( campo_valor[0] === "email" ){
							
							coluna_email = '<td>'+campo_valor[1]+'</td>';
							console.log("Coluna E-mail: "+campo_valor[1]);
						
						}else if( campo_valor[0] === "telefone" ){
							
							coluna_telefone = '<td align="center">'+campo_valor[1]+'</td>';
							console.log("Coluna Telefone: "+campo_valor[1]);
						
						}
					}); // fim foreach elementos internos
					linha_tabela += coluna_nome_razao+coluna_email+coluna_telefone;
					linha_tabela += '<td align="center"> <img title="Alterar '+path_principal+'" src="media/icons/update.png" width="30" height="30" onclick="objeto.update('+id_da_vez+')"> <img title="Excluir '+path_principal+'" src="media/icons/trash.png" width="30" height="30" onclick="objeto.remove('+id_da_vez+')"> </td>';
					linha_tabela += '</tr>';
					
					console.log("Finaliza criação de linha com id: "+id_da_vez);
					indice++;
				}); // fim foreach elementos	
			} // fim else
			document.getElementById("corpo_tabela_index").innerHTML = linha_tabela;
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
				//alert("Antes");
				xhttp.open("PUT", "/sistema_financeiro_2017_A/"+path_principal+"?id="+id_sendo_alterado+"&nome_razao="+nome_razao+"&endereco="+endereco+"&telefone="+telefone+"&email="+email+"&cpf_cnpj="+cpf_cnpj+"&rg_ie="+rg_ie+"&cep="+cep+"&contato="+contato+"&info_add="+info_add, true);
				xhttp.setRequestHeader("Content-type","application/x-www-form-urlencoded");
				xhttp.send();
				
				id_sendo_alterado = "";

				//alert("Depois");
				alert(path_principal+" alterado com sucesso!");

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
			xhttp.send();
			
			alert(path_principal+" removido com sucesso!");
			
			document.getElementById("linha_"+identificador).parentNode.removeChild(document.getElementById("linha_"+identificador));
		}
	}
}