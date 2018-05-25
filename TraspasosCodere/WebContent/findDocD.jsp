<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<!-- JS de JQGrid -->
	<script src="js/Grid3/js/jquery.jqgrid.src.js"></script>
	<!-- CSS para la JQGrid -->
	<link href="js/Grid3/css/ui.jqgrid.css" rel="stylesheet">

	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Busqueda por Documento</title>
	<style>

	</style>
</head>
<body>
<table class="header2">
	<tr>
		<td width="100px">
			<label id="lblusuarioD" class="caption">Usuario: </label>
		</td>
		<td colspan="2">
			<label id="lblusuarioValueD"><%= session.getAttribute("usuario") %></label>		
		</td>
	</tr>
	<tr>
		<td>
			<label id="lblCentroD" class="caption">Centro: </label>		
		</td>
		<td colspan="2">
			<label id="lblCentroValueD"><%= session.getAttribute("werks") %></label>		
		</td>
	</tr>	
	<tr>
		<td>
			<label id="lblAlmacenD" class="caption">Almacen: </label>		
		</td>
		<td colspan="2">
			<label id="lblAlmacenValueD"><%= session.getAttribute("lgort") + " - " +  session.getAttribute("descripcion") %></label>		
		</td>
	</tr>
	<tr>
		<td colspan="2">
			<label id="lblMaterialD" class="caption">Documento de </label>		
			<input type="text" name="docl" id="docl">
			<label id="lblAlmacenValueD" class="caption"> a </label>		
			<input type="text" name="doch" id="doch">
			<button id="button-iconD">Buscar</button>
		</td>
	
	</tr>
</table>

<br>

    <table id="masterD"></table>
    <div id="pagerD"></div>

    <br /><br />

    <div id="detailsPlaceholderD">
        <table id="detaillD"></table>
        <div id="pager_dD"></div>
    </div>
	<div id="errordD" class="ui-widget relative">
		<div class="ui-state-highlight ui-corner-all" style="padding: 0 .7em;">
			<p id="textoerrordD"><span class="ui-icon ui-icon-info" style="float: left; margin-right: .3em;"></span>
			<strong>Error:</strong> </p>
		</div>
	</div>
	<button id="button-icon2D">Buscar</button>

