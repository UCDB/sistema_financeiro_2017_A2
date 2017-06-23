package controller;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import helper.JsonHelper;
import model.TipoServico;
import repository.TipoServicoRepositoryBanco;
import utils.RottaUtils;
@WebServlet(urlPatterns = "/tiposervicocontroller")
public class TipoServicoController extends HttpServlet{
	
	
	private static final long serialVersionUID = 1L;
	
	private TipoServicoRepositoryBanco tipoServicoRepository = new TipoServicoRepositoryBanco();
	private JsonHelper jsonHelper = new JsonHelper();
	
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {		
		
		TipoServico serv = (TipoServico) RottaUtils.populaReq(new TipoServico(), req.getParameterMap());	

		
		tipoServicoRepository.cadastrar(serv);
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
			json = jsonHelper.gerarJsonLista(TipoServicoRepositoryBanco.buscarTodos());
			resp.getWriter().print(json);
		} catch (IllegalArgumentException | IllegalAccessException | InvocationTargetException e) {
			
			e.printStackTrace();
		}
	}

	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		TipoServico serv = (TipoServico) RottaUtils.populaReq(new TipoServico(), req.getParameterMap());	
		
		tipoServicoRepository.alterar(serv);
	
	}
	
	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		int id = Integer.parseInt(req.getParameter("id"));			
		tipoServicoRepository.excluir(id);
	
	}
}
