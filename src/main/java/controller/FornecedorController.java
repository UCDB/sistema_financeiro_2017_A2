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
import repository.FornecedorRepository;

@WebServlet(urlPatterns = "/forncontroller")

public class FornecedorController extends HttpServlet{
	public static final long serialVersionUID = 1L;
	
	private FornecedorRepository fr = new FornecedorRepositoryBanco();
	
	private JsonHelper jsonHelper = new JsonHelper();
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String nome = req.getParameter("nome");
		String endereco = req.getParameter("endereco");
		String cpf_cnpj = req.getParameter("cpf_cnpj");
		String rg_ie = req.getParameter("rg_ie");
		String telefone = req.getParameter("telefone");
		String cep = req.getParameter("cep");
		String contato = req.getParameter("contato");
		String info_adicional = req.getParameter("info_adicional");
		String email = req.getParameter("email");
		
	
		Fornecedor fornecedor = new Fornecedor(nome,endereco,cpf_cnpj,rg_ie,telefone,cep,contato,info_adicional,email);
		fr.cadastrar(fornecedor);
		try {
			resp.getWriter().println(jsonHelper.gerarJson(fornecedor));
		} catch (IllegalArgumentException | IllegalAccessException | InvocationTargetException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String json;
		try {
			json = jsonHelper.gerarJson(fr.buscarTodos());
			resp.getWriter().print(json);
		} catch (IllegalArgumentException | IllegalAccessException | InvocationTargetException e) {
			e.printStackTrace();
		}
	}
	
	//Falta Preencher
	
	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String idForn = req.getParameter("id");
		Integer id = Integer.parseInt(idForn);
		
		String nome = req.getParameter("nome");
		String endereco = req.getParameter("endereco");
		String cpf_cnpj = req.getParameter("cpf_cnpj");
		String rg_ie = req.getParameter("rg_ie");
		String telefone = req.getParameter("telefone");
		String cep = req.getParameter("cep");
		String contato = req.getParameter("contato");
		String info_adicional = req.getParameter("info_adicional");
		String email = req.getParameter("email");
		
		Fornecedor fornecedor = new Fornecedor();
		fornecedor.setId(id);
		fornecedor.setNome(nome);
		fornecedor.setEndereco(endereco);
		fornecedor.setCPF_CNPJ(cpf_cnpj);
		fornecedor.setRG_IE(rg_ie);
		fornecedor.setTelefone(telefone);
		fornecedor.setCEP(cep);
		fornecedor.setContato(contato);
		fornecedor.setInfo(info_adicional);
		fornecedor.setEmail(email);
		
		fr.alterar(fornecedor);
		
	}
	
	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int id = Integer.parseInt(req.getParameter("id"));
		fr.excluir(id);
	}
	
}
