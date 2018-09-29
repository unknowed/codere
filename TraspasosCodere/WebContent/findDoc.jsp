<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<!-- JS de JQGrid -->
	<script src="js/Grid3/js/jquery.jqgrid.src.js"></script>
	<!-- CSS para la JQGrid -->
	<link href="js/Grid3/css/ui.jqgrid.css" rel="stylesheet">
	<!-- JS de JQGrid ML-->
	<script src="js/Grid3/js/i18n/grid.locale-es.js"></script>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Busqueda por Material</title>
	<style>
		div.growlUI {background: url(images/check48.png) no-repeat 10px 10px}
		div.growlUI h1, div.growlUI h2 {
		    color: white; padding: 5px 5px 5px 75px; text-align: left
		}
		.ui-jqgrid .ui-widget-header {
		    background-color: #4CAF50;
		    background-image: none;
		    color: #ffffff;
		}
		.ui-jqgrid .ui-jqgrid-labels th.ui-th-column {
		    background-color:  #ecf9ec;
		    background-image: none;
		}
	</style>
</head>
<body>
<table class="header2">
	<tr>
		<td width="100px">
			<label id="lblusuario" class="caption">Usuario: </label>
		</td>
		<td colspan="2">
			<label id="lblusuarioValue"><%= session.getAttribute("usuario") %></label>		
		</td>
	</tr>
	<tr>
		<td>
			<label id="lblCentro" class="caption">Centro: </label>		
		</td>
		<td colspan="2">
			<label id="lblCentroValue"><%= session.getAttribute("werks") %></label>		
		</td>
	</tr>	
	<tr>
		<td>
			<label id="lblAlmacen" class="caption">Almacen: </label>		
		</td>
<!-- 		<td colspan="2"> -->
<%-- 			<label id="lblAlmacenValue"><%= session.getAttribute("lgort") + " - " +  session.getAttribute("descripcion") %></label>		 --%>
<!-- 		</td> -->
		<td colspan="2">
		<input type="hidden" id="hdSelectSala" name="hdSelectSala" value="<%= session.getAttribute("lgort")%>"/>
			<select id="selectsalasDoc" name="selectsalasDoc" data-prev-selected="1" >
			</select>
		</td>
	</tr>
	<tr>
		<td colspan="2">
			<button id="buscar">Buscar</button>
		<!-- 
			<label id="lblMaterial" class="caption">Material de </label>		
			<input type="text" name="matl" id="matl">
			<label id="lblAlmacenValue" class="caption"> a </label>		
			<input type="text" name="math" id="math">
			<button id="buscar">Buscar</button>
			-->
		</td>
	
	</tr>
</table>

<br>

    <table id="master"></table>
    <div id="pager"></div>

    <br /><br />

    <div id="detailsPlaceholder">
        <table id="detaill"></table>
        <div id="pager_d"></div>
    </div>
	<div id="errord" class="ui-widget relative">
		<div class="ui-state-highlight ui-corner-all" style="padding: 0 .7em;">
			<p id="textoerrord"><span class="ui-icon ui-icon-info" style="float: left; margin-right: .3em;"></span>
			<strong>Error:</strong> </p>
		</div>
	</div>
	<button id="enviar">Guardar</button>


<!-- Dialogo para preguntar si quiere ignorar los cambios -->
	<div id="dialogChangeDocument" title="Deshacer los cambios" align="center">
		<p id="txtDeshacerCambio"></p>
	</div>
	
	
