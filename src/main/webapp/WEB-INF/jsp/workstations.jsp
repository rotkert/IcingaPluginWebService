<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Workstations</title>
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
					<c:forEach items="${modelMap['groups'] }" var="group">
						<li><a href="javascript:getWorkstations(${group.id})" class="list-group-item">${group.name }</a>
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
							<div align="right">
								<a id="showRulesLink" href="" style="color: white">Show Rules</a>
							</div>
						</h3>
					</div>
					<div class="panel-body">
						<table class="table table-condensed" id="workstationsTable">
							<thead>
								<tr>
									<th>Name</th>
									<th>Description</th>
									<th>Notes</th>
									<th></th>
								</tr>
							</thead>
							<tbody id="workstationsTableBody">
							</tbody>
						</table>
					</div>
				</div>
			</div>
		</div>
	</div>

	<!-- Modal -->
	<div id="moveModal" class="modal fade" role="dialog">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4 class="modal-title">Select new group</h4>
				</div>
				<div class="modal-body">
					<form id="moveWorkstationForm" method="get" action="moveWorkstation">
						<input type="hidden" id="movedWorkstationId" name="workstationId" value="" /> <select name="groupId" class="form-control">
							<c:forEach items="${modelMap['groups'] }" var="group">
								<option value="${group.id}"><c:out value="${group.name}" /></option>
							</c:forEach>
						</select>
						<div class="modal-footer">
							<input type="reset" class="btn btn-default" data-dismiss="modal" value="Cancel" /> <input type="submit" id="moveWorkstationBtn"
								class="btn btn-success" value="Accept" />
						</div>
					</form>
				</div>
			</div>

		</div>
	</div>

	<!-- Modal -->
	<div id="newGroupModal" class="modal fade" role="dialog">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4 class="modal-title">CreateNewGroup</h4>
				</div>
				<div class="modal-body">
					<form id="newWorkstationsGroupForm" method="get" action="createWorkstationsGroup">
						<input type="hidden" id="newGroupWorkstationId" name="workstationId" value="" /> <label>Name:</label> <input type="text"
							class="form-control" name="name" /> <label>Description:</label> <input type="text" class="form-control" name="desc" />
						<div class="modal-footer">
							<input type="reset" class="btn btn-default" data-dismiss="modal" value="Cancel" /> <input type="submit" class="btn btn-success"
								value="Accept" />
						</div>
					</form>
				</div>
			</div>

		</div>
	</div>

	<script>
		var currentGroupId = 0;
		var groupMap = {};
		<c:forEach items="${modelMap['groups']}" var="group">
			groupMap[${group.id}] = "${group.name}";
		</c:forEach>

		$(document).ready(function() {
			getWorkstations("${modelMap['selectedGroup']}");
		});

		$("#moveModal").on("shown.bs.modal", function(e) {
			var workstationId = e.relatedTarget.value;
			$("#movedWorkstationId").val(workstationId);
		});
		
		$("#newGroupModal").on("shown.bs.modal", function(e) {
			var workstationId = e.relatedTarget.value;
			$("#newGroupWorkstationId").val(workstationId);
		});

		function getWorkstations(groupId) {
			currentGroupId = groupId;
			$.ajax({
				type : "GET",
				url : "getWorkstationsForGroup",
				data : {
					groupId : groupId
				},
				success : function(data) {
					finishGetWorkstations(data, groupId);
				}
			});
		}

		function finishGetWorkstations(responseData, groupId) {
			var workstations = $.parseJSON(responseData);
			var wsTableHtml = "";

			$.each(workstations, function(index, elem) {
				var disabled = "";
				var notes = "";
				var discardButton = "";
				
				if(elem.requestedCounters) {
					disabled = "disabled";
				}
				
				if(elem.description2 != null && elem.description2 != "undefined") {
					notes = elem.description2;
				}
				
				if(groupId == 0) {
					discardButton += "<td>";
					discardButton += "<button type='button' class='btn btn-danger' value='" + elem.id + "' onclick='discardWorkstation(" + elem.id + ")'>Discard</button>";
					discardButton += "</td>";	
				}
				
				wsTableHtml += "<tr>";
				wsTableHtml += "<td>" + elem.name + "</td>";
				wsTableHtml += "<td>" + elem.description + "</td>";
				wsTableHtml += "<td>" + notes + "</td>";
				wsTableHtml += "<td>";
				wsTableHtml += "<button type='button' class='btn btn-info' data-toggle='modal' data-target='#moveModal' value='" + elem.id + "' " + disabled + ">Move</button>";
				wsTableHtml += "</td>";
				wsTableHtml += "<td>";
				wsTableHtml += "<button type='button' class='btn btn-info' data-toggle='modal' data-target='#newGroupModal' value='" + elem.id + "' " + disabled + ">New group</button>";
				wsTableHtml += "</td>";
				wsTableHtml += discardButton;
				wsTableHtml += "</tr>";
				
			});

			var wsTable = $("#workstationsTableBody");
			wsTable.empty();
			wsTable.html(wsTableHtml);
			$("#groupNameLabel").html(groupMap[groupId]);
			$("#showRulesLink").attr("href", "counterRules?groupId=" + groupId);
		}
		
		function discardWorkstation(workstationId, groupId) {
			$.ajax({
				type : "GET",
				url : "discardWorkstation",
				data : {
					workstationId : workstationId
				},
				success : function() {
					getWorkstations(currentGroupId);
				}
			});
		}
	</script>
</body>
</html>
