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

import com.google.gson.Gson;
import com.google.gson.JsonObject;

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

		log("D");
		HttpSession session = request.getSession();
		

		if( !request.getParameter("doc").equals("0")) {
			DT_VITRASP_REQ r = new DT_VITRASP_REQ();
			r.setTYBUS("P");
			r.setWERKS(session.getAttribute("werks").toString());
			r.setLGORT(session.getAttribute("lgort").toString());
			r.setDOCMATH("");
			r.setMATNRH("");
			r.setMATNRL("");
			r.setTYCON("D");
			r.setDOCMATL(request.getParameter("doc").toString());			

			
			List<DT_VITRASP_RESSTOCDETItem> arrResp = new ArrayList<DT_VITRASP_RESSTOCDETItem>();
			
			// JSon Objects.
			Gson gson = new Gson();
			JsonObject jres = new JsonObject();
			
			DT_VITRASP_RES a;
			
			SI_VTRASPASOS_SOProxy p = new SI_VTRASPASOS_SOProxy();
			
			int rows;
			int pages;
			int page;
			int totalrows;
			
			rows = Integer.parseInt(request.getParameter("rows"));
			page = Integer.parseInt(request.getParameter("page"));
			
			try {
				a = p.SI_VTRASPASOS_SO(r);
				
				totalrows = a.getSTOCDET().length;

				if(totalrows > 0) {
					int i = 0;
					int contador = ((page - 1) * rows) ; 
					
					while( contador < totalrows && contador < (page * rows)) {
						arrResp.add(a.getSTOCDET()[contador]);
						contador++;
						i++;
					}  
					log(String.valueOf(totalrows));
					log(String.valueOf(rows));
					log(String.valueOf((int) Math.ceil((double)totalrows / rows)));
					jres.addProperty("total", (int) Math.ceil((double)totalrows / rows));
					jres.addProperty("page", page);
					jres.addProperty("records", totalrows);
					jres.add("rows", gson.toJsonTree(arrResp));
					
					
					
				}else {
					jres.addProperty("total", 0);
					jres.addProperty("page", 0);
					jres.addProperty("records", 0);
				}
				log(jres.toString());
	        	response.getWriter().append(jres.toString());							
				
			}catch (MT_FAULT e) {
				jres.addProperty("total", 0);
				jres.addProperty("page", 0);
				jres.addProperty("records", 0);
				log(jres.toString());
				response.getWriter().append(jres.toString());
				
			}
		}else {
			JsonObject jres = new JsonObject();

			jres.addProperty("total", 0);
			jres.addProperty("page", 0);
			jres.addProperty("records", 0);
			log(jres.toString());
			response.getWriter().append(jres.toString());
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
