<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Counter Rule Information</title>
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
	<script src="https://code.jquery.com/jquery-1.9.1.min.js"></script>
	<script type="text/javascript">
		
		$(document).ready(function() {
			$('#counterCategory').change(function() {
				var categoryId = $(this).find(":selected").get(0).id;
				getCounters(categoryId);
			});
		});
		
		function getCounters(categoryId) {
			$.ajax({
				type: "GET",
				url: "getCountersForCategory",
				data: {categoryId: categoryId},
				success: function(data) {
					finishGetCounters(data);
				}
			});
		}
		
		function finishGetCounters(responseData) {
			var response = $.parseJSON(responseData);
			
			var counterNames = response.counters;
			var cnSelect = $("#counterName");
			cnSelect.prop('disabled', false);
			cnSelect.empty();
			cnSelect.append("<option value=''></option>")
			
			$.each(counterNames, function(index, elem) {
				var optionStr = "<option value='" + elem.name +"'>" + elem.name + "</option>";
				cnSelect.append(optionStr);
			});
			
			var counterInstances = response.instances;
			var ciSelect = $("#counterInstance");
			ciSelect.empty();
			
			if(counterInstances.length == 0) {
				ciSelect.prop('disabled', true);
				return;
			}
			
			ciSelect.prop('disabled', false);
			ciSelect.append("<option value=''></option>")
			
			
			$.each(counterInstances, function(index, elem) {
				var optionStr = "<option value='" + elem.name +"'>" + elem.name + "</option>";
				ciSelect.append(optionStr);
			});
		}
	</script>
	
	<div class="container myrow-container">
		<div class="panel panel-primary">
			<div class="panel-heading">
				<h3 class="panel-title">
					<div align="left">
						<b>Counter Rule Details</b>
					</div>
					<div align="right">
						<a href="counterRules" style="color:white">Back to list</a>
					</div>
				</h3>
			</div>
			<div class="panel-body">
				<table id="rules" class="table table-stripped">
					<thead>
						<tr>
							<th>Category</th>
							<th>Instance</th>
							<th>Name</th>
							<th>Threshold</th>
							<th>Min. Period</th>
							<th>Is Above</th>
							<th></th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${modelMap.counterRules}" var="cr">
							<tr>
								<td><c:out value="${cr.category}" /></td>
								<td><c:out value="${cr.instance}" /></td>
								<td><c:out value="${cr.name}" /></td>
								<td><c:out value="${cr.threshold}" /></td>
								<td><c:out value="${cr.minPeriod}" /></td>
								<td><c:out value="${cr.isAbove}" /></td>
								<td align="center">
									<a href='deleteCounterRule?id=${cr.id}&ruleId=<%= request.getParameter("ruleId") %>'>
									<span class="glyphicon glyphicon-remove" aria-hidden="true"></span></a>
									
								</td>
							</tr>
						</c:forEach>
						<form:form id="newCounterRuleForm" modelAttribute="counterRule" method="post" action="saveCounterRule">
							<form:hidden path="id" value="${counterRuleObject.id}"/>
							<form:hidden path="ruleId" value='<%= request.getParameter("ruleId") %>'/>
							<tr>
								<td>
									<form:select id="counterCategory" class="form-control" path="category" value="${counterRuleObject.category}">
										<option value=""></option>
										<c:forEach items="${modelMap.counterObjects}" var="category">
											<option id= "${category.id}" value="${category.name}"><c:out value="${category.name}"/></option>
										</c:forEach>
									</form:select>
								</td>
								<td><form:select id="counterInstance" class="form-control" path="instance" value="${counterRuleObject.instance}" style="width:120px" disabled="true"/></td>
								<td><form:select id="counterName" class="form-control" path="name" value="${counterRuleObject.name}" style="width:120px" disabled="true"/></td>
								<td><form:input type="text" class="form-control" path="threshold" value="${counterRuleObject.threshold}"/></td>
								<td><form:input type="text" class="form-control" path="minPeriod" value="${counterRuleObject.minPeriod}"/></td>
								<td><form:input type="text" class="form-control" path="isAbove" value="${counterRuleObject.isAbove}"/></td>
								<td><input type="submit" id="addCounterRule" class="btn btn-success" value="Add"/></td>
							</tr>
						</form:form>
					</tbody>
				</table>
			</div>
		</div>
	</div>

	<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>


</body>
</html>