<!-- Dialogo para obtener texto cabecera obligatorio -->
	<div id="dialogmensaje" title="Escriba un mensaje" align="center">
	<fieldset>
		<form id="frmmensaje" method="post" action="SendDocuments">
			<textarea id="textCabecera" value="" class="ui-widget ui-state-default ui-corner-all" name="textCabecera"></textarea>
		</form>		
	</fieldset>
	</div>
	
	
	<!-- notificaciones.... -->
	<div id="notificacion" class="growlUI"><h1>Notifico!</h1></div>
	
	
	<!-- Dialogo de Busqueda -->
	<div id="busqueda">
		<div id="busquedagroup">
			<table class="header2">
				<tr>
					<td>
						<label for="rdmaterial">Material</label>
						<input type="radio" name="busqueda" id="rdmaterial" checked="checked">
						<label for="rddocumento">Documento</label>
						<input type="radio" name="busqueda" id="rddocumento">
					</td>
				</tr>
				<tr>
					<td>De:&nbsp;
						<input type="text" name="low" id="low" class="ui-widget ui-widget-content ui-corner-all">
						&nbsp;a&nbsp;
						<input type="text" name="high" id="high" class = "ui-widget ui-widget-content ui-corner-all">
					</td>
				</tr>
			</table>			
		</div>
	</div>
	
<script>
	///////////////////////////////////////////////////////
	// Preparo los objetos
	//////////////////////////////////////////////////////
	
	//mantengo la ultima celda seleccionada y si se edito algo o no
	var editCount = 0;
	var lastSel;
	var edit = false;
	var docprevio;
	var lastRowNum=10;// = $("#master").getGridParam("rowNum");
	
	//preparlo los controles del control group
	$("#busquedagroup").controlgroup();
	
	//Ocultar div de busqueda
	$("#busqueda").css("display","none");
	//Ocultar div de comentarios
	$("#dialogmensaje").css("display","none");
	//Oculto el div de notificacioens
	$('#notificacion').css("display","none");
	//Oculto el div de errores
	$("#errord").css("display","none");
	
	//Boton de buscar
	
	$("#buscar").button({
			icon: "ui-icon-search whiteIcon",
			showLabel: true
	});
