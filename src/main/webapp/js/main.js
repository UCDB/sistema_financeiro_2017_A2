let xhttp = new XMLHttpRequest();
var path_principal = "";
var id_sendo_alterado = "";

function acessa_modulo(caminho){
	id_sendo_alterado = "";

	path_principal = caminho;

	xhttp.onreadystatechange = function() { document.getElementById("conteudo_menu").innerHTML = this.responseText; };
    xhttp.open("GET", "./conteudo/"+caminho+"/index.html", false);
    xhttp.send();
	if(path_principal == "cliente" ||path_principal == "fornecedor" || path_principal == "funcionario" ){
	carrega_tabela_principal();
	}else if(path_principal == "servico"){
		carrega_tabela_servico();
	}else if(path_principal == "caixa"){
		carrega_tabela_caixa();
    }else if (path_principal == "produto") {
        carrega_tabela_produto();
    }


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
// Ações referentes ao Serviço
function acessa_sub_modulo_serv(caminho){
	xhttp.onreadystatechange = function() { document.getElementById("conteudo_submenu").innerHTML = this.responseText; };
    xhttp.open("GET", "./conteudo/"+path_principal+"/"+caminho+".html", false);
    xhttp.send();

    xhttp.onreadystatechange = function() {
        if (this.readyState == 4 && this.status == 200) {
    		elementos = this.responseText.split(',');
			elementos.forEach(function(e){
				var campo_valor = e.split(':');
				if( campo_valor[0] === "id_servico" ){
					// não preciso fazer nada pois ja tenho o ID
				}else if( campo_valor[0] === "descricao" ){
					document.getElementById("descricao").value = campo_valor[1];				
				}else if( campo_valor[0] === "valorservico" ){
					document.getElementById("valorservico").value = campo_valor[1];
				}else if( campo_valor[0] === "valorminimo" ){
					document.getElementById("valorminimo").value = campo_valor[1];
				}else if( campo_valor[0] === "valormaximo" ){
					document.getElementById("valormaximo").value = campo_valor[1];
				}else if( campo_valor[0] === "id_tiposervico" ){
					document.getElementById("id_tiposervico").value = campo_valor[1];
				}else if( campo_valor[0] === "id_funcionario" ){
					document.getElementById("id_funcionario").value = campo_valor[1];				
				}
				document.getElementById("btn_alterar").setAttribute('onclick', 'objetoServ.update('+id_sendo_alterado+')');
			});
        }
    };
    xhttp.open("GET", "/sistema_financeiro_2017_A/"+path_principal+"?id="+id_sendo_alterado, true);
	xhttp.send();
}

function carrega_tabela_servico(){		
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
						if( campo_valor[0] === "id_servico" ){
							id_da_vez = campo_valor[1];
						}else if( campo_valor[0] === "id_tiposervico" ){
							conteudo_tabela += '<td>'+campo_valor[1]+'</td>';
						}else if( campo_valor[0] === "descricao" ){
							conteudo_tabela += '<td>'+campo_valor[1]+'</td>';
						}else if( campo_valor[0] === "valorservico" ){
							conteudo_tabela += '<td align="center">'+campo_valor[1]+'</td>';
						}
					}); // fim foreach elementos internos
					conteudo_tabela += '<td align="center"> <img title="Excluir Servico" src="media/icons/trash.png" width="30" height="30" onclick="objetoServ.remove('+id_da_vez+')"> </td>';
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

