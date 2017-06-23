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
	FuncionarioRepositoryBanco funcRb = new FuncionarioRepositoryBanco();
	private JsonHelper jsonHelper = new JsonHelper();
	
	@Override
	protected void doPost (HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String 	nome 		= req.getParameter("nome");
		String	endereco 	= req.getParameter("endereco");
		String	cpf 		= req.getParameter("cpf");
		String	rg 			= req.getParameter("rg");
		String	telefone 	= req.getParameter("telefone");
		String	cep 		= req.getParameter("cep");
		String	email 		= req.getParameter("email");
		String	infoAdc		= req.getParameter("info_add");
		String	contato		= req.getParameter("contato");

		Funcionario f = new Funcionario(nome, endereco, cpf, rg, telefone, cep, email, infoAdc, contato);
		funcRb.cadastrar(f);

		try {
			resp.getWriter().println(jsonHelper.gerarJson(f));
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}

	public void doGet (HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		String json;
		String idb = req.getParameter("id");

		if(idb.equals("all")){
		    try {
				json = jsonHelper.gerarJsonLista(funcRb.buscarTodos());
				resp.getWriter().print(json);
			} catch (IllegalArgumentException | IllegalAccessException | InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			idb = req.getParameter("id");
			int id = Integer.parseInt(idb);
			try {
				json = jsonHelper.gerarJson(funcRb.buscarPorId(id));
				resp.getWriter().print(json);
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
		}
	}

	public void doPut (HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		int idFunc = Integer.parseInt(req.getParameter("id"));

		String 	nome 		= req.getParameter("nome");
		String	endereco 	= req.getParameter("endereco");
		String	cpf 		= req.getParameter("cpf");
		String	rg 			= req.getParameter("rg");
		String	telefone 	= req.getParameter("telefone");
		String	cep 		= req.getParameter("cep");
		String	email 		= req.getParameter("email");
		String	infoAdc		= req.getParameter("info_add");
		String	contato		= req.getParameter("contato");

		Funcionario func = new Funcionario(nome, endereco, cpf, rg, telefone, cep, email, infoAdc, contato);
		func.setId(idFunc);
		funcRb.alterar(func);
	}

	public void doDelete (HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		int id = Integer.parseInt(req.getParameter("id"));
		funcRb.excluir(id);
	}
}