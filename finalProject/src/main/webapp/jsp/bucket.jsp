<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Periodicals</title>
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
</head>
<body>
<body>
	<div class="container">

		<!-- Sidebar -->
		<div class="w3-sidebar w3-light-grey w3-bar-block" style="width: 10%">
			<h3 class="w3-bar-item">Menu</h3>
			<a href="/home" class="w3-bar-item w3-button">Home</a>

			<security:authorize access="hasRole('ROLE_ADMIN')">
				<a href="/create-periodical" class="w3-bar-item w3-button">Create
					periodical</a>
			</security:authorize>

			<security:authorize access="hasRole('ROLE_USER')">
				<a href="/buckets" class="w3-bar-item w3-button">Bucket</a>
			</security:authorize>
		</div>

		<!-- Page Content -->
		<div style="margin-left: 10%">
			<div class="w3-container w3-teal">
				<h1>Create new Periodical</h1>
			</div>
			<div class="w3-container">
				<c:if test="${pageContext.request.userPrincipal.name != null}">
					<form id="logoutForm" method="POST" action="${contextPath}/logout">
						<input type="hidden" name="${_csrf.parameterName}"
							value="${_csrf.token}" />
					</form>
					<h2>
						Welcome ${pageContext.request.userPrincipal.name} | <a
							onclick="document.forms['logoutForm'].submit()">Logout</a>
					</h2>
				</c:if>

				<table class="table table-striped">
					<thead>
						<tr>
							<th>Id</th>
							<th>Name</th>
							<th>Description</th>
							<th>Price</th>
							<th>Image</th>
							<th>Purchase Date</th>
							<th>Action</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="bucket" items="${bucketItems}">
							<tr>
								<td>${bucket.id}</td>
								<td>${bucket.periodical.name}</td>
								<td>${bucket.periodical.description}</td>
								<td>${bucket.periodical.price}</td>
								<td><img src="data:image/jpg;base64,${bucket.periodical.encodedImage}" alt="image" style="width: 10%"></td>
								<td>${bucket.purchaseDate}</td>
								<td><a href="bucket?id= ${bucket.id}">delete</a></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>


			</div>

		</div>


	</div>
</body>
</html>