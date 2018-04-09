<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<link href="js/JQuery/jquery-ui.css" rel="stylesheet">
		<link href="styles/textstyle.css" rel="stylesheet">
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		
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
	

<form action="LoginServlet" method="post">

Username: <input type="text" name="user">
<br>

<button id="button-icon">Entrar</button>
</form>

	<script src="js/JQuery/external/jquery/jquery.js"></script>
	<script src="js/JQuery/jquery-ui.js"></script>
	<script>
		$( "#button-icon" ).button({
			icon: "ui-icon-check whiteIcon",
			showLabel: true,
		});
		//$( "#button-icon" ).css("background","#00802b");
		//$( "#button-icon" ).css("color","#ffffff");
		$( "#button-icon" ).click(function(){
			alert("clicked!");
		});
	</script>
	</body>
</html>