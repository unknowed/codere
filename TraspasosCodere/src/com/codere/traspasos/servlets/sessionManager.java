package com.codere.traspasos.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import javax.servlet.http.Cookie;

/**
 * Servlet implementation class sessionManager
 */
@WebServlet("/sessionManager")
public class sessionManager extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public sessionManager() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String usuario = request.getParameter("hdnusuario");
		String seleccion = request.getParameter("selectsalas");
		String val[] = seleccion.split("/");
		

		//creo la session y le asigno los valores que necesito guardar
		HttpSession session = request.getSession();
		session.setAttribute("usuario", usuario);
		session.setAttribute("werks", val[1]);
		session.setAttribute("lgort", val[0]);
		session.setAttribute("descripcion", val[2]);

		
		// valores de expire y demas sarasas
		session.setMaxInactiveInterval(30*60);
		Cookie cusuario = new Cookie("usuario", usuario);
		cusuario.setMaxAge(30*60);
		response.addCookie(cusuario);
		Gson gson = new Gson(); 
        JsonObject r = new JsonObject();
        r.addProperty("success", true);
        
        response.getWriter().append(r.toString());
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
