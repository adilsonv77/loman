package br.udesc.loman.web;

import br.udesc.dao.jpa.JPAUtil;
import br.udesc.loman.dao.core.LoManDAOFactory;
import br.udesc.loman.dao.jpa.JPADAOFactory;
import java.util.TimeZone;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class LoManListener implements ServletContextListener {

    private static LoManDAOFactory daoFactory;

    public void contextInitialized(ServletContextEvent sce) {

		try {
			TimeZone zone = TimeZone.getTimeZone("AGT");
			TimeZone.setDefault(zone);

			daoFactory = new JPADAOFactory();

                        JPAUtil.inicializar("loman");
                        
                    sce.getServletContext().setAttribute("versao", "08/05/2012");


		} catch (Exception e) {
			e.printStackTrace();
		}
    }

    public void contextDestroyed(ServletContextEvent evt) {

    }

	public static LoManDAOFactory getDAOFactory() {
		return daoFactory;
    }

}
