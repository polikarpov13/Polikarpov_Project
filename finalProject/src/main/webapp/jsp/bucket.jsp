<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<!DOCTYPE html>
<html>
<head>

<title><spring:message code='login.title'/></title>

<script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
<script src="//netdna.bootstrapcdn.com/bootstrap/3.1.0/js/bootstrap.min.js"></script>

<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<link href="//netdna.bootstrapcdn.com/bootstrap/3.1.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<link href="//netdna.bootstrapcdn.com/font-awesome/4.0.3/css/font-awesome.css" rel="stylesheet">


<script type="text/javascript">
	$(document).ready(function() {
		var selItem = localStorage.getItem("locales");
		$('#locales').val(selItem ? selItem : 'en');
		$("#locales").change(function() {
			var selectedOption = $('#locales').val();
			if (selectedOption) {
				window.location.replace('?lang=' + selectedOption);
				localStorage.setItem("locales", selectedOption);
			}
		});
	});
</script>
</head>
<body>
<body>

<div class="container-fluid" >
	
		<!-- Sidebar -->
		<div class="w3-sidebar" style="width: 10%">

			<div class="list-group" style="margin-top: 40px">

				<div class="list-group-item active">
					<div>
						<h3><spring:message code='login.title'/></h3>
					</div>
					<div>${pageContext.request.userPrincipal.name}</div>
				</div>

				<a href="/home" class="list-group-item"> <i
					class="fa fa-comment-o"></i> <spring:message code='sidebar.home'/>
				</a>

				<security:authorize access="hasRole('ROLE_ADMIN')">
					<a href="/create-periodical" class="list-group-item"> <i
						class="fa fa-search"></i><spring:message code='sidebar.create_periodical'/>
					</a>
				</security:authorize>

				<security:authorize access="hasRole('ROLE_USER')">
					<a href="/buckets" class="list-group-item"> <i
						class="fa fa-search"></i> <spring:message code='sidebar.bucket'/>
					</a>
				</security:authorize>


				<c:if test="${pageContext.request.userPrincipal.name != null}">
					<form id="logoutForm" method="POST" action="${contextPath}/logout">
						<input type="hidden" name="${_csrf.parameterName}"
							value="${_csrf.token}" />
					</form>

					<a class="list-group-item"
						onclick="document.forms['logoutForm'].submit()"
						style="cursor: pointer"> <i class="fa fa-search"></i>  <spring:message code='sidebar.logout'/>
					</a>

				</c:if>

			</div>
		</div>


		<!-- Page Content -->
		
		<!-- Page Content -->
		<div style="margin-left: 10%">
			<div class="w3-container">
				<table class="table table-striped" style="margin-top: 2%; padding: 2%; width:800px; height: 150px; margin-left:30%">
					<thead>
						<tr>
							<th>Id</th>
							<th><spring:message code='bucket.name'/></th>
							<th><spring:message code='bucket.description'/></th>
							<th><spring:message code='bucket.price'/></th>
							<th><spring:message code='bucket.image'/></th>
							<th><spring:message code='bucket.date'/></th>
							<th><spring:message code='bucket.action'/></th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="bucket" items="${bucketItems}">
							<tr style="height: 10%">
								<td>${bucket.id}</td>
								<td>${bucket.periodical.name}</td>
								<td>${bucket.periodical.description}</td>
								<td>${bucket.periodical.price}</td>
								<td><img src="data:image/jpg;base64,${bucket.periodical.encodedImage}" alt="image" style="width: 40%; height: 20%"></td>
								<td>${bucket.purchaseDate}</td>
								<td><a href="bucket?id= ${bucket.id}"><spring:message code='bucket.delete'/></a></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>

	</div>
	
</body>
</html>