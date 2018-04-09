package com.codere.traspasos.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import salas_usuarios.sap.document.sap_com.BC_APPTRASP_SI_USUARIOSALA_SOLocator;
import salas_usuarios.sap.document.sap_com.DT_USUARIO_REQ;
import salas_usuarios.sap.document.sap_com.DT_USUARIO_RES;
import salas_usuarios.sap.document.sap_com.SI_USUARIOSALA_SOBindingStub;
import salas_usuarios.sap.document.sap_com.SI_USUARIOSALA_SOProxy;
import solpeds.sap.document.sap_com.MT_FAULT;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		//log();
		DT_USUARIO_REQ r = new DT_USUARIO_REQ("usuario");
		DT_USUARIO_RES a;
		SI_USUARIOSALA_SOProxy p = new SI_USUARIOSALA_SOProxy();
		try {
			a = p.SI_USUARIOSALA_SO(r);
		}catch(MT_FAULT e) {
			response.getWriter().append("Sin Datos");
			log("dio error " + e.getStandard().getFaultText().toString());
		}
		
		log("paso por aca");
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
