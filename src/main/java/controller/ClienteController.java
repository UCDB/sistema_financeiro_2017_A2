package controller;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import helper.JsonHelper;
import model.Cliente;
import repository.ClienteRepositoryBanco;

@WebServlet(urlPatterns = "/cliente")
public class ClienteController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ClienteRepositoryBanco crb = new ClienteRepositoryBanco();

	private JsonHelper jsonHelper = new JsonHelper();

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String nome_razao	= req.getParameter("nome_razao");
		String endereco 	= req.getParameter("endereco");
		String telefone 	= req.getParameter("telefone");
		String email 		= req.getParameter("email");
		String cpf_cpnj 	= req.getParameter("cpf_cnpj");
		String rg_ie 		= req.getParameter("rg_ie");
		String cep 			= req.getParameter("cep");
		String contato 		= req.getParameter("contato");
		String info_add		= req.getParameter("info_add");

		Cliente cl = new Cliente(nome_razao, endereco, telefone, email, cpf_cpnj, rg_ie, cep, contato, info_add);
		crb.cadastrar(cl);

		try {
			resp.getWriter().println(jsonHelper.gerarJson(cl));
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

	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Integer id 			= Integer.parseInt(req.getParameter("id"));
		String nome_razao 	= req.getParameter("nome_razao");
		String endereco 	= req.getParameter("endereco");
		String telefone 	= req.getParameter("telefone");
		String email 		= req.getParameter("email");
		String cpf_cnpj 	= req.getParameter("cpf_cnpj");
		String rg_ie 		= req.getParameter("rg_ie");
		String cep 			= req.getParameter("cep");
		String contato 		= req.getParameter("contato");
		String info_add 	= req.getParameter("info_add");

		Cliente cl = new Cliente(nome_razao,endereco,telefone,email,cpf_cnpj,rg_ie,cep,contato,info_add);
		cl.setId(id);

		crb.alterar(cl);
	}

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String json;
		String idb = req.getParameter("id");
		//System.out.println("IDB: "+idb);
		if( idb.equals("all") ){
			try {
				json = jsonHelper.gerarJsonLista(crb.buscarTodos());
				resp.getWriter().print(json);
			} catch (IllegalArgumentException | IllegalAccessException | InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			int id = Integer.parseInt(req.getParameter("id"));
			//System.out.print("ID: "+id);
			try {
				json = jsonHelper.gerarJson(crb.buscarPorId(id));
				resp.getWriter().print(json);
				//System.out.print(json);
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

	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Integer id = Integer.parseInt(req.getParameter("id"));
		crb.excluir(id);
	}
}