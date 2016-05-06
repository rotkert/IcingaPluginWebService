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
						<a href="createEmployee">Add Counter Rule</a>
					</div>
				</h3>
			</div>
			<div class="panel-body">
				<table class="table table-condensed" style="border-collapse: collapse;">
					<tbody>
						<c:forEach items="${counterRules}" var="map">
							<tr>
								<td>
									<table style="width: 100%">
										<tbody>
											<tr data-toggle="collapse" data-target="#ruleDetails${map.key}" class="accordion-toggle">
												<td rowspan=2><c:out value="${map.key}"></c:out></td>
												<c:forEach items="${map.value}" var="cr">
													<td><c:out value="${cr.category}${cr.instance}\\${cr.name}" /></td>
												</c:forEach>
											</tr>
											<tr id="ruleDetails${map.key}" class="accordian-body collapse">
												<c:forEach items="${map.value}" var="cr">
													<td>
														<ul>
															<li><c:out value="${cr.threshold}" /></li>
															<li><c:out value="${cr.minPeriod}" /></li>
														</ul>
													</td>
												</c:forEach>
											</tr>
										</tbody>
									</table>
								</td>
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
</body>
</html>
