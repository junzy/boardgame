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
	<script>
	document.addEventListener('DOMContentLoaded', function() {
		makeMove(null);
	}, false);
	
	function makeMove(id){
		var xhr = new XMLHttpRequest();
		xhr.open('GET', "/spring/executeMove?id=" + id, false);
		xhr.onload = function(e) {
		  if (this.status == 200) {
		  	var apiResponse = JSON.parse(this.response);
		    console.log('response', apiResponse); // JSON response
		    setGameState(JSON.parse(apiResponse.gameState));
		    if (apiResponse.playerTurn) {
		    		document.getElementById("message").innerText = apiResponse.playerTurn + "'s Turn'";
		    }
		    if (apiResponse.message) {
		    		document.getElementById("message").innerText = apiResponse.message;
		    }
		  }
		};

		xhr.send();
	}
	
	function setGameState(state) {
		
		var pits = document.getElementsByClassName("pit_value")
		// initial state setup
		if (state == null) {
			console.log(state)
			for (var i = pits.length - 1; i >= 0; i--) {
				pits[i].innerText = "6";
			}
		} else {
			// set game state based on API response
			for (var i = pits.length - 1; i >= 0; i--) {
				pits[i].innerText = state[i].stoneCount;
			}

		}
		

	}
	</script>
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

	<div class="container">

		<div class="starter-template">
			<h1>bol.com board Game</h1>
			<h2 id="message">Message: </h2>
		</div>
			<table border="1">
				<tr>
					<td>P1</td>
				</tr>
				<tr>
					<td><button id="0" onclick="makeMove(this.id)">pit1</button></td>
					<td><button id="1" onclick="makeMove(this.id)">pit2</button></td>
					<td><button id="2" onclick="makeMove(this.id)">pit3</button></td>
					<td><button id="3" onclick="makeMove(this.id)">pit4</button></td>
					<td><button id="4" onclick="makeMove(this.id)">pit5</button></td>
					<td><button id="5" onclick="makeMove(this.id)">pit6</button></td>
				</tr>
				<tr>
					<td><div id="pit0" class="pit_value"> </div></td>
					<td><div id="pit1" class="pit_value"> </div></td>
					<td><div id="pit2" class="pit_value"> </div></td>
					<td><div id="pit3" class="pit_value"> </div></td>
					<td><div id="pit4" class="pit_value"> </div></td>
					<td><div id="pit5" class="pit_value"> </div></td>
					<td><div id="pit6" class="pit_value"> </div></td>
				</tr>
				<tr>
					<td>P2</td>
				</tr>
				<tr>
					<td><div id="pit7" class="pit_value"> </div></td>
					<td><div id="pit8" class="pit_value"> </div></td>
					<td><div id="pit9" class="pit_value"> </div></td>
					<td><div id="pit10" class="pit_value"> </div></td>
					<td><div id="pit11" class="pit_value"> </div></td>
					<td><div id="pit12" class="pit_value"> </div></td>
					<td><div id="pit13" class="pit_value"> </div></td>
				</tr>
				<tr>
					<td><button id="7" onclick="makeMove(this.id)">pit1</button></td>
					<td><button id="8" onclick="makeMove(this.id)">pit2</button></td>
					<td><button id="9" onclick="makeMove(this.id)">pit3</button></td>
					<td><button id="10" onclick="makeMove(this.id)">pit4</button></td>
					<td><button id="11" onclick="makeMove(this.id)">pit5</button></td>
					<td><button id="12" onclick="makeMove(this.id)">pit6</button></td>
				</tr>
			</table>

	</div>
	<!-- /.container -->

	<script type="text/javascript"
		src="webjars/bootstrap/3.3.7/js/bootstrap.min.js"></script>

</body>

</html>
