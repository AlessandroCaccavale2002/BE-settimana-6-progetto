package it.presentation;

import java.io.IOException;
import java.io.PrintWriter;

import it.business.BancomatEjb;
import it.data.ContoCorrenteDAO;
import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class Servlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

    @EJB
    BancomatEjb ban;

	@SuppressWarnings("unused")
	private Long idconto;

	@SuppressWarnings("unused")
	private int importo;

	@SuppressWarnings("unused")
	private ContoCorrenteDAO ccd;

	public void MyServlet() {
		
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		idconto = Long.parseLong(request.getParameter("id"));
		importo = Integer.parseInt(request.getParameter("importo"));
		PrintWriter out = response.getWriter();
		
		ccd = new ContoCorrenteDAO();
		
		String pa = request.getParameter("banca");
		if(pa.equalsIgnoreCase("preleva")) {
			out.println(pa);
		}
	}
		
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
