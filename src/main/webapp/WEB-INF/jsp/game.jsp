<!DOCTYPE html>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html lang="en">
<head>

<link rel="stylesheet" type="text/css"
	href="webjars/bootstrap/3.3.7/css/bootstrap.min.css" />

<!-- 
	<spring:url value="/css/main.css" var="springCss" />
	<link href="${springCss}" rel="stylesheet" />
	 -->
<c:url value="/css/main.css" var="jstlCss" />
<link href="${jstlCss}" rel="stylesheet" />

</head>
<body>

	<nav class="navbar navbar-inverse">
		<div class="container">
			<div class="navbar-header">
				<a class="navbar-brand" href="#">Spring Boot</a>
			</div>
			<div id="navbar" class="collapse navbar-collapse">
				<ul class="nav navbar-nav">
					<li class="active"><a href="#">Home</a></li>
					<li><a href="#about">About</a></li>
				</ul>
			</div>
		</div>
	</nav>
	<script>
	function makeMove(id){
		var xhr = new XMLHttpRequest();
		xhr.open('GET', "/spring/executeMove?id=" + id, false);
		xhr.send();
	}
	</script>

	<div class="container">

		<div class="starter-template">
			<h1>bol.com board Game</h1>
			<h2>Message: ${message}</h2>
		</div>
			<table>
				<tr>
					<td>Player1's pits</td>
				</tr>
				<tr>
					<td><button id="0">pit1</button></td>
					<td><button id="1" onclick="makeMove(this.id)">pit2</button></td>
					<td><button id="2">pit3</button></td>
					<td><button id="3">pit4</button></td>
					<td><button id="4">pit5</button></td>
					<td><button id="5">pit6</button></td>
				</tr>
				<tr>
					<td>Player2's pits</td>
				</tr>
				<tr>
					<td><button id="7">pit1</button></td>
					<td><button id="8">pit2</button></td>
					<td><button id="9">pit3</button></td>
					<td><button id="10">pit4</button></td>
					<td><button id="11">pit5</button></td>
					<td><button id="12">pit6</button></td>
				</tr>
			</table>

	</div>
	<!-- /.container -->

	<script type="text/javascript"
		src="webjars/bootstrap/3.3.7/js/bootstrap.min.js"></script>

</body>

</html>
