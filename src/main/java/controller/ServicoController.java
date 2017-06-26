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
import model.Servico;
import repository.ServicoRepositoryBanco;
import utils.RottaUtils;
@WebServlet(urlPatterns = "/servcontroller")
public class ServicoController extends HttpServlet{

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
				json = jsonHelper.gerarJson(servicoRepository.buscarTodos());
				resp.getWriter().print(json);
			} catch (IllegalArgumentException | IllegalAccessException | InvocationTargetException e) {
				
				e.printStackTrace();
			}
			
		}
		
		@Override
		protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			Servico servic = (Servico) RottaUtils.populaReq(new Servico(), req.getParameterMap());
			
			servicoRepository.alterar(servic);
		
		}
		
		@Override
		protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			
			int id = Integer.parseInt(req.getParameter("id"));			
			servicoRepository.excluir(id);
		
		}
}