var objetoServ = new function(){
	this.create = function(){
		var descricao	= document.getElementById("descricao").value;
		var valorservico 	= document.getElementById("valorservico").value;
		var valorminimo 	= document.getElementById("valorminimo").value;
		var valormaximo 		= document.getElementById("valormaximo").value;
		var id_tiposervico	= document.getElementById("id_tiposervico").value;
		var id_funcionario 		= document.getElementById("id_funcionario").value;
		

		if( descricao != '' && valorservico != '' && valorminimo != '' && valormaximo != '' && id_tiposervico != '' && id_funcionario != '' ){
				

			xhttp.open("POST", "/sistema_financeiro_2017_A/"+path_principal, true);
			
			xhttp.setRequestHeader("Content-type","application/x-www-form-urlencoded");

			xhttp.send("descricao="+descricao+"&valorservico="+valorservico+"&valorminimo="+valorminimo+"&valormaximo="+valormaximo+"&id_tiposervico="+id_tiposervico+"&id_funcionario="+id_funcionario);

			alert(path_principal+" cadastrado com sucesso!"); // retorno ao usuário
			acessa_modulo(path_principal); // volta pra index principal do modulo ativo
		}else{
			alert("Para prosseguir é obrigatório preencher todos os campos!");
		}
	}

	this.update = function(identificador){
		if( id_sendo_alterado != "" ){ // PRONTO
			var descricao	= document.getElementById("descricao").value;
			var valorservico 	= document.getElementById("valorservico").value;
			var valorminimo 	= document.getElementById("valorminimo").value;
			var valormaximo 		= document.getElementById("valormaximo").value;
			var id_tiposervico	= document.getElementById("id_tiposervico").value;
			var id_funcionario 		= document.getElementById("id_funcionario").value;

			xhttp.open("PUT", "/sistema_financeiro_2017_A/"+path_principal, true);
			xhttp.setRequestHeader("Content-type","application/x-www-form-urlencoded");
			xhttp.send("descricao="+descricao+"&valorservico="+valorservico+"&valorminimo="+valorminimo+"&valormaximo="+valormaximo+"&id_tiposervico="+id_tiposervico+"&id_funcionario="+id_funcionario);
			//xhttp.send("id="+id_sendo_alterado+"nome="+nome_razao+"&endereco="+endereco+"&telefone="+telefone+"&email="+email+"&cpf="+cpf_cnpj+"&rg="+rg_ie+"&cep="+cep+"&contato="+contato+"&info_add="+info_add);

			id_sendo_alterado = "";

			acessa_modulo(path_principal);
		}else{ // FAZENDO
			id_sendo_alterado = identificador;
			
			var retorno = "";
			
            xhttp.onreadystatechange = function() {
                if (this.readyState == 4 && this.status == 200) {
                	retorno = this.responseText; console.log(this.responseText);
                }
            };	

            xhttp.open("GET", "/sistema_financeiro_2017_A/"+path_principal+"?id="+identificador, true);
			xhttp.send();

			acessa_sub_modulo('update');

			console.log("Retorno: "+retorno);
		}
	}

	this.remove = function(identificador){
		if(confirm("Deseja realmente remover esse "+path_principal)){
			xhttp.open("DELETE", "/sistema_financeiro_2017_A/"+path_principal+"?id="+identificador, true);
			xhttp.setRequestHeader("Content-type","application/x-www-form-urlencoded");
			xhttp.send("id="+identificador)
			acessa_modulo(path_principal); // mudar por remoção de linha somente - remover tr tabela
		}
	}
}
// Ações referentes ao Caixa
function acessa_sub_modulo_caixa(caminho){
	xhttp.onreadystatechange = function() { document.getElementById("conteudo_submenu").innerHTML = this.responseText; };
    xhttp.open("GET", "./conteudo/"+path_principal+"/"+caminho+".html", false);
    xhttp.send();

    xhttp.onreadystatechange = function() {
        if (this.readyState == 4 && this.status == 200) {
    		elementos = this.responseText.split(',');
			elementos.forEach(function(e){
				var campo_valor = e.split(':');
				if( campo_valor[0] === "id_caixa" ){
					// não preciso fazer nada pois ja tenho o ID
				}else if( campo_valor[0] === "data" ){
					document.getElementById("data").value = campo_valor[1];				
				}else if( campo_valor[0] === "descricao_caixa" ){
					document.getElementById("descricao_caixa").value = campo_valor[1];
				}else if( campo_valor[0] === "valor_caixa" ){
					document.getElementById("valor_caixa").value = campo_valor[1];
				}else if( campo_valor[0] === "status" ){
					document.getElementById("status").value = campo_valor[1];
				}else if( campo_valor[0] === "formapagamento" ){
					document.getElementById("formapagamento").value = campo_valor[1];
				}else if( campo_valor[0] === "id_tipodespesa" ){
					document.getElementById("id_tipodespesa").value = campo_valor[1];				
				}else if( campo_valor[0] === "id_cliente_caixa" ){
					document.getElementById("id_cliente_caixa").value = campo_valor[1];				
				}else if( campo_valor[0] === "id_fornecedor" ){
					document.getElementById("id_fornecedor").value = campo_valor[1];				
				}
				document.getElementById("btn_alterar").setAttribute('onclick', 'objetoCaixa.update('+id_sendo_alterado+')');
			});
        }
    };
    xhttp.open("GET", "/sistema_financeiro_2017_A/"+path_principal+"?id="+id_sendo_alterado, true);
	xhttp.send();
}

