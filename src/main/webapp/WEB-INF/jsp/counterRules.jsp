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
	<div class="container myrow-container">
		<div class="panel panel-success">
			<div class="panel-heading">
				<h3 class="panel-title">
					<div align="left">
						<b>Counter Rules</b>
					</div>
					<div align="right">
						<a href="counterRuleDetails?ruleId=-1">Add Counter Rule</a>
					</div>
				</h3>
			</div>
			<div class="panel-body">
				<table class="table table-condensed" style="border-collapse: collapse;" id="rulesTable">
					<tbody>
						<c:forEach items="${counterRules}" var="map">
							<tr>
								<td><c:out value="${map.key}"></c:out></td>
								<c:forEach items="${map.value}" var="cr">
									<td data-toggle="collapse" data-target=".ruleDetails${map.key}" class="accordion-toggle"><c:out
											value="${cr.category}(${cr.instance})\\${cr.name}" /></td>
								</c:forEach>
								<td align="right"><a href="counterRuleDetails?ruleId=${map.key}" class="editButton"> <span class="glyphicon glyphicon-edit"
										aria-hidden="true"></span>
								</a></td>
							</tr>
								<tr>
									<td></td>
									<c:forEach items="${map.value}" var="cr">
										<td >
											<div class="accordian-body collapse ruleDetails${map.key}">
												<ul>
													<li><c:out value="${cr.threshold}" /></li>
													<li><c:out value="${cr.minPeriod}" /></li>
													<li><c:out value="${cr.isAbove}" /></li>
												</ul>
											</div>
										</td>
									</c:forEach>
									<td></td>
								</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
	</div>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>

	<%-- <script src="<c:url value="/resources/js/jquery-2.1.3.js"/>"></script>
<script src="<c:url value="/resources/js/bootstrap.min.js"/>"></script>
--%>
	<script>
		$(document).ready(function() {
			moveLastCellRight();
		});

		function moveLastCellRight() {
			var maxLength = 0;
			$('#rulesTable tr').each(function(index, element) {
				var length = element.cells.length;
				if (length > maxLength) {
					maxLength = length;
				}
			});

			$('#rulesTable tr').each(
					function(index, element) {
						var length = element.cells.length;
						$(this).find('td:last').attr('colspan',
								maxLength - length + 1);
					});
		}
	</script>
</body>
</html>
