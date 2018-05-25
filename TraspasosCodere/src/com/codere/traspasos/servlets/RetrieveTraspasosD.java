package com.codere.traspasos.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import entrada_cendest.sap.document.sap_com.DT_ENTRADAS_REQI_ENTRADAItem;
import solpeds.sap.document.sap_com.MT_FAULT;
import visual_traspaso.sap.document.sap_com.DT_VITRASP_REQ;
import visual_traspaso.sap.document.sap_com.DT_VITRASP_RES;
import visual_traspaso.sap.document.sap_com.DT_VITRASP_RESSTOCDETItem;
import visual_traspaso.sap.document.sap_com.SI_VTRASPASOS_SOProxy;

/**
 * Servlet implementation class RetrieveTraspasosD
 */
@WebServlet("/RetrieveTraspasosD")
public class RetrieveTraspasosD extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RetrieveTraspasosD() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession();
		
		// Levanto si viene valor documento hago cosas sino no.
		if( !request.getParameter("doc").equals("0")) {
			// pongo todos los parametros que necesito con los valores default y que tengo en memoria
			DT_VITRASP_REQ r = new DT_VITRASP_REQ();
			r.setTYBUS("P");
			r.setWERKS(session.getAttribute("werks").toString());
			r.setLGORT(session.getAttribute("lgort").toString());
			r.setDOCMATH("");
			r.setMATNRH("");
			r.setMATNRL("");
			r.setTYCON("D");
			r.setDOCMATL(request.getParameter("doc").toString());			

			// Creo objeto para paginar
			List<DT_VITRASP_RESSTOCDETItem> arrResp = new ArrayList<DT_VITRASP_RESSTOCDETItem>();
			
			// JSon Objects.
			Gson gson = new Gson();
			JsonObject jres = new JsonObject();
			
			//Creo objetos para realizar el pull de informacion del backend.
			DT_VITRASP_RES a;
			
			SI_VTRASPASOS_SOProxy p = new SI_VTRASPASOS_SOProxy();
			
			int rows;
			int pages;
			int page;
			int totalrows;
			
			rows = Integer.parseInt(request.getParameter("rows"));
			page = Integer.parseInt(request.getParameter("page"));
			
			try {
				//Realizo la consulta al backend
				a = p.SI_VTRASPASOS_SO(r);
				
				totalrows = a.getSTOCDET().length;
				
				//Si no dio error la consulta levanto de memoria la info correspondiente a lo que hizo update el usuario.
				List<DT_ENTRADAS_REQI_ENTRADAItem> list = null;
				boolean edit = session.getAttribute("posiciones") != null ; 
				if (edit) {
					list = (List<DT_ENTRADAS_REQI_ENTRADAItem>) session.getAttribute("posiciones");
				}
				
				//empiezo el paginado
				if(totalrows > 0) {
					int i = 0;
					int contador = ((page - 1) * rows) ; 
					
					while( contador < totalrows && contador < (page * rows)) {
						arrResp.add(a.getSTOCDET()[contador]);
						//si se edito esto en algun momento levanto los valores editados y los mando al frontend
						if(edit) {
							int result = Iterables.indexOf(list, this.filterPos(a.getSTOCDET()[contador].getEBELP()));
							if(result != -1) {
								//log(String.valueOf(result));
								arrResp.get(i).setiCantUpdate(Integer.parseInt(list.get(result).getZMENG()));
							}
						}
						contador++;
						i++;
					}  
					jres.addProperty("total", (int) Math.ceil((double)totalrows / rows));
					jres.addProperty("page", page);
					jres.addProperty("records", totalrows);
					jres.add("rows", gson.toJsonTree(arrResp));
				}else {
					// si no hay rows..... 
					jres.addProperty("total", 0);
					jres.addProperty("page", 0);
					jres.addProperty("records", 0);
				}
	        	response.getWriter().append(jres.toString());							
				
			}catch (MT_FAULT e) {
				//si dio error...
				jres.addProperty("total", 0);
				jres.addProperty("page", 0);
				jres.addProperty("records", 0);
				response.getWriter().append(jres.toString());
				
			}
		}else {
			JsonObject jres = new JsonObject();

			jres.addProperty("total", 0);
			jres.addProperty("page", 0);
			jres.addProperty("records", 0);
			response.getWriter().append(jres.toString());
		}
	}

	public Predicate<DT_ENTRADAS_REQI_ENTRADAItem> filterPos(final String val){
		return new Predicate<DT_ENTRADAS_REQI_ENTRADAItem>() {
			@Override
			public boolean apply(DT_ENTRADAS_REQI_ENTRADAItem arg0) {
				// TODO Auto-generated method stub
				return arg0.getEBELP().equals(val);
			}
			
		};
		
	}
	
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