//	$("#buscar").css("height","26px");
//	$("#buscar").css("align","center");

	//Boton de enviar documento
	$("#enviar").button({
		icon: "ui-icon-disk whiteIcon",
		showLabel: true,
		disabled: true
	});
	
	
	//****************************************************
	//Grid Master
	//****************************************************
	
	jQuery("#master").jqGrid({
		width: "400px",
		url:"RetrieveTraspasos?tyb=P",
		datatype: "json",
	   	colNames:["Documento","Fecha"],
	   	colModel:[
	   		{name:'MBLNR',index:'MBLNR',key: true, width:100, sortable: false},
	   		{name:'EINDT',index:'EINDT', width:120, sortable: false}		
	   	],
	   	rowNum:10,
	   	rownumbers: true,
	   	rownumWidth: 25,
	   	rowList:[10,20,30],
	   	pager: '#pager',
	   	hidegrid:false,
	   	sortname: 'id',
	    viewrecords: true,
	    sortorder: "desc",
	    jsonReader: {
	    	root: "rows",
	    	page: "page",
	    	total: "total",
	    	records: "records",
	    	id: "0",
	    	repeatitems: false
	    },
		multiselect: false,
		onSortCol : clearSelection,
		//onPaging : clearSelection,
		caption: "Documentos de Traspasos",
	    loadComplete: function(data){
	    	if($("#master").getGridParam("records") == 0){
	    		$("#textoerrord").html("<span class='ui-icon ui-icon-info' style='float: left; margin-right: .3em;'></span>" + data.mensaje);
	    		$("#errord").css("display","inline");
	    	}else{
	    		$("#errord").css("display","none");
	    	}
	    },
		beforeSelectRow: function(rowid,e){
			if(edit){
				ShowModalYesNoGrid("Deshacer Cambios?", "Desea deshacer los cambios?",rowid);				
			}
			return !edit;
		},
		onSelectRow: function(ids) {
			
			//alert(lastRowNum);
			$('#detaill').jqGrid('clearGridData');
			if(ids == null) {
				ids=0;
				if(jQuery("#detaill").jqGrid('getGridParam','records') >0 )
				{
					jQuery("#detaill").jqGrid('setGridParam',{url:"RetrieveTraspasosD?doc="+ids});
					jQuery("#detaill").jqGrid('setCaption',"Detalle Documento: "+ids)
					.trigger('reloadGrid');
				}
			} else {
				jQuery("#detaill").jqGrid('setGridParam',{url:"RetrieveTraspasosD?doc="+ids});
				jQuery("#detaill").jqGrid('setCaption',"Detalle documento: "+ids)
				.trigger('reloadGrid');			
			}
		}
		,onPaging: function(pgButton) {
// 			//$(this).onchange(function(){
// 				//alert($(this).html());
// // 				var selectRowNum=$("#select [class='ui-widget-content ui-pg-selbox']");
// // 				alert(selectRowNum.html());
// 				$dataFiled=$("#pager_center");
// 				alert($dataFiled.html());
// // 				$field = $dataFiled.find("select option:selected");
// // 				alert($field.html());
// 				var str="select";
// 				alert(str);
// 				$field = $dataFiled.find(str);//.find("[value="+lastRowNum+"]");
// 				$field.val(lastRowNum);
// 				alert($field.html());
// 			//	});
// 		   // return 'stop';
 		  //alert(pgButton);
		  
			if (edit){
				ShowModalYesNoRefreshRowNumChange("Deshacer Cambios?", "Desea deshacer los cambios?");
				//return [false,""];
			}else{
				$("#detaill").jqGrid("clearGridData");
				//lastRowNum = $("#master").getGridParam("rowNum");
				lastRowNum=$('.ui-pg-selbox').val();
				//if (pgButton == 'records') {
				         //   alert('records'+$('.ui-pg-selbox').val());
				      //  }
			}
			//lastRowNum=$("#master").getGridParam('rowNum');
			return !edit;
			//return 'stop';
		}	
	});
	jQuery("#master").jqGrid('navGrid','#pager',{add:false,edit:false,del:false,search: false,
		beforeRefresh: function() {
			if (edit){
				ShowModalYesNoRefresh("Deshacer Cambios?", "Desea deshacer los cambios?");
			}else{
				$("#detaill").jqGrid("clearGridData");
			}
			return !edit;
		}	
	});
	
	//*******************************************************
	// Grid Detaill
	//*******************************************************
	
	var myEditOptions = {
			aftersavefunc: function(rowid, response, options){
				alert(rowid);				
			}
	};
	
	
	$('#detaill').jqGrid('clearGridData');
	jQuery("#detaill").jqGrid({
	   	url:'RetrieveTraspasosD?doc=0',
		datatype: "json",
	   	colNames:["iId","Nro Doc","Doc Compra","Posicion","Nro Material", "Descripcion","Cantidad","UM","Cantidad Recibida"],
	   	colModel:[
	   		{name:'iId',index:'iId', editable: "hidden", width:80, sortable: false, hidden: true, key:true},
	   		{name:'MBLNR',index:'MBLNR', editable: "hidden", width:80, sortable: false},
	   		{name:'EBELN',index:'EBELN', editable: "hidden", width:95, sortable: false},
	   		{name:'EBELP',index:'EBELP', width:50, sortable: false, editable:"hidden"},
	   		{name:'MATNR',index:'MATNR', editable: "hidden", width:80, sortable: false},
	   		{name:'MAKTX',index:'MAKTX', width:250, sortable: false},
	   		{name:'MENGE',index:'MENGE', width:50, sortable: false},
	   		{name:'MEINS',index:'MEINS', width:50, sortable: false},
	   		{name:"iCantUpdate", index:"iCantUpdate", formatter: removeCero, sortable: false, width: 100, editable: true,  editoptions:{
	   				size: 15,
	   				maxlenght: 10,
	   				dataInit: function (element){
	   					$(element).keypress(function(e){
	                         if (e.which != 8 && e.which != 0 && (e.which < 48 || e.which > 57)) {
	                             return false;
	                         }				
	   					});
	   				},
	   		},editrules: {
	   			custom: true,
	   			custom_func: bValMaxValor
	   		}}
	   	],
	   	rowNum:10,
	    jsonReader: {
	    	root: "rows",
	    	page: "page",
	    	total: "total",
	    	records: "records",
	    	id: "0",
	    	repeatitems: false
	    },
	    viewrecords:true,
	    hidegrid:false,
	   	rowList:[10,20,30],
	   	pager: '#pager_d',
	   	multiselect:false,
	   	sortname: 'Posicion',
	    viewrecords: true,
	    sortorder: "asc",
	    editurl:"UpdatePosicion",
	    caption:"Detalle Documento",
	   	onSelectRow: function(id){
	   		if (id && id != lastSel){
	   			$("#detaill").restoreRow(lastSel);
	   			lastSel=id;
	   		}
	   		$("#detaill").editRow(id,true,null,editado);
	   	}
	}).navGrid('#pager_d',{add:false,edit:false,del:false, search:false, refresh:false});
	
	//***************************************************
	//Dialog Deshacer cambios Grid
	//***************************************************
	
	function ShowModalYesNoGrid (title, message, rowid){
		var def;
		$("#txtDeshacerCambio").html(message);
		
		$("#dialogChangeDocument").dialog({
			modal: true,
			title: title,
			autoOpen: true,
			dialogClass: "myTitleClass",
			buttons: {
				Si: function() {
					$("#master").setSelection(rowid,true);
					$.ajax({
						type: "POST",
						url: "ClearPosiciones"
					});
					edit=false;
					lastSel = null;
					editCount = 0;
					$("#enviar").button("disable");
					$("#txtDeshacerCambio").html("");
					$(this).dialog("close");
				},
				No: function() {
					$("#txtDeshacerCambio").html("");
					$(this).dialog("close");
				}
			}
		});
		
	}
	
	//***************************************************
	//Dialog Deshacer cambios Buscar
	//***************************************************
	
	function ShowModalYesNo (title, message){
		var def;
		$("#txtDeshacerCambio").html(message);
		
		$("#dialogChangeDocument").dialog({
			modal: true,
			title: title,
			autoOpen: true,
			dialogClass: "myTitleClass",
			buttons: {
				Si: function() {
					edit=false;
					$.ajax({
						type: "POST",
						url: "ClearPosiciones",
						complete: function (){
							$("#detaill").trigger("reloadGrid");
						}
					});
					//jQuery("#detaill").jqGrid("clearGridData");
					
					//jQuery("#master").jqGrid('setGridParam',{url:"RetrieveTraspasos?tyb=P" ,page:1})
					//.trigger('reloadGrid');				
					
					lastSel = null;
					editCount = 0;
					$("#enviar").button("disable");
					$("#txtDeshacerCambio").html("");
					$("#busqueda").dialog("open");
					$(this).dialog("close");

				},
				No: function() {
					$("#txtDeshacerCambio").html("");
					$("#buscar").button("enable");
					$(this).dialog("close");					
				}
			}
		});
		
	}
	
	
	
	
	//***************************************************
	//Dialog Deshacer cambios Refresh
	//***************************************************
	
	function ShowModalYesNoRefresh (title, message){
		var def;
		$("#txtDeshacerCambio").html(message);
		
		$("#dialogChangeDocument").dialog({
			modal: true,
			title: title,
			autoOpen: true,
			dialogClass: "myTitleClass",
			buttons: {
				Si: function() {
					edit=false;
					$.ajax({
						type: "POST",
						url: "ClearPosiciones",
						complete: function (){
							$("#master").trigger("reloadGrid");
							$("#detaill").jqGrid("clearGridData");
						}
					});
					//jQuery("#detaill").jqGrid("clearGridData");
					
					//jQuery("#master").jqGrid('setGridParam',{url:"RetrieveTraspasos?tyb=P" ,page:1})
					//.trigger('reloadGrid');				
					
					lastSel = null;
					editCount = 0;
					$("#enviar").button("disable");
					$("#txtDeshacerCambio").html("");
					$(this).dialog("close");

				},
				No: function() {
					$("#txtDeshacerCambio").html("");
					$(this).dialog("close");
				}
			}
		});
		
	}
	

	//////////////////////////////////////////////////////////////
	// Dialogo de Busqueda
	/////////////////////////////////////////////////////////////
	
	$("#busqueda").dialog({
		width: 500,
		modal: false,
		title: "Buscar",
		autoOpen: false,
		dialogClass: "myTitleClass",
		buttons: [
			{
				text: "Buscar",
				click: function(){
					jQuery("#detaill").jqGrid("clearGridData");
					jQuery("#master").jqGrid('setGridParam',{url:"RetrieveTraspasos" + getQueryString() ,page:1})
					.trigger('reloadGrid');			
					$("#buscar").button("enable");	
					$(this).dialog("close");
				}
			},
			{
				text:"Reiniciar",
				click: function () {
					$("#low").val("");
					$("#high").val("");
					jQuery("#detaill").jqGrid("clearGridData");
					jQuery("#master").jqGrid('setGridParam',{url:"RetrieveTraspasos?tyb=P",page:1})
					.trigger('reloadGrid');
					$("#buscar").button("enable");
					$(this).dialog("close");					
				}
			},
			{
				text:"Cancelar",
				click: function (){
					$("#low").val("");
					$("#high").val("");
					$("#buscar").button("enable");
					$(this).dialog("close");
				}
				
			}
			
		]
	});
	
	
	//funcion de editado
	function editado(data,rowid){
		$("#enviar").button("enable");
		var r = jQuery.parseJSON(data.responseText);
		edit = r.success;
		var currentValue = $("#detaill").getGridParam('rowNum');
		if(currentValue-1 > rowid){
			rowid++
			$("#detaill").setSelection(rowid,true);
//			$("#detaill").editRow(rowid,true,null,editado);
		}
		return [true,""];
	}
	
	//formatter para remover 0s
	function removeCero (cellvalue, options, rowObject)
	{
	   // do something here
	   if (cellvalue != "" && cellvalue != null){
		   return Number(cellvalue);   
	   }else{
		   return "";
	   }
	   
	}
	
	//boton de busqueda
	$("#buscar").click(function(){
		if(edit){
			ShowModalYesNo("Deshacer Cambios?", "Desea deshacer los cambios?");
		}else{
			$("#busqueda").dialog("open");
		}
		$("#buscar").button("disable");	
	});
	
	//boton enviar al backend 
	$("#enviar").click(function(){
		$("#textCabecera").val("");
		$("#dialogmensaje").dialog("open");
	});

	///////////////////////////////////////////////////
	// Dialogo de captura de mensaje
	//////////////////////////////////////////////////
	
	$("#dialogmensaje").dialog({
		modal: true,
		title: "Guardar Datos",
		autoOpen: false,
		dialogClass: "myTitleClass",
		buttons: [
			{
				text: "Guardar",
				click: function() {
					if($("#textCabecera").val() == ""){
						alert("Debe completar un texto Cabecera.");
					}else{
						$.blockUI({
							message: "<h1 class='mensajeblock'><img src='images/ajax-loader.gif' /> Guardando...</h1>"
						});
						var frmm = $("#frmmensaje");  
						$.ajax({
				            type: frmm.attr("method"),
				            url: frmm.attr("action"),
				            data: frmm.serialize(),
				            success: function (data) {
								$.unblockUI();
								var r = jQuery.parseJSON(data);
								jQuery("#detaill").jqGrid("clearGridData");
								jQuery("#master").jqGrid('setGridParam',{url:"RetrieveTraspasos?tyb=P",page:1})
								.trigger('reloadGrid');				
								$("#notificacion").html("<h1>" + r.message +"</h1>");
								
								if(r.success){
							        $.blockUI({ 
							            message: $('div.growlUI'), 
							            fadeIn: 700, 
							            fadeOut: 3000, 
							            timeout: 3000, 
							            showOverlay: false, 
							            centerY: false,
							            css: { 
							            	align: 'center',
							                width: '350px', 
							                top: '40%',
							                left: '35%', 
							                border: 'none', 
							                padding: '5px', 
							                backgroundColor: '#4CAF50', 
							                '-webkit-border-radius': '10px', 
							                '-moz-border-radius': '10px', 
							                opacity: .6, 
							                
							                color: '#fff' 
							            } 
							        });
								}else{
							        $.blockUI({ 
							            message: $('div.growlUI'), 
							            fadeIn: 700, 
							            fadeOut: 3000, 
							            timeout: 3000, 
							            showOverlay: false, 
							            centerY: false,
							            css: { 
							            	
							            	align: 'center',
							                width: '350px', 
							                top: '40%',
							                left: '35%', 
							                border: 'none', 
							                padding: '5px', 
							                backgroundColor: '#ffcccc', 
							                '-webkit-border-radius': '10px', 
							                '-moz-border-radius': '10px', 
							                opacity: .6, 
							                color: '#fff' 
							            } 
							        });
									
								}
								edit=false;
								lastSel = null;
								editCount = 0;
								//$.growlUI('Traspasos Codere', r.message);
				            }
						})
						$(this).dialog("close");
					}
					
				}
	
			},
			{
				text: "Cancelar",
				click: function() {
					$(this).dialog("close");
				}
				
			}
		]
	});
	
	
	
	//////////////////////////////////////////////////
	// Funciones 
	/////////////////////////////////////////////////

	function clearSelection() {
		jQuery("#detaill").jqGrid("clearGridData");
				
	}
	
	function bValMaxValor (value,colname){
		var values;
		var row;
		values = $("#detaill").jqGrid("getRowData", lastSel);
		if((Number(value) <= Number(values.MENGE) && Number(value) > 0) || value == ""){
			return [true,""];
		}else{
			return [false,"No puede recibir mas items que los especificados en el documento. Y debe ser mayor a 0"];
		}
		
	}
	
	
	function getQueryString(){
		var strRetorno = "";
		var strB = "D";
		//if($("#rdMaterial").prop("checked") == "checked"){
			
		//}
		if($("#rdmaterial").prop("checked")){
			strB = "P";			
		}
		strRetorno = "?tyb=" + strB + "&low=" + $("#low").val() + "&high=" + $("#high").val();
		
		return strRetorno;		
	}
	