<script>

	///////////////////////////////////////////////////////
	// Preparo los objetos
	//////////////////////////////////////////////////////
	//Oculto el div de errores
	$("#errordD").css("display","none");
	
	//Boton de buscar
	
	$("#button-iconD").button({
			icon: "ui-icon-search whiteIcon",
			showLabel: true,
	});
	$("#button-icon2D").button({
		icon: "ui-icon-search whiteIcon",
		showLabel: true,
	});
	
	$("#button-iconD").css("height","26px");
	$("#button-iconD").css("align","center");
	
	//****************************************************
	//Grid Master
	//****************************************************
	
	jQuery("#masterD").jqGrid({
		width: "400px",
		url:"RetrieveTraspasos?tyb=D",
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
	   	pager: '#pagerD',
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
	    loadComplete: function(data){
	    	if($("#masterD").getGridParam("records") == 0){
	    		$("#textoerrordD").html("<span class='ui-icon ui-icon-info' style='float: left; margin-right: .3em;'></span>No hay datos para mostrar");
	    		$("#errordD").css("display","inline");
	    	}else{
	    		$("errordD").css("display","none");
	    	}
	    },
		onPaging : clearSelection,
		caption: "Documentos de Traspasos",
		onSelectRow: function(ids) {
			$('#detaillD').jqGrid('clearGridData');
			if(ids == null) {
				ids=0;
				if(jQuery("#detaillD").jqGrid('getGridParam','records') >0 )
				{
					jQuery("#detaillD").jqGrid('setGridParam',{url:"RetrieveTraspasosD?doc="+ids});
					jQuery("#detaillD").jqGrid('setCaption',"Detalle Documento: "+ids)
					.trigger('reloadGrid');
				}
			} else {
				jQuery("#detaillD").jqGrid('setGridParam',{url:"RetrieveTraspasosD?doc="+ids});
				jQuery("#detaillD").jqGrid('setCaption',"Detalle documento: "+ids)
				.trigger('reloadGrid');			
			}
		}
	});
	jQuery("#masterD").jqGrid('navGrid','#pagerD',{add:false,edit:false,del:false,search: false});
	
	$('#detaillD').jqGrid('clearGridData');
	jQuery("#detaillD").jqGrid({
	   	url:'RetrieveTraspasosD?doc=0',
		datatype: "json",
	   	colNames:["Nro Doc","Doc Compra","Posicion","Nro Material", "Descripcion","Cantidad","UM","Cantidad Recibida"],
	   	colModel:[
	   		{name:'MBLNR',index:'MBLNR',  width:80},
	   		{name:'EBELN',index:'EBELN', width:95},
	   		{name:'EBELP',index:'EBELP',key: true, width:50},
	   		{name:'MATNR',index:'MATNR', width:80},
	   		{name:'MAKTX',index:'MAKTX', width:250},
	   		{name:'MENGE',index:'MENGE', width:50},
	   		{name:'MEINS',index:'MEINS', width:50},
	   		{name:"ZMENG", index:"ZMENG", width: 100, editable: true}
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
	    hidegrid:false,
	   	rowList:[10,20,30],
	   	pager: '#pager_dD',
	   	multiselect:true,
	   	sortname: 'Posicion',
	   	onSelectRow: function(id){
	   		alert(id);
	   	  $('#detaillD').jqGrid('saveRow',lastsel,false,'clientArray');
	   	  
	   	  if(id && id!==lastsel){
	   	    $('#detaillD').jqGrid('restoreRow',lastsel);
	   	    lastsel=id;
	   	  }
	   	  $('#detaillD').jqGrid('editRow',id,false);
	   	},
	    viewrecords: true,
	    sortorder: "asc",
	    editurl:"sarasa.jsp",
	    caption:"Detalle Documento"
	}).navGrid('#pager_dD',{add:false,edit:false,del:false, search:false});
	
	
	//boton de entrar en el login
	$("#button-iconD").click(function(){
		
		jQuery("#detaillD").jqGrid("clearGridData");
		jQuery("#masterD").jqGrid('setGridParam',{url:"RetrieveTraspasos?tyb=D"+ getQueryString() ,page:1})
		.trigger('reloadGrid');	
		
		
	});
	$("#button-icon2D").click(function(){
		jQuery("#detaillD").jqGrid("editRow","00190");
		this.disabled = 'true';
	});
	
	//////////////////////////////////////////////////
	// Funciones 
	/////////////////////////////////////////////////

	function clearSelection() {
				jQuery("#detaillD").jqGrid('setGridParam',{url: "empty.json", datatype: 'json'}); // the last setting is for demo purpose only
				jQuery("#detaillD").jqGrid('setCaption', 'Detail Grid:: none');
				jQuery("#detaillD").trigger("reloadGrid");
				
	}
	
	function getQueryString(){
		var strRetorno = "";
		
		strRetorno = "&docl=" + $("#docl").val() + "&doch=" + $("#doch").val();
		
		return strRetorno;		
	}
	
	//funcion para redireccionar dependiendo del explorador
	function redirect (url) {
	    var ua        = navigator.userAgent.toLowerCase(),
	        isIE      = ua.indexOf('msie') !== -1,
	        version   = parseInt(ua.substr(4, 2), 10);
	
	    // Internet Explorer 8 and lower
	    if (isIE && version < 9) {
	        var link = document.createElement('a');
	        link.href = url;
	        document.body.appendChild(link);
	        link.click();
	    }
	
	    // All other browsers can use the standard window.location.href (they don't lose HTTP_REFERER like Internet Explorer 8 & lower does)
	    else { 
	        window.location.href = url; 
	    }
	}


</script>
</body>
</html>
