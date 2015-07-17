package baul;


import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.SessionFactory;

import conexiones.SessionManager;

public class ServletContextListener implements javax.servlet.ServletContextListener {

	private final Logger logger = LogManager.getRootLogger();
	
	@Override
	public void contextDestroyed(ServletContextEvent servletcontextevent) 
	{
		ServletContext servletcontext = null;
		SessionFactory sessionfactory = null;

		servletcontext = servletcontextevent.getServletContext();
		
		try
		{
			SessionManager.cerrarFactory();   // Este metodo suprime el recoger el factory de nuevo y cerrarlo
			sessionfactory = (SessionFactory)servletcontext.getAttribute("SessionFactory");
			logger.info("Session Factory recogida con exito");
			sessionfactory.close();
			logger.info("Session Factory cerrada con SessionManager");
		}
		catch(Exception e)
		{
			e.printStackTrace();
			logger.error("Error al recoger Session Factory del ServletContext o cerrarlo");
		}
		logger.trace("ServletContextListener : Destruido");
	}

	@Override
	public void contextInitialized(ServletContextEvent servletcontextevent) 
	{
		Map<String, HttpSession> map_sesiones = new HashMap<String, HttpSession>();
		ServletContext servletcontext = null;
		SessionFactory sessionfactory = null;
		int peticiones_contador = 0;
		int peticiones_activas = 0;
		
		servletcontext = servletcontextevent.getServletContext();
		try
		{
			sessionfactory = SessionManager.getSessionFactory();
			logger.info("Session Factory creada con SessionManager");
			
			servletcontext.setAttribute("SessionFactory", sessionfactory);
			logger.info("Session Factory insertada en ServletContext");
			
			servletcontext.setAttribute("peticiones_contador", peticiones_contador);
			logger.info("Contador de peticiones establecido a 0");
			
			servletcontext.setAttribute("peticiones_activas", peticiones_activas);
			logger.info("Peticiones Activas establecido a 0");
			
			servletcontext.setAttribute("map_sesiones", map_sesiones);
			logger.info("HashMap de sesiones implementado como Atributo en ServletContext");
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
			logger.error("Error al crear Session Factory o insertarlo en el  ServletContext");
		}
		
		logger.trace("ServletContextListener : Inicializado");
	}
}
