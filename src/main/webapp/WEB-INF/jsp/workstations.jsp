<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>CounterRules</title>
<!-- Bootstrap CSS -->
<%-- <link href="<c:url value="/resources/css/bootstrap.min.css" />" rel="stylesheet"> --%>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
<style type="text/css">
.myrow-container {
	margin: 20px;
}
</style>
</head>
<body class=".container-fluid">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>

	<div class="container myrow-container">
		<div class="row">
			<div class="col-md-3" id="leftCol">
				<ul class="nav nav-stacked menu" id="sidebar">
					<c:forEach items="${groups }" var="group">
						<li><a href="javascript:getWorkstations(${group.id}, '${group.name}')" class="list-group-item">${group.name }</a>
					</c:forEach>
				</ul>
			</div>
			<div class="col-md-9">
				<div class="panel panel-primary">
					<div class="panel-heading">
						<h3 class="panel-title">
							<div align="left">
								<b id="groupNameLabel"></b>
							</div>
						</h3>
					</div>
					<div class="panel-body">
						<table class="table table-condensed" id="workstationsTable">
							<thead>
								<tr>
									<th>Name</th>
									<th>Description</th>
								</tr>
							</thead>
							<tbody id="workstationsTableBody">
							</tbody>
						</table>
					</div>
				</div>
			</div>
		</div>

		<script>
			$(document).ready(function() {
				getWorkstations("${groups[0].id}", "${groups[0].name}");
			});

			function getWorkstations(groupId, groupName) {
				$("#groupNameLabel").html(groupName);
				
				$.ajax({
					type : "GET",
					url : "getWorkstations",
					data : {
						groupId : groupId
					},
					success : function(data) {
						finishGetWorkstations(data);
					}
				});
			}
			
			function finishGetWorkstations(responseData) {
				var workstations = $.parseJSON(responseData);
				var wsTableHtml = "";
				
				$.each(workstations, function(index, elem) {
					wsTableHtml += "<tr><td>" + elem.name + "</td><td>" + elem.description + "</td></tr>";
				});
				
				var wsTable = $("#workstationsTableBody");
				wsTable.empty();
				wsTable.html(wsTableHtml);
			}
		</script>
</body>
</html>