/*
 * Ajustes para agregar combo de salas a la pantalla Home
 *
 */
 // funcion ready para cargar el combo al iniciar findDoc.jsp
 $(document).ready(function(){
		GetSalas('post','SalasServlet');
		//$("#hdSelectSala").val($("#selectsalasDoc").val());
 });
 
 // Funcion para cargar el select de Salas
function GetSalas(method, action){
	var frm = $("#login");
	//alert(userval);
	//mando todo al backend
	$.ajax({
        type:method,
        url: action,
        data: frm.serialize(),
        success: function (data) {
        	var r = jQuery.parseJSON(data);
			//si hay data y no error...
    		if(r.success){
    			//alert( $("#hdSelectSala").val());
    			//creo los options para el combobox
        		var options = [];	
        		for(i=0; i<r.result.length; i++){
        			var arr = r.result[i].LGORT.split("-");
        			if($("#hdSelectSala").val()===arr[0].trim())
        				options.push("<option selected value='" + arr[0].trim() + "/" + r.result[i].WERKS + "/" + arr[1].trim() +"'>" + arr[1].trim() + "</option>");
        			else
        				options.push("<option value='" + arr[0].trim() + "/" + r.result[i].WERKS + "/" + arr[1].trim() +"'>" + arr[1].trim() + "</option>");
        		}
        		
        		//los agrego al select y lo refresco en jquery para que los muestre en el control
        		$("#selectsalasDoc").empty();
        		$("#selectsalasDoc").append(options.join(""));
         		$("#selectsalasDoc").selectmenu("refresh");
         		//$("#selectsalasDoc").css( "display", "block" );
        		//$("#dialog").dialog("open");
         		$("#hdSelectSala").val($("#selectsalasDoc").val());
        	}else{
        		//en caso que de error aca cacheo los errores y muestro un mensaje apropiado.
        		switch(parseInt(r.type)){
        		case 1: //Sin datos el usuario no existe.
                	$("#textoerror").html("<span class='ui-icon ui-icon-alert' style='float: left; margin-right: .3em;'></span><strong>Error:</strong> El usuario <strong>" + r.result.i_USUARIO + "</strong> no existe.");
                	break;
				default:
                	$("#textoerror").html("<span class='ui-icon ui-icon-alert' style='float: left; margin-right: .3em;'></span><strong>Error:</strong> Error en SAP por favor contacte a su Administrador de Redes");
        		}
            	$("#error").css("display","inline");	
        	}
			
        },
        error: function (jqXHR, textStatus, message) {
			alert("error");
			alert(jqXHR.responseText);
			alert(textStatus);
			alert(message);
			
        } ,
        complete: function(){
        	$.unblockUI();
        }
    });
}
 
 

