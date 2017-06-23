package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import helper.JsonHelper;
import model.Cliente;
import repository.ClienteRepositoryBanco;

@WebServlet(urlPatterns = "/clcontroller")
public class ClienteController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private JsonHelper jsonHelper = new JsonHelper();

	private ClienteRepositoryBanco cr = new ClienteRepositoryBanco();

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		Integer id = Integer.parseInt(req.getParameter("id"));
		String nome = req.getParameter("nome");
		String endereco = req.getParameter("endereco");
		String cpf = req.getParameter("cpf");
		String rg = req.getParameter("rg");
		String telefone = req.getParameter("telefone");
		String cep = req.getParameter("cep");
		String contato = req.getParameter("contato");
		String info = req.getParameter("info");
		String email = req.getParameter("email");

		Cliente cl = new Cliente(id, nome, endereco, cpf, rg, telefone, cep, contato, info, email);

	}

	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		// capturando valores para alterar
		Integer id = Integer.parseInt(req.getParameter("id"));
		String nome = req.getParameter("nome");
		String endereco = req.getParameter("endereco");
		String cpf = req.getParameter("cpf");
		String rg = req.getParameter("rg");
		String telefone = req.getParameter("telefone");
		String cep = req.getParameter("cep");
		String contato = req.getParameter("contato");
		String info = req.getParameter("info");
		String email = req.getParameter("email");

		Cliente cl = new Cliente();
		//verificando caso algum valor esteja null, evitando erros posteriormente
		if (id != null) {
			cl.setId(id);
		}
		if (nome != null) {
			cl.setNome(nome);
		}
		if (endereco != null) {
			cl.setEndereco(endereco);
		}
		if (cpf != null) {
			cl.setCpf(cpf);
		}
		if (rg != null) {
			cl.setRg(rg);
		}
		if (telefone != null) {
			cl.setTelefone(telefone);
		}
		if (cep != null) {
			cl.setCep(cep);
		}
		if (contato != null) {
			cl.setContato(contato);
		}
		if (info != null) {
			cl.setInfo(info);
		}
		if (email != null) {
			cl.setEmail(email);
		}
		
		cr.alterar(cl);
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		if (req.getParameter("buscar").equals("busca_todos")) {
			cr.buscarTodos();
		} else if (req.getParameter("buscar").equals("busca_id")) {
			Integer id = Integer.parseInt(req.getParameter("id"));
			cr.buscarPorId(id);
		}
	}

	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Integer id = Integer.parseInt(req.getParameter("id"));
		cr.excluir(id);
	}

}
