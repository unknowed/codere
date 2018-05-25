<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<!-- CSS para los controles de JQueryUI -->
		<link href="js/JQuery2/jquery-ui.css" rel="stylesheet">
		<!-- CSS para sobreescribir los controles con el theme de Codere -->
		<link href="styles/textstyle.css" rel="stylesheet">  
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<style>
			div.relative {
			    position: relative;
			    width: 400px;
			    height: 50px;
			    align: center;
			} 
			.ui-selectmenu-open {z-index:9999 !important;}
		</style>
		<title>Traspasos CODERE</title>
	</head>
	<body>
	
	<!-- Este es el encabezado... la clase cabecera pone el color y demas sarasas. -->
	<table class="header">
		<tr>
			<th width="20%" class="header">
				<img alt="Logo" src="images/codere_logo_3.png">
			</th>
			<th width="80%" style="text-align:center;" class="header">
				<h1 class="header">Traspasos Codere</h1>
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
		<li><a href="#tabs-1">Documentos en transito</a></li>
		<li><a href="#tabs-2">Cerrar Sesion</a></li>
	</ul>
	<div id="tabs-1"></div>
	<div id="tabs-2"></div>
	</div>

	
	<div align="center" id="divlogin">
		<table class="table login">
			<tr>
				<td align="center">
					<form id="login" action="LoginServlet" method="post">
					
						<label for="user" class="caption">Usuario:</label> <input type="text" name="user" id="user" class="ui-widget ui-widget-content ui-corner-all">
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
	<div id="dialogSalir" title="Seleccionar una sala" align="center">
	<p id="TextoSalirCambios">Hay cambios pendientes, desea salir?</p>
	</div>

		<!-- JS De JQuery -->
		<script src="js/JQuery2/external/jquery/jquery.js"></script>
		<!-- JS de JQuery UI (Controles copados) -->
		<script src="js/JQuery2/jquery-ui.js"></script>		
		<!-- BlockUI -->
		<script src="js/jquery.blockUI.js"></script> 

	 
	<script>
		//****************************************************
		// preparar todos los objetos con el look de jquery
		//****************************************************
		
		//select para las salas
		$("#selectsalas").selectmenu();			
	
		//boton de Entrar del login
		$("#button-icon").button({
			icon: "ui-icon-check whiteIcon",
			showLabel: true
		});
		
		//oculto el div de errores
		$("#error").css("display","none");
		
		//oculto los tab
		$("#tabs").hide();
		//$("#tabs").css("display","none");
		$("#tabs").tabs().css({
			"min-height": "700px",
			"overflow": "auto",
		});
		
		$("#dialogSalir").css("display","none");
		//Logica para el logout
		$("#tabs").tabs({
			beforeActivate: function (event, ui ){
				if (ui.newTab.index() == 1 ){
					if (edit){
						$("#dialogSalir").dialog({
							modal: true,
							title: "Cambios Pendientes",
							autoOpen: true,
							dialogClass: "myTitleClass",
							buttons: {
								Yes: function() {
									edit=false;
									lastSel = null;
									editCount = 0;
									redirect("LogoutServlet");
									$(this).dialog("close");
								},
								No: function() {
									$(this).dialog("close");
								}
							}
						});
					}else{
						redirect("LogoutServlet");						
					}

				}
				return false;
			}
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
			modal:true,
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
				$.blockUI({
					message: '<h1 class="mensajeblock"><img src="images/ajax-loader.gif" /> Cargando...</h1>'
				});
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
		            complete: function(){
		            	$.unblockUI();
		            }
		        });
				//$("#divlogin").children().prop('disabled',false);
			}
	    });
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