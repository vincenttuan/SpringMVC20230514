<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<!-- head -->
	<%@include file="../include/head.jspf" %>
	<title>Person page JPA</title>
</head>
<body style="padding: 15px">
	<!-- Person page 分頁 -->
	<form:form class="pure-form"
				method="get"
				action="${ pageContext.request.contextPath }/mvc/person/page">
		<fieldset>
			<legend>Person page 分頁</legend>
			頁數：
			<section name="no" onchange="this.form.submit()">
				<c:forEach var="no" begin="0" end="${ totalPage - 1 }">
					<option value="${ no }" ${ no == pageNo ? "selected" : "" } >
						${ no + 1 }
					</option>
				</c:forEach>
			</section>
		</fieldset>
		
	</form:form>
	<!-- Person 列表 -->
	<form class="pure-form">
		<fieldset>Person List</fieldset>
		<table class="pure-table pure-table-bordered">
			<thead>
				<tr>
					<th>ID</th>
					<th>Name</th>
					<th>Password</th>
					<th>Birth</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="p" items="${ persons }">
					<tr>
						<td>${ p.id }</td>
						<td>${ p.name }</td>
						<td>${ p.password }</td>
						<td>${ p.birth }</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</form>
	
</body>
</html>