$("#selectsalasDoc" ).selectmenu({
	  change: function( event, ui ) {
		  
// 		  var someCondition = false; // hardcoded for now
          
//           // Undo new selection
//           if ( someCondition ) {
//         	 //alert(someCondition);
//               var prevIdx = $('select#selectsalasDoc').attr('data-prev-selected');
//               //alert(prevIdx);
//              // $('select#selectsalasDoc').selectmenu("index", prevIdx);
             
// 				$('#selectsalasDoc').val(prevIdx);
// 				$('#selectsalasDoc').selectmenu('refresh');
//               return;
//           }
          
//           // Save new index
//          // var idx = $('select#selectsalasDoc').selectmenu("index");
//           var idx= $('#selectsalasDoc').val();
//           $('select#selectsalasDoc').data('data-prev-selected', idx);
		  
		  //alert($("#hdSelectSala").val());
		  var selectsalasDocAnterior=$("#hdSelectSala").val();
		  var hdnusuario=$("#lblusuarioValue").text();
			var selectsalasDoc= ui.item.value;
			//alert(hdnusuario);
			//alert(selectsalasDoc);
			// $("#hdSelectSala").val(selectsalasDoc);
			// alert( $("#hdSelectSala").val());
			//$("#master").trigger("reloadGrid");
			if (edit){
				ShowModalYesNoRefreshSalas("Deshacer Cambios?", "Desea deshacer los cambios?",hdnusuario,selectsalasDoc,selectsalasDocAnterior);
			}else{		
				$.ajax({
					  type: "POST",
					  url: "sessionManager",
					  data: {hdnusuario:hdnusuario,selectsalas:selectsalasDoc},
					  success: function (data) {
						  jQuery("#detaill").jqGrid("clearGridData");
						  $("#master").trigger("reloadGrid");
						  console.log('success');
					  },
					  error: function (error) {
						  console.log('error');
					  }
					});
			}
			//return [false,""];
			
			var someCondition = true; // hardcoded for now
			// Undo new selection
			if ( someCondition ) {
			    var prevIdx = $('selectsalasDoc').attr('data-prev-selected');
			    $('selectsalasDoc').selectmenu("index", prevIdx);
			    return;
			}

			// Save new index
			var idx = $('selectsalasDoc').selectmenu("index");
			$('selectsalasDoc').data('data-prev-selected', idx);
	  }
// 	  ,
// 	  select: function( event, ui ) {
// 		  return [false,''];
// 		  alert('select event'+ui.item.value);
// 	  }
});
 

