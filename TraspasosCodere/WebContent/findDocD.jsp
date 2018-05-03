<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<script src="js/Grid3/js/jquery.jqgrid.src.js"></script>
	<link href="js/Grid3/css/ui.jqgrid.css" rel="stylesheet">
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Busqueda por Documento</title>
	<style>

	</style>
</head>
<body>
<table class="encabezado">
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
		<td colspan="2">
			<label id="lblAlmacenValue"><%= session.getAttribute("lgort") + " - " +  session.getAttribute("descripcion") %></label>		
		</td>
	</tr>
	<tr>
		<td colspan="2">
			<label id="lblMaterial" class="caption">Material de </label>		
			<input type="text" name="matl" id="matl">
			<label id="lblAlmacenValue" class="caption"> a </label>		
			<input type="text" name="math" id="math">
			<button id="button-icon">Buscar</button>
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
	<button id="button-icon2">Buscar</button>

<script>
	///////////////////////////////////////////////////////
	// Preparo los objetos
	//////////////////////////////////////////////////////
	//Oculto el div de errores
	$("#errord").css("display","none");
	
	//Boton de buscar
	
	$("#button-icon").button({
			icon: "ui-icon-search whiteIcon",
			showLabel: true,
	});
	$("#button-icon2").button({
		icon: "ui-icon-search whiteIcon",
		showLabel: true,
	});
	
	$("#button-icon").css("height","26px");
	$("#button-icon").css("align","center");
	
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
	    loadComplete: function(data){
	    	if($("#master").getGridParam("records") == 0){
	    		$("#textoerrord").html("<span class='ui-icon ui-icon-info' style='float: left; margin-right: .3em;'></span>No hay datos para mostrar");
	    		$("#errord").css("display","inline");
	    	}else{
	    		$("errord").css("display","none");
	    	}
	    },
		onPaging : clearSelection,
		caption: "Documentos de Traspasos",
		onSelectRow: function(ids) {
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
	});
	jQuery("#master").jqGrid('navGrid','#pager',{add:false,edit:false,del:false,search: false});
	
	$('#detaill').jqGrid('clearGridData');
	jQuery("#detaill").jqGrid({
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
	   	pager: '#pager_d',
	   	multiselect:true,
	   	sortname: 'Posicion',
	   	onSelectRow: function(id){
	   		alert(id);
	   	  $('#detaill').jqGrid('saveRow',lastsel,false,'clientArray');
	   	  
	   	  if(id && id!==lastsel){
	   	    $('#detaill').jqGrid('restoreRow',lastsel);
	   	    lastsel=id;
	   	  }
	   	  $('#detaill').jqGrid('editRow',id,false);
	   	},
	    viewrecords: true,
	    sortorder: "asc",
	    editurl:"sarasa.jsp",
	    caption:"Detalle Documento"
	}).navGrid('#pager_d',{add:false,edit:false,del:false, search:false});
	
	
	//boton de entrar en el login
	$("#button-icon").click(function(){
		
		jQuery("#detaill").jqGrid("clearGridData");
		jQuery("#master").jqGrid('setGridParam',{url:"RetrieveTraspasos?tyb=P"+ getQueryString() ,page:1})
		.trigger('reloadGrid');	
		
		
	});
	$("#button-icon2").click(function(){
		jQuery("#detaill").jqGrid("editRow","00190");
		this.disabled = 'true';
	});
	
	//////////////////////////////////////////////////
	// Funciones 
	/////////////////////////////////////////////////

	function clearSelection() {
				jQuery("#detaill").jqGrid('setGridParam',{url: "empty.json", datatype: 'json'}); // the last setting is for demo purpose only
				jQuery("#detaill").jqGrid('setCaption', 'Detail Grid:: none');
				jQuery("#detaill").trigger("reloadGrid");
				
	}
	
	function getQueryString(){
		var strRetorno = "";
		
		strRetorno = "&matl=" + $("#matl").val() + "&math=" + $("#math").val();
		
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
