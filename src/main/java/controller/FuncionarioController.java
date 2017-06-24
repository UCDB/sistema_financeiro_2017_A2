package controller;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import helper.JsonHelper;
import model.Funcionario;
import repository.FuncionarioRepositoryBanco;

@WebServlet(urlPatterns = "/funcionario")
public class FuncionarioController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	FuncionarioRepositoryBanco frb = new FuncionarioRepositoryBanco();
	private JsonHelper jsonHelper = new JsonHelper();

	@Override
	protected void doPost (HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String 	nome 		= req.getParameter("nome");
		String	endereco 	= req.getParameter("endereco");
		String	telefone 	= req.getParameter("telefone");
		String	email 		= req.getParameter("email");
		String	cpf 		= req.getParameter("cpf");
		String	rg 			= req.getParameter("rg");
		String	cep 		= req.getParameter("cep");
		String	contato		= req.getParameter("contato");
		String	info_add	= req.getParameter("info_add");

		Funcionario f = new Funcionario(nome, endereco, telefone, email, cpf, rg, cep, contato, info_add);
		frb.cadastrar(f);

		try {
			resp.getWriter().println(jsonHelper.gerarJson(f));
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}	
	}

	public void doGet (HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
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

	public void doPut (HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		int id 				= Integer.parseInt(req.getParameter("id"));
		String 	nome 		= req.getParameter("nome");
		String	endereco 	= req.getParameter("endereco");
		String	telefone 	= req.getParameter("telefone");
		String	email 		= req.getParameter("email");
		String	cpf 		= req.getParameter("cpf");
		String	rg 			= req.getParameter("rg");
		String	cep 		= req.getParameter("cep");
		String	contato		= req.getParameter("contato");
		String	info_add	= req.getParameter("info_add");

		Funcionario func = new Funcionario(nome, endereco, telefone, email, cpf, rg, cep, contato, info_add);
		func.setId(id);
		frb.alterar(func);
	}

	public void doDelete (HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		int id = Integer.parseInt(req.getParameter("id"));
		frb.excluir(id);
	}
}