//***************************************************
//Dialog Deshacer cambios Refresh combo salas
//***************************************************

function ShowModalYesNoRefreshSalas (title, message,hdnusuario,selectsalasDoc,selectsalasDocAnterior){
	var def;
	$("#txtDeshacerCambio").html(message);
	
	$("#dialogChangeDocument").dialog({
		modal: true,
		title: title,
		autoOpen: true,
		dialogClass: "myTitleClass",
		buttons: {
			Si: function() {
				edit=false;
				$.ajax({
					type: "POST",
					url: "ClearPosiciones",
					complete: function (){
						$("#master").trigger("reloadGrid");
						$("#detaill").jqGrid("clearGridData");
					}
				});
				//jQuery("#detaill").jqGrid("clearGridData");
				
				//jQuery("#master").jqGrid('setGridParam',{url:"RetrieveTraspasos?tyb=P" ,page:1})
				//.trigger('reloadGrid');				
				
				lastSel = null;
				editCount = 0;
				$("#enviar").button("disable");
				$("#txtDeshacerCambio").html("");
				$(this).dialog("close");
				
				// despues de descartar los cambios cargar el combo
				$.ajax({
					  type: "POST",
					  url: "sessionManager",
					  data: {hdnusuario:hdnusuario,selectsalas:selectsalasDoc},
					  success: function (data) {
						  jQuery("#detaill").jqGrid("clearGridData");
						  $("#master").trigger("reloadGrid");
						  console.log('success');
					  },
					  error: function (error) {
						  console.log('error');
					  }
					});
				
				// guardo el index selecionado
				 var idx= $('#selectsalasDoc').val();
		          $('select#selectsalasDoc').data('data-prev-selected', idx);
		          $("#hdSelectSala").val(selectsalasDoc);

			},
			No: function() {
				$("#txtDeshacerCambio").html("");
				$(this).dialog("close");	
// 				$("#selectsalasDoc").val(selectsalasDocAnterior);
// 				//$( "#selectsalasDoc" ).selectmenu( selectsalasDocAnterior, "selected", true );
// 				$('#selectsalasDoc > option[value="'+selectsalasDocAnterior+'"]').attr('selected', 'selected');
				 var prevIdx = $('select#selectsalasDoc').attr('data-prev-selected');
	              //alert(prevIdx);
	             // $('select#selectsalasDoc').selectmenu("index", prevIdx);
	             
					//$('#selectsalasDoc').val(prevIdx);
					$('#selectsalasDoc').val(selectsalasDocAnterior);
					$('#selectsalasDoc').selectmenu('refresh');
					 $("#hdSelectSala").val(selectsalasDocAnterior);
	              return;
			}
		}
	});
	
}

 
//***************************************************
//Dialog Deshacer cambios Refresh al cambiar numero de reglonde por pagina
//***************************************************

