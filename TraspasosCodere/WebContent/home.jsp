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
		} 
		</style>
		<title>Traspasos CODERE</title>
	</head>
	<body>
	<table width="100%" height="20px" cellpadding="0" border="0" cellspacing="0">
		<tr>
			<td width="20%">
				<img alt="Logo" src="images/Logo.png" height="45px" width="165px">
			</td>
			<td width="80%" style="text-align:center;">
				<h1>Traspasos Codere</h1>
			</td>
		</tr>
		<tr>
			<td colspan="2">
				<hr color="#009e00">
			</td>
		</tr>
	</table>
	

<form id="login" action="LoginServlet" method="post">

Username: <input type="text" name="user">
<br>

</form>
<button id="button-icon">Entrar</button>
<div id="error" class="ui-widget relative">
	<div class="ui-state-error ui-corner-all" style="padding: 0 .7em;">
		<p><span class="ui-icon ui-icon-alert" style="float: left; margin-right: .3em;"></span>
		<strong>Error:</strong> El usuario no existe!</p>
	</div>
</div>
	<script src="js/JQuery/external/jquery/jquery.js"></script>
	<script src="js/JQuery/jquery-ui.js"></script>
	<script>
		$( "#button-icon" ).button({
			icon: "ui-icon-check whiteIcon",
			showLabel: true,
		});
		//$( "#button-icon" ).css("background","#00802b");
		//$( "#button-icon" ).css("color","#ffffff");


		$('#error').css("display","none");
	    var frm = $('#login');

	    frm.submit(function (e) {

	        e.preventDefault();

	        $.ajax({
	            type: frm.attr('method'),
	            url: frm.attr('action'),
	            data: frm.serialize(),
	            success: function (data) {
	                console.log('Submission was successful.');
	                
	                var result = $.trim(data);
	                //alert(result);
	                if(result == "Sin Datos"){
	                	$('#error').css("display","inline");	
	                	//alert("entro if");
	                }
	                console.log(data);
	            },
	            error: function (data) {
	                console.log('An error occurred. Maybe?');
	                console.log(data);
	            },
	        });
	    });
	    
		$( "#button-icon" ).click(function(){
			alert("clicked!");
			$("#login").submit();
		});
	</script>
	</body>
</html>