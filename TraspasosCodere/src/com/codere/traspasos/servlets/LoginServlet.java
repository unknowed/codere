package com.codere.traspasos.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

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
		DT_USUARIO_REQ r = new DT_USUARIO_REQ(request.getParameter("user"));
		DT_USUARIO_RES a;
		SI_USUARIOSALA_SOProxy p = new SI_USUARIOSALA_SOProxy();
		try {
			
			response.setContentType("text/html");
	        response.setHeader("Cache-control", "no-cache, no-store");
	        response.setHeader("Pragma", "no-cache");
	        response.setHeader("Expires", "-1");

	        response.setHeader("Access-Control-Allow-Origin", "*");
	        response.setHeader("Access-Control-Allow-Methods", "POST");
	        response.setHeader("Access-Control-Allow-Headers", "Content-Type");
	        response.setHeader("Access-Control-Max-Age", "86400");

	        a = p.SI_USUARIOSALA_SO(r);
	        log("acaba de llenar a");
	        Gson gson = new Gson(); 
	        JsonObject res = new JsonObject();
	        
	        res.addProperty("success", true);
	        res.addProperty("type", 0);
	        res.add("result", gson.toJsonTree(a.getSALAS()));
	        log(res.toString());
	        response.getWriter().append(res.toString());
	        
		}catch(MT_FAULT e) {
			
	        //response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
	        Gson gson = new Gson(); 
	        JsonObject myObj = new JsonObject();
			
	        if(e.getStandard().getFaultText().equals("Sin datos")) {
	        	myObj.addProperty("success", false);
		        myObj.addProperty("type", "1"); //Sin Datos
		        myObj.add("result",gson.toJsonTree(r));
//		        response.sendError(400, myObj.toString());
				response.getWriter().append(myObj.toString());
	        }
			log("dio error " + e.getStandard().getFaultText().toString());
			log(myObj.toString());
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