function carrega_tabela_caixa(){		
	xhttp.onreadystatechange = function () {
        if (this.readyState == 4 && this.status == 200) {
            var indice = 0;
            var aux = this.responseText.replace('[{', '');
            aux = aux.replace('}]', '');

            if (aux.indexOf('},{') == -1) { // string de jSon
                // fazer ainda
            } else { // lista de jSon
                var conteudo_tabela = "";
                let elementos_internos = "";
                elementos = aux.split('},{');
                elementos.forEach(function (e) {
                    console.log("Elemento: " + e);
                    let id_da_vez = "";
                    elementos_internos = e.split(',');
                    elementos_internos.forEach(function (ei) {
                        var campo_valor = ei.split(':');
                        if (campo_valor[0] === "id_caixa") {                            
                            id_da_vez = campo_valor[1];                      
                        } else if (campo_valor[0] === "formapagamento") {
                            conteudo_tabela += '<td align="center">' + campo_valor[1] + '</td>';
                        }
						
                    }); // fim foreach elementos internos
                    conteudo_tabela += '<td align="center"> <img title="Excluir Produto" src="media/icons/trash.png" width="30" height="30" onclick="objetoProduto.remove(' + id_da_vez + ')"> </td>';
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

var objetoCaixa = new function(){
	this.create = function(){
		var data	= document.getElementById("data").value;
		var descricao	= document.getElementById("descricao_caixa").value;
		var valor 	= document.getElementById("valor_caixa").value;
		var status 	= document.getElementById("status").value;
		var formapagamento 		= document.getElementById("formapagamento").value;
		var id_tipodespesa	= document.getElementById("id_tipodespesa").value;
		var id_cliente		= document.getElementById("id_cliente_caixa").value;
		var id_fornecedor		= document.getElementById("id_fornecedor").value;
		

		if( data != '' && descricao != '' && valor != '' && status != '' && formapagamento != '' && id_tipodespesa != '' && id_cliente != '' && id_fornecedor != '' ){
			xhttp.open("POST", "/sistema_financeiro_2017_A/"+path_principal, true);
			xhttp.setRequestHeader("Content-type","application/x-www-form-urlencoded");
			xhttp.send("data="+data+"&descricao="+descricao+"&valor="+valor+"&status="+status+"&formapagamento="+formapagamento+"&id_tipodespesa="+id_tipodespesa+"&id_cliente="+id_cliente+"&id_fornecedor="+id_fornecedor);

			alert(path_principal+" cadastrado com sucesso!"); // retorno ao usuário
			acessa_modulo(path_principal); // volta pra index principal do modulo ativo
		}else{
			alert("Para prosseguir é obrigatório preencher todos os campos!");
		}
	}

	this.update = function(identificador){
		if( id_sendo_alterado != "" ){ // PRONTO
			var data	= document.getElementById("data").value;
			var descricao	= document.getElementById("descricao_caixa").value;
			var valor 	= document.getElementById("valor_caixa").value;
			var status 	= document.getElementById("status").value;
			var formapagamento 		= document.getElementById("formapagamento").value;
			var id_tipodespesa	= document.getElementById("id_tipodespesa").value;
			var id_cliente		= document.getElementById("id_cliente_caixa").value;
			var id_fornecedor		= document.getElementById("id_fornecedor").value;

			xhttp.open("PUT", "/sistema_financeiro_2017_A/"+path_principal, true);
			xhttp.setRequestHeader("Content-type","application/x-www-form-urlencoded");
			xhttp.send("data="+data+"&descricao="+descricao+"&valor="+valor+"&status="+status+"&formapagamento="+formapagamento+"&id_tipodespesa="+id_tipodespesa+"&id_cliente="+id_cliente+"&id_fornecedor="+id_fornecedor);
		//	xhttp.send("id="+id_sendo_alterado+"nome="+nome_razao+"&endereco="+endereco+"&telefone="+telefone+"&email="+email+"&cpf="+cpf_cnpj+"&rg="+rg_ie+"&cep="+cep+"&contato="+contato+"&info_add="+info_add);

			id_sendo_alterado = "";

			acessa_modulo(path_principal);
		}else{ // FAZENDO
			id_sendo_alterado = identificador;
			
			var retorno = "";
			
            xhttp.onreadystatechange = function() {
                if (this.readyState == 4 && this.status == 200) {
                	retorno = this.responseText; console.log(this.responseText);
                }
            };	

            xhttp.open("GET", "/sistema_financeiro_2017_A/"+path_principal+"?id="+identificador, true);
			xhttp.send();

			acessa_sub_modulo('update');

			console.log("Retorno: "+retorno);
		}
	}

	this.remove = function(identificador){
		if(confirm("Deseja realmente remover esse "+path_principal)){
			xhttp.open("DELETE", "/sistema_financeiro_2017_A/"+path_principal+"?id="+identificador, true);
			xhttp.setRequestHeader("Content-type","application/x-www-form-urlencoded");
			xhttp.send("id="+identificador)
			acessa_modulo(path_principal); // mudar por remoção de linha somente - remover tr tabela
		}
	}
}
//Ações referentes ao Produto
function acessa_sub_modulo_produto(caminho) {
    xhttp.onreadystatechange = function () { document.getElementById("conteudo_submenu").innerHTML = this.responseText; };
    xhttp.open("GET", "./conteudo/" + path_principal + "/" + caminho + ".html", false);
    xhttp.send();

    xhttp.onreadystatechange = function () {
        if (this.readyState == 4 && this.status == 200) {
            elementos = this.responseText.split(',');
            elementos.forEach(function (e) {
                var campo_valor = e.split(':');
                if (campo_valor[0] === "id_produto") {
                    // não preciso fazer nada pois ja tenho o ID
                } else if (campo_valor[0] === "descricao") {
                    document.getElementById("descricao").value = campo_valor[1];

                } else if (campo_valor[0] === "codbarras") {
                    document.getElementById("codbarras").value = campo_valor[1];

                } else if (campo_valor[0] === "precocusto") {
                    document.getElementById("precocusto").value = campo_valor[1];

                } else if (campo_valor[0] === "precovenda") {
                    document.getElementById("precovenda").value = campo_valor[1];

                } else if (campo_valor[0] === "precominvenda") {
                    document.getElementById("precominvenda").value = campo_valor[1];

                } else if (campo_valor[0] === "precomaxvenda") {
                    document.getElementById("precomaxvenda").value = campo_valor[1];

                } else if (campo_valor[0] === "comissaovenda") {
                    document.getElementById("comissaovenda").value = campo_valor[1];

                } else if (campo_valor[0] === "qtdestoque") {
                    document.getElementById("qtdestoque").value = campo_valor[1];
                }

				else if (campo_valor[0] === "qtdminestoque") {
                    document.getElementById("qtdminestoque").value = campo_valor[1];
                }

				else if (campo_valor[0] === "altura") {
                    document.getElementById("altura").value = campo_valor[1];
                }

				else if (campo_valor[0] === "peso") {
                    document.getElementById("peso").value = campo_valor[1];
                }

				else if (campo_valor[0] === "largura") {
                    document.getElementById("largura").value = campo_valor[1];
                }

				else if (campo_valor[0] === "profundidade") {
                    document.getElementById("profundidade").value = campo_valor[1];
                }

				else if (campo_valor[0] === "validade") {
                    document.getElementById("validade").value = campo_valor[1];
                }

				else if (campo_valor[0] === "medidaproduto") {
                    document.getElementById("medidaproduto").value = campo_valor[1];
                }

				else if (campo_valor[0] === "tipoproduto") {
                    document.getElementById("tipoproduto").value = campo_valor[1];
                }

				else if (campo_valor[0] === "fornecedorCampo") {
                    document.getElementById("fornecedorCampo").value = campo_valor[1];
                }

				else if (campo_valor[0] === "funcionarioCampo") {
                    document.getElementById("funcionarioCampo").value = campo_valor[1];
                }

                document.getElementById("btn_alterar").setAttribute('onclick', 'objetoCaixa.update(' + id_sendo_alterado + ')');
            });
        }
    };
    xhttp.open("GET", "/sistema_financeiro_2017_A/" + path_principal + "?id=" + id_sendo_alterado, true);
    xhttp.send();
}

function carrega_tabela_produto() {
    xhttp.onreadystatechange = function () {
        if (this.readyState == 4 && this.status == 200) {
            var indice = 0;
            var aux = this.responseText.replace('[{', '');
            aux = aux.replace('}]', '');

            if (aux.indexOf('},{') == -1) { // string de jSon
                // fazer ainda
            } else { // lista de jSon
                var conteudo_tabela = "";
                let elementos_internos = "";
                elementos = aux.split('},{');
                elementos.forEach(function (e) {
                    console.log("Elemento: " + e);
                    let id_da_vez = "";
                    elementos_internos = e.split(',');
                    elementos_internos.forEach(function (ei) {
                        var campo_valor = ei.split(':');
                        if (campo_valor[0] === "id_produto") {
                            
                            id_da_vez = campo_valor[1];
                        } else if (campo_valor[0] === "descricao") {
                            conteudo_tabela += '<td>' + campo_valor[1] + '</td>';
                        } else if (campo_valor[0] === "precovenda") {
                            conteudo_tabela += '<td>' + campo_valor[1] + '</td>';
                        } else if (campo_valor[0] === "qtdestoque") {
                            conteudo_tabela += '<td align="center">' + campo_valor[1] + '</td>';
                        }
                    }); // fim foreach elementos internos
                    conteudo_tabela += '<td align="center"> <img title="Excluir Produto" src="media/icons/trash.png" width="30" height="30" onclick="objetoProduto.remove(' + id_da_vez + ')"> </td>';
                    conteudo_tabela += '</tr>';
                    indice++;
                }); // fim foreach elementos
                document.getElementById("corpo_tabela_index").innerHTML = conteudo_tabela;
            } // fim else
        }
    };
    xhttp.open("GET", "/sistema_financeiro_2017_A/" + path_principal + "?id=all", true);
    xhttp.send();
}

var objetoProduto = new function () {
    this.create = function () {
        var descricao = document.getElementById("descricao").value;
        var codbarras = document.getElementById("codbarras").value;
        var precocusto = document.getElementById("precocusto").value;
        var precovenda = document.getElementById("precovenda").value;
        var precominvenda = document.getElementById("precominvenda").value;
        var precomaxvenda = document.getElementById("precomaxvenda").value;
        var comissaovenda = document.getElementById("comissaovenda").value;
        var qtdestoque = document.getElementById("qtdestoque").value;
        var qtdminestoque = document.getElementById("qtdminestoque").value;
        var altura = document.getElementById("altura").value;
        var peso = document.getElementById("peso").value;
        var largura = document.getElementById("largura").value;
        var profundidade = document.getElementById("profundidade").value;
        var validade = document.getElementById("validade").value;
        var medidaproduto = document.getElementById("medidaproduto").value;
        var tipoproduto = document.getElementById("tipoproduto").value;
        var fornecedor = document.getElementById("fornecedorCampo").value;
        var funcionario = document.getElementById("funcionarioCampo").value;

        
            xhttp.open("POST", "/sistema_financeiro_2017_A/" + path_principal, true);
            xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
            xhttp.send("descricao=" + descricao + "&codbarras=" + codbarras + "&id_fornecedor=" + fornecedor + "&precocusto=" + precocusto + "&precovenda=" + precovenda + "&precominvenda=" + precominvenda + "&precomaxvenda=" + precomaxvenda + "&comissaovenda=" + comissaovenda + "&qtdestoque=" + qtdestoque + "&qtdminestoque=" + qtdminestoque + "&altura=" + altura + "&peso=" + peso + "&largura=" + largura + "&profundidade=" + profundidade + "&id_medidaproduto=" + medidaproduto + "&id_tipoproduto=" + tipoproduto + "&id_funcionario=" + funcionario + "&validade=" + validade);

            alert(path_principal + " cadastrado com sucesso!"); // retorno ao usuário
            acessa_modulo(path_principal); // volta pra index principal do modulo ativo
        
           
        
    }

    this.update = function (identificador) {
        if (id_sendo_alterado != "") {
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
                xhttp.open("PUT", "/sistema_financeiro_2017_A/" + path_principal + "?id=" + id_sendo_alterado + "&nome_razao=" + nome_razao + "&endereco=" + endereco + "&telefone=" + telefone + "&email=" + email + "&cpf_cnpj=" + cpf_cnpj + "&rg_ie=" + rg_ie + "&cep=" + cep + "&contato=" + contato + "&info_add=" + info_add, true);
                xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
                xhttp.send();

                id_sendo_alterado = "";
                acessa_modulo(path_principal);
            } else {
                alert("Para prosseguir é obrigatório preencher todos os campos!");
            }
        } else {
            id_sendo_alterado = identificador;
            acessa_sub_modulo('update');
        }
    }

    this.remove = function (identificador) {
        if (confirm("Deseja realmente remover esse " + path_principal)) {
            xhttp.open("DELETE", "/sistema_financeiro_2017_A/" + path_principal + "?id=" + identificador, true);
            xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
            xhttp.send()
            acessa_modulo(path_principal); // mudar por remoção de linha somente - remover tr tabela
        }
    }
}