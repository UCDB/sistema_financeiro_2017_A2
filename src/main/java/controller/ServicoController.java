package controller;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import helper.JsonHelper;
import model.Servico;
import repository.ServicoRepositoryBanco;
import utils.RottaUtils;
@WebServlet(urlPatterns = "/servicocontroller")
public class ServicoController extends HttpServlet{


	private static final long serialVersionUID = 1L;
		private ServicoRepositoryBanco servicoRepository = new ServicoRepositoryBanco();
		private JsonHelper jsonHelper = new JsonHelper();
		
		protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			Servico serv= (Servico) RottaUtils.populaReq(new Servico(), req.getParameterMap());
			
			servicoRepository.cadastrar(serv);
			try {
				resp.getWriter().println(jsonHelper.gerarJson(serv));
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
		
		protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			String json;			
			try {				
				json = jsonHelper.gerarJsonLista(servicoRepository.buscarTodos());
				resp.getWriter().print(json);
			} catch (IllegalArgumentException | IllegalAccessException | InvocationTargetException e) {
				
				e.printStackTrace();
			}
			
		}
		
		@Override
		protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			Integer idFunc = Integer.parseInt(req.getParameter("idfunc"));
			Integer idServ = Integer.parseInt(req.getParameter("idserv"));			
			String descricao = req.getParameter("descricao");
			String tipo = req.getParameter("tipo");
			Double valorServico = Double.parseDouble(req.getParameter("valorservico"));
			Double valorMax = Double.parseDouble(req.getParameter("valormax"));
			Double valorMin = Double.parseDouble(req.getParameter("valormin"));			
			
			Servico servico = new Servico();
			
			if (idFunc != null ){
				servico.setId_funcionario(idFunc);
			}
			if (descricao != null){
				servico.setDescricao(descricao);
			}
			
			if (valorServico != null ){
				servico.setValorservico(valorServico);
			}
			if (valorMax != null ){
				servico.setValormaximo(valorMax);
			}
			if (valorMin != null ){
				servico.setValorminimo(valorMin);
			}	
			
			servicoRepository.alterar(servico);
		
		}
		
		@Override
		protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			
			int id = Integer.parseInt(req.getParameter("id"));			
			servicoRepository.excluir(id);
		
		}
}
