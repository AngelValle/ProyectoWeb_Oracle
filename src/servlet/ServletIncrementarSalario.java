package servlet;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;





public class ServletIncrementarSalario extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException 
	{
		super.doGet(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException 
	{
		//TODO Hacer bien el incremento de salario obtenido desde el ServletConfig
		ServletConfig servletconfig = getServletConfig();
		String incremental = servletconfig.getInitParameter("incrementalsalario");
		System.out.println(incremental);
	}
	
	
}
