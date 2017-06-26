package controller;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import helper.JsonHelper;
import model.Caixa;
import repository.CaixaRepositoryBanco;
import utils.RottaUtils;
@WebServlet(urlPatterns = "/caixa")
public class CaixaController extends HttpServlet{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private CaixaRepositoryBanco caixaRepository = new CaixaRepositoryBanco();
	private JsonHelper jsonHelper = new JsonHelper();
	
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Caixa caix = (Caixa) RottaUtils.populaReq(new Caixa(), req.getParameterMap());

		
		caixaRepository.cadastrar(caix);
		try {
			resp.getWriter().println(jsonHelper.gerarJson(caix));
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
		String idb = req.getParameter("id");
		//System.out.println("IDB: "+idb);
		if( idb.equals("all") ){
			try {
				json = jsonHelper.gerarJsonLista(caixaRepository.buscarTodos());
				resp.getWriter().print(json);
			} catch (IllegalArgumentException | IllegalAccessException | InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			int id = Integer.parseInt(req.getParameter("id"));
			//System.out.print("ID: "+id);
			try {
				json = jsonHelper.gerarJson(caixaRepository.buscarPorId(id));
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
	
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Caixa caix = (Caixa) RottaUtils.populaReq(new Caixa(), req.getParameterMap());	
		
		caixaRepository.alterar(caix);
	
	}
	
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		int id = Integer.parseInt(req.getParameter("id"));			
		caixaRepository.excluir(id);
	
	}
	
	
}
