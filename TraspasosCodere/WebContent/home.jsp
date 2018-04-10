<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<link href="js/JQuery/jquery-ui.css" rel="stylesheet">
		<link href="styles/textstyle.css" rel="stylesheet">
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<style>
		div.relative {
		    position: relative;
		    width: 400px;
		    height: 50px;
		    align: center;
		} 
		</style>
		<title>Traspasos CODERE</title>
	</head>
	<body>
	<table>
		<tr>
			<th width="20%">
				<img alt="Logo" src="images/codere_logo_3.png">
			</th>
			<th width="80%" style="text-align:center;">
				<h1>Traspasos Codere</h1>
			</th>
		</tr>
		<tr>
			<td colspan="2">
				<hr color="#009e00">
			</td>
		</tr>
	</table>
	

	
	<div align="center">
		<table class="table login">
			<tr>
				<td align="center">
					<form id="login" action="LoginServlet" method="post">
					
						Usuario: <input type="text" name="user" id="user">
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
	<div id="salaseleccion">
		<select id="selectmenu" name="selectmenu">
			<option>Slower</option>
			<option>Slow</option>
			<option selected="selected">Medium</option>
			<option>Fast</option>
			<option>Faster</option>
		</select>
		<h2 class="demoHeaders">Datepicker</h2>
<div id="datepicker"></div>
		
	</div>
	<script src="js/JQuery/external/jquery/jquery.js"></script>
	<script src="js/JQuery/jquery-ui.js"></script>
	<script>
		$(document).ready(function(){
			$( "#selectmenu" ).selectmenu();			
			
		});

		$( "#button-icon" ).button({
			icon: "ui-icon-check whiteIcon",
			showLabel: true,
		});

		
		$( "#datepicker" ).datepicker({
			inline: true
		});
		
		//$( "#button-icon" ).css("background","#00802b");
		//$( "#button-icon" ).css("color","#ffffff");

		$('#error').css("display","none");
	    var frm = $('#login');

	    frm.submit(function (e) {
			var usuario = $('#user').val(); 
			if(usuario == ""){
            	$('#textoerror').html("<span class='ui-icon ui-icon-alert' style='float: left; margin-right: .3em;'></span><strong>Error:</strong> Debe completar el usuario");
            	$('#error').css("display","inline");
            	return false;
			}else{
	        	e.preventDefault();

		        $.ajax({
		            type: frm.attr('method'),
		            url: frm.attr('action'),
		            data: frm.serialize(),
		            success: function (data) {
		            	var r = jQuery.parseJSON(data);

		            	if(r.success){
		            		
		            		
		            	}else{
		            		alert("entro error");
		            		switch(parseInt(r.type)){
		            		case 1:
		            			alert("entro 1");
			                	$('#textoerror').html("<span class='ui-icon ui-icon-alert' style='float: left; margin-right: .3em;'></span><strong>Error:</strong> El usuario <strong>" + r.result.i_USUARIO + "</strong> no existe.");
			                	break;
							default:
			                	$('#textoerror').html("<span class='ui-icon ui-icon-alert' style='float: left; margin-right: .3em;'></span><strong>Error:</strong> Error en SAP por favor contacte a su Administrador de Redes");
		            		}
		                	$('#error').css("display","inline");		            		
		            	}
		            },
		            error: function (jqXHR, textStatus, message) {

		            },
		        });
			}
	    });
	    
	   
	    
		$( "#button-icon" ).click(function(){
        	$('#error').css("display","none");
			$("#login").submit();
		});
		
		

	</script>
	</body>
</html>