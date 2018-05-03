<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<link href="js/JQuery2/jquery-ui.css" rel="stylesheet">
		<link href="styles/textstyle.css" rel="stylesheet">  
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<style>
		div.relative {
		    position: relative;
		    width: 400px;
		    height: 50px;
		    align: center;
		} 
		.ui-front{z-index:0 !important; } 
		.ui-selectmenu-open {z-index:9999 !important;}
		</style>
		<title>Traspasos CODERE</title>
	</head>
	<body>
	<table class="cabecera">
		<tr>
			<th width="20%" class="cabecera">
				<img alt="Logo" src="images/codere_logo_3.png">
			</th>
			<th width="80%" style="text-align:center;" class="cabecera">
				<h1>Traspasos Codere</h1>
			</th>
		</tr>
		<tr>
			<td colspan="2">
				<hr color="#009e00">
			</td>
		</tr>
	</table>
	<div id="tabs">
	<ul>
		<li><a href="#tabs-1">Busqueda por Material</a></li>
		<li><a href="#tabs-2">Busqueda por Documento</a></li>
	</ul>
	<div id="tabs-1">Tab 1</div>
	<div id="tabs-2">Tab 2</div>
	</div>

	
	<div align="center" id="divlogin">
		<table class="table login">
			<tr>
				<td align="center">
					<form id="login" action="LoginServlet" method="post">
					
						<label for="user">Usuario:</label> <input type="text" name="user" id="user">
						<br>
			
					</form>
					<button id="button-icon">Entrar</button>
					<br>
					<div id="error" class="ui-widget relative">
						<div class="ui-state-error ui-corner-all" style="padding: 0 .7em;">
							<p id="textoerror"><span class="ui-icon ui-icon-alert" style="float: left; margin-right: .3em;"></span>
							<strong>Error:</strong> </p>
						</div>
					</div>
				</td>
			</tr>
		</table>
	</div>
	
	<div id="dialog" title="Seleccionar una sala" align="center">
		<form id="frmsala" method="post" action="sessionManager">
			<select id="selectsalas" name="selectsalas">
			</select>
			<input type="hidden" id="hdnusuario" name="hdnusuario"/>
		</form>		
	</div>
	
 
	<script src="js/JQuery2/external/jquery/jquery.js"></script>
	<script src="js/JQuery2/jquery-ui.js"></script>
	 
	<script>
		//****************************************************
		// preparar todos los objetos con el look de jquery
		//****************************************************
		
		//select para las salas
		$("#selectsalas").selectmenu();			
	
		//boton de Entrar del login
		$("#button-icon").button({
			icon: "ui-icon-check whiteIcon",
			showLabel: true,
		});
		
		//oculto el div de errores
		$("#error").css("display","none");
		
		//oculto los tab
		$("#tabs").hide();
		//$("#tabs").css("display","none");
		$("#tabs").tabs().css({
				"min-height": "700px",
				"overflow": "auto"
		});

		
		//****************************************************
		//Seteamos las funciones de los distintos controles
		//****************************************************
		
		
		//boton de entrar en el login
		$("#button-icon").click(function(){
        	$("#error").css("display","none");
			$("#login").submit();
		});

		
		//armo las funciones del dialogo de seleccionar sala.
		$("#dialog").dialog({
			autoOpen: false,
			width: 400,
			height: 200,
			dialogClass: "myTitleClass",
			buttons: [
				{
					text: "Seleccionar",
					click: function() {
						$("#hdnusuario").val($("#user").val());
						$("#frmsala").submit();
						$( this ).dialog( "close" );
					}
				},
				{
					text: "Cancelar",
					click: function() {
						$( this ).dialog( "close" );
					}
				}
			]
		});
		
		
		
		//**************************************************
		// Preparo los formularios con ajax
		//**************************************************
		
		
		/////////////////////////////////
		// Formulario de seleccion de salas para Session.
		////////////////////////////////
		var frmsalas = $("#frmsala");
		frmsalas.submit(function (e){
			e.preventDefault();
			//alert("asdf");
			$.ajax({
				type: frmsalas.attr("method"),
				url: frmsalas.attr("action"),
				data: frmsalas.serialize(),
				success: function(data){
					$("#divlogin").hide();
					$("#tabs-1").load("findDoc.jsp");
					$("#tabs-2").load("findDocD.jsp");
					$("#tabs").show();					
				},
				error: function (jqXHR, textStatus, message) {
					//alert("dio error ");
	            },
			});
			
		});
		
		
		/////////////////////////////////
		//Formulario de login, le agrego la logica de ajax para el postback.
		////////////////////////////////
		var frm = $("#login");
	    frm.submit(function (e) {
	    	
	    	//validar que se haya puesto un usuario
			var usuario = $("#user").val(); 
			if(usuario == ""){
				//muestro el mensaje de error
            	$("#textoerror").html("<span class='ui-icon ui-icon-alert' style='float: left; margin-right: .3em;'></span><strong>Error:</strong> Debe completar el usuario");
            	$("#error").css("display","inline");
            	return false;
			}else{
				//si se puso usuario mandar todo al backend para obtener las salas o mostrar mensaje de error de que no existe el usuario.
	        	e.preventDefault();
				
				//$("#divlogin").children().prop('disabled',true);
		        //mando todo al backend
				$.ajax({
		            type: frm.attr("method"),
		            url: frm.attr("action"),
		            data: frm.serialize(),
		            success: function (data) {
		            	var r = jQuery.parseJSON(data);
						//si hay data y no error...
	            		if(r.success){
	            			
	            			//creo los options para el combobox
		            		var options = [];	
		            		for(i=0; i<r.result.length; i++){
		            			var arr = r.result[i].LGORT.split("-");
		            			options.push("<option value='" + arr[0].trim() + "/" + r.result[i].WERKS + "/" + arr[1].trim() +"'>" + arr[1].trim() + "</option>");
		            		}
		            		//los agrego al select y lo refresco en jquery para que los muestre en el control
		            		$("#selectsalas").empty();
		            		$("#selectsalas").append(options.join("")).selectmenu();
		            		$("#selectsalas").selectmenu("refresh");
		            		$("#dialog").dialog("open");
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
		            },
		        });
				//$("#divlogin").children().prop('disabled',false);
			}
	    });
	    
	</script>
	</body>
</html>