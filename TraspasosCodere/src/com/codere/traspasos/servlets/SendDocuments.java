package com.codere.traspasos.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import entrada_cendest.sap.document.sap_com.DT_ENTRADAS_REQ;
import entrada_cendest.sap.document.sap_com.DT_ENTRADAS_REQI_ENTRADAItem;
import entrada_cendest.sap.document.sap_com.DT_ENTRADAS_RES;
import entrada_cendest.sap.document.sap_com.SI_ENTRADAS_SOProxy;
import solpeds.sap.document.sap_com.MT_FAULT;

/**
 * Servlet implementation class SendDocuments
 */
@WebServlet("/SendDocuments")
public class SendDocuments extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SendDocuments() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@SuppressWarnings("unchecked")
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		//Levanto la session
		HttpSession session = request.getSession();

		boolean edit = session.getAttribute("posiciones") != null ; 
		if (edit) {
			
			String texto = null;
			texto = request.getParameter("textCabecera");
			log(texto);
			log(request.getQueryString());
			//Creo objeto de request al WS
			DT_ENTRADAS_REQ req = new DT_ENTRADAS_REQ();
			
			//levanto la lista de memoria de las cosas que hay que enviar el WS 
			List<DT_ENTRADAS_REQI_ENTRADAItem> list = null;
			list = (List<DT_ENTRADAS_REQI_ENTRADAItem>) session.getAttribute("posiciones");
			
			//Convierto la lista en un array para pasarselo a objeto de Req del WS
			DT_ENTRADAS_REQI_ENTRADAItem[] arrEntrada = list.toArray(new DT_ENTRADAS_REQI_ENTRADAItem[list.size()]);
			
			//Le asigno el array y posteriormente el texto cabecera.
			req.setI_ENTRADA(arrEntrada);
			req.setI_TEXT(texto);
			//req.setI_TEXT(null);
			
			//Creo objeto de response y el proxy
			DT_ENTRADAS_RES res;
			SI_ENTRADAS_SOProxy p = new SI_ENTRADAS_SOProxy();
			JsonObject jres = new JsonObject();
			
			// ejecuto el proxy pasandole el request y almaceno el resultado en el objeto que devuelve de response. y temrino la sarasa.
			try {
				res = p.SI_ENTRADAS_SO(req);
				////////////// TEMPORAL QUITAR UNA VEZ QUIERA MANDAR AL BACKEND
				Gson gson = new Gson();
				log(gson.toJson(req) );				
				////////////// HASTA ACA ES TEMPORAL
				jres.addProperty("success", true);
				jres.addProperty("message", res.getRETURN()[0].getMESSAGE());
				response.getWriter().append(jres.toString());
				log("no dio error");
			}catch(MT_FAULT e) { /////////////////////////////////// CAMBIAR POR MTFAULT
				jres.addProperty("success", false);
				jres.addProperty("message", e.getStandard().getFaultText());
				log("dio error");
				
			}finally {
				session.setAttribute("posiciones", null);
			}
			
			
			
			
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