function ShowModalYesNoRefreshRowNumChange (title, message){
	var def;
	$("#txtDeshacerCambio").html(message);
	
	$("#dialogChangeDocument").dialog({
		modal: true,
		title: title,
		autoOpen: true,
		dialogClass: "myTitleClass",
		buttons: {
		    Si: function() {
				edit=false;
				$.ajax({
					type: "POST",
					url: "ClearPosiciones",
					complete: function (){
						$("#master").trigger("reloadGrid");
						$("#detaill").jqGrid("clearGridData");
					}
				});
				//jQuery("#detaill").jqGrid("clearGridData");
				
				//jQuery("#master").jqGrid('setGridParam',{url:"RetrieveTraspasos?tyb=P" ,page:1})
				//.trigger('reloadGrid');				
				
				lastSel = null;
				editCount = 0;
				$("#enviar").button("disable");
				$("#txtDeshacerCambio").html("");
				$(this).dialog("close");
				
 				//lastRowNum=$("#master").getGridParam('rowNum');
 				//alert(lastRowNum);
 				lastRowNum=$('.ui-pg-selbox').val();
 				//if (pgButton == 'records') {
 				         //   alert('records'+$('.ui-pg-selbox').val());
 				    //    }

			},
			No: function() {
				$("#txtDeshacerCambio").html("");
				$(this).dialog("close");
				//lastRowNum
				//$("#master").setGridParam(rowNum,lastRowNum);
				jQuery("#master").setGridParam({rowNum:lastRowNum});//.trigger("reloadGrid");
				//alert(lastRowNum);
				// ui-widget-content ui-pg-selbox
				//var selectRowNum=$('.ui-widget-content ui-pg-selbox');
				//alert(selectRowNum.html());
// 				var paper_id=$('#pager_center');
// 				alert(paper_id.html());
// 				jQuery("#master").jqGrid('setGridParam',{rowNum:lastRowNum}).trigger("reloadGrid");
// 				alert(lastRowNum);
				
				$dataFiled=$("#pager_center");
				//alert($dataFiled.html());
// 				$field = $dataFiled.find("select option:selected");
// 				alert($field.html());
				var str="select";
				//alert(str);
				$field = $dataFiled.find(str);//.find("[value="+lastRowNum+"]");
				$field.val(lastRowNum);
				//alert($field.html());
				
				return 'stop';
			}
		}
	});
	
}

 
 
</script>
</body>
</html>
