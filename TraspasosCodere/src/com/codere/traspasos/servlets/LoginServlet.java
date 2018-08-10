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
				
		//levanto el objeto de request al que le paso el valor del usuario
		DT_USUARIO_REQ req = new DT_USUARIO_REQ(request.getParameter("user"));
		//objeto de respuesta
		DT_USUARIO_RES res;
		//proxy
		SI_USUARIOSALA_SOProxy p = new SI_USUARIOSALA_SOProxy();

		//seteo las propiedades de la respuesta
		response.setContentType("text/html");
        response.setHeader("Cache-control", "no-cache, no-store");
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Expires", "-1");

        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "POST");
        response.setHeader("Access-Control-Allow-Headers", "Content-Type");
        response.setHeader("Access-Control-Max-Age", "86400");

		
		try {
			
			//obtengo los resultados del WS y los devuelvo a la pagina.
	        res = p.SI_USUARIOSALA_SO(req);
	        //objetos para generar json
	        Gson gson = new Gson(); 
	        JsonObject jres = new JsonObject();
	        
	        //agrego propiedades para determinar si hubo error o no y que tipo.
	        jres.addProperty("success", true);
	        jres.addProperty("type", 0);
	        
	        //Agrego la lista de salas a la propiedad result 
	        jres.add("result", gson.toJsonTree(res.getSALAS()));
	        
	        
	        //lo mando a la pagina como json para que haga lo que debe hacer
	        response.getWriter().append(jres.toString());
	        
		}catch(MT_FAULT e) {
			//En caso de error que devuelva el servidor de SAP
	        //response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
	        Gson gerrcontrolado = new Gson(); 
	        JsonObject jreserr = new JsonObject();
			
	        if(e.getStandard().getFaultText().equals("Sin datos")) {
	        	jreserr.addProperty("success", false);
	        	jreserr.addProperty("type", "1"); //Sin Datos
	        	jreserr.add("result",gerrcontrolado.toJsonTree(req));
		        //response.sendError(503, jreserr.toString());
				response.getWriter().append(jreserr.toString());
	        } 
		}catch(Exception ex) {
	        Gson gerr = new Gson(); 
	        JsonObject jerr = new JsonObject();
			
        	jerr.addProperty("success", false);
        	jerr.addProperty("type", "2"); //Error Server
        	jerr.add("result",gerr.toJsonTree(req));
//		        response.sendError(400, myObj.toString());
			response.getWriter().append(jerr.toString());
			
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
