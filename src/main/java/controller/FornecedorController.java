package controller;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Fornecedor;
import helper.JsonHelper;
import repository.FornecedorRepositoryBanco;

@WebServlet(urlPatterns = "/fornecedor")

public class FornecedorController extends HttpServlet{
	public static final long serialVersionUID = 1L;
	FornecedorRepositoryBanco frb = new FornecedorRepositoryBanco();
	private JsonHelper jsonHelper = new JsonHelper();

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String razao_social = req.getParameter("nome_razao");
		String endereco 	= req.getParameter("endereco");
		String telefone 	= req.getParameter("telefone");
		String email 		= req.getParameter("email");
		String cnpj 		= req.getParameter("cpf_cnpj");
		String ie 			= req.getParameter("rg_ie");
		String cep 			= req.getParameter("cep");
		String contato 		= req.getParameter("contato");
		String info_add 	= req.getParameter("info_add");

		Fornecedor fornecedor = new Fornecedor(razao_social,endereco,telefone,email,cnpj,ie,cep,contato,info_add);
		frb.cadastrar(fornecedor);
		try {
			resp.getWriter().println(jsonHelper.gerarJson(fornecedor));
		} catch (IllegalArgumentException | IllegalAccessException | InvocationTargetException e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String json;
		String idb = req.getParameter("id");

		if( idb.equals("all") ){
		    try {
				json = jsonHelper.gerarJsonLista(frb.buscarTodos());
				resp.getWriter().print(json);
			} catch (IllegalArgumentException | IllegalAccessException | InvocationTargetException e) {
				e.printStackTrace();
			}
		} else {
			int id = Integer.parseInt(req.getParameter("id"));
			try {
				json = jsonHelper.gerarJson(frb.buscarPorId(id));
				resp.getWriter().print(json);
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			}	
		}
	}

	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Integer id 			= Integer.parseInt(req.getParameter("id"));
		String razao_social	= req.getParameter("nome_razao");
		String endereco 	= req.getParameter("endereco");
		String telefone 	= req.getParameter("telefone");
		String email 		= req.getParameter("email");
		String cnpj 		= req.getParameter("cpf_cnpj");
		String ie 			= req.getParameter("rg_ie");
		String cep 			= req.getParameter("cep");
		String contato 		= req.getParameter("contato");
		String info_add 	= req.getParameter("info_add");

		Fornecedor fornecedor = new Fornecedor(razao_social,endereco,telefone,email,cnpj,ie,cep,contato,info_add);
		fornecedor.setId(id);

		frb.alterar(fornecedor);
	}

	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int id = Integer.parseInt(req.getParameter("id"));
		frb.excluir(id);
	}
}
