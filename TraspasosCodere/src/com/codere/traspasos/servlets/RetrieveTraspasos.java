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
import visual_traspaso.sap.document.sap_com.DT_VITRASP_RESSTOCCABItem;
import visual_traspaso.sap.document.sap_com.SI_VTRASPASOS_SOProxy;

/**
 * Servlet implementation class RetrieveTraspasos
 */
@WebServlet("/RetrieveTraspasos")
public class RetrieveTraspasos extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RetrieveTraspasos() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//Levanto la session
		HttpSession session = request.getSession();
		

		//Preparo los objetos para hacer el request al WS
		DT_VITRASP_REQ req = new DT_VITRASP_REQ();
		
		int rows;
		int pages;
		int page;
		int totalrows;
		
		rows = Integer.parseInt(request.getParameter("rows"));
		page = Integer.parseInt(request.getParameter("page"));
		
		//Si viene valores por documento o materiales mando busqueda sino, no.
		req.setTYBUS(request.getParameter("tyb").toString());
		req.setWERKS(session.getAttribute("werks").toString());
		req.setLGORT(session.getAttribute("lgort").toString());
		req.setTYCON("C");
//		Si busco por material entro por aca
		if(request.getParameter("tyb").equals("P")) {
			req.setDOCMATL("");
			req.setDOCMATH("");
			if(request.getParameter("low") == null) {
				req.setMATNRL("");
				req.setMATNRH("");
			}else {
				log(request.getParameter("low").toString());
				req.setMATNRL(request.getParameter("low").toString());
				req.setMATNRH(request.getParameter("high").toString());
			}
		}else {
			//Si busco por documento... va por aca
			req.setMATNRL("");
			req.setMATNRH("");
			if(request.getParameter("low") == null) {
				req.setDOCMATL("");
				req.setDOCMATH("");
			}else {
				req.setDOCMATL(request.getParameter("low").toString());
				req.setDOCMATH(request.getParameter("high").toString());
			}
		}
		
		
		DT_VITRASP_RES res;
		
		List<DT_VITRASP_RESSTOCCABItem> arrResp = new ArrayList<DT_VITRASP_RESSTOCCABItem>();
//		DT_VITRASP_RESSTOCCABItem[] arrResp = new DT_VITRASP_RESSTOCCABItem[10];
		
		SI_VTRASPASOS_SOProxy p = new SI_VTRASPASOS_SOProxy();

		// JSon Objects.
		Gson gson = new Gson();
		JsonObject jres = new JsonObject();
		
		//hago la peticion al WS pasandole los datos que recopile
		try {
			res = p.SI_VTRASPASOS_SO(req);
			

			totalrows = res.getSTOCCAB().length;

			//hago el paginado
			if(totalrows > 0) {
				int contador = ((page - 1) * rows) ; 
				
				while( contador < totalrows && contador < (page * rows)) {
					arrResp.add(res.getSTOCCAB()[contador]);
					contador++;
				}  
				jres.addProperty("total", (int) Math.ceil((double)totalrows / rows));
				jres.addProperty("page", page);
				jres.addProperty("records", totalrows);
				jres.add("rows", gson.toJsonTree(arrResp));
				
			}else {
				jres.addProperty("total", 0);
				jres.addProperty("page", 0);
				jres.addProperty("records", 0);
				jres.addProperty("mensaje", res.getRETURN()[0].getMESSAGE());
			}
				
			response.getWriter().append(jres.toString());
			
		}catch(MT_FAULT e) {
			jres.addProperty("total", 0);
			jres.addProperty("page", 0);
			jres.addProperty("records", 0);
			jres.addProperty("mensaje",e.getStandard().getFaultText());
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
