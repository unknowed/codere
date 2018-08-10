package com.codere.traspasos.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;
import com.google.gson.JsonObject;

import entrada_cendest.sap.document.sap_com.DT_ENTRADAS_REQ;
import entrada_cendest.sap.document.sap_com.DT_ENTRADAS_REQI_ENTRADAItem;



/**
 * Servlet implementation class UpdatePosicion
 */
@WebServlet("/UpdatePosicion")
public class UpdatePosicion extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdatePosicion() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//levanto la session en el objeto
		HttpSession session = request.getSession();
		
		List<DT_ENTRADAS_REQI_ENTRADAItem> list = null;
		
		log("entro update");

		//Creo la lista o la bajo de la session
		if (session.getAttribute("posiciones") == null ) {
			list = new ArrayList<DT_ENTRADAS_REQI_ENTRADAItem>();
		}else{
			list = (List<DT_ENTRADAS_REQI_ENTRADAItem>) session.getAttribute("posiciones");
		}
		
		//Levanto todos los parametros que necesito del request.
		
		String EBELN = request.getParameter("EBELN");
		String EBELP = request.getParameter("EBELP");
		String MATNR = request.getParameter("MATNR");
		String MBLNR = request.getParameter("MBLNR");
		String iCantRecibida = request.getParameter("iCantUpdate");
		log("-------------");
		log(EBELN);
		log(EBELP);
		log(MATNR);
		log(MBLNR);
		log(iCantRecibida);
		
		int result = Iterables.indexOf(list, this.filterPos(EBELP));
		log("resultado: " + String.valueOf(result));
		if(result == -1 && iCantRecibida != "") {
			DT_ENTRADAS_REQI_ENTRADAItem item = new DT_ENTRADAS_REQI_ENTRADAItem();
			
			item.setEBELN(EBELN);
			item.setEBELP(EBELP);
			item.setMATNR(MATNR);
			item.setMBLNR(MBLNR);
			item.setZMENG(iCantRecibida.replaceFirst("^0+(?!$)", ""));
			list.add(item);
		}else {
			if (result != -1 && iCantRecibida != "") {
				list.get(result).setZMENG(iCantRecibida);
			}
			if (result != -1 && iCantRecibida == "") {
				list.remove(result);
			}
		}
		session.setAttribute("posiciones", list);
		
		//objetos JSON
		JsonObject jres = new JsonObject();
		jres.addProperty("success", true);
		response.getWriter().append(jres.toString());
/*		
		Enumeration<String> paramNames = request.getParameterNames();
		
		while(paramNames.hasMoreElements()) {
			String paramName = paramNames.nextElement();
			log("name: " + paramName);
			String[] paramValues = request.getParameterValues(paramName);
			for (int i = 0; i< paramValues.length; i ++) {
				String paramValue = paramValues[i];
				log("value" + i + ":" + paramValue);
			}
			
		}
*/		
	}

	
	//busco la posicion que quiero
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
