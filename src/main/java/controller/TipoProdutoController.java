package controller;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import helper.JsonHelper;
import model.Produto;
import model.TipoProduto;
import repository.TipoDespesaRepositoryBanco;
import repository.TipoProdutoRepositoryBanco;
import utils.RottaUtils;
@WebServlet(urlPatterns = "/tipoprodutocontroller")
public class TipoProdutoController extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private TipoProdutoRepositoryBanco tipoProdutoRB = new TipoProdutoRepositoryBanco();
	private JsonHelper jsonHelper = new JsonHelper();
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {		
		
		
		TipoProduto tipoPro = (TipoProduto) RottaUtils.populaReq(new TipoProduto(), req.getParameterMap());

		
		tipoProdutoRB.cadastrar(tipoPro);
		try {
			resp.getWriter().println(jsonHelper.gerarJson(tipoPro));
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
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String json;			
		try {				
			json = jsonHelper.gerarJson(tipoProdutoRB.buscarTodos());
			resp.getWriter().print(json);
		} catch (IllegalArgumentException | IllegalAccessException | InvocationTargetException e) {
			
			e.printStackTrace();
		}
	}
	
	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		TipoProduto tipoPro = (TipoProduto) RottaUtils.populaReq(new TipoProduto(), req.getParameterMap());
		
		tipoProdutoRB.alterar(tipoPro);
		
	}
	
	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int id = Integer.parseInt(req.getParameter("id"));
		tipoProdutoRB.excluir(id);
	}
}
