<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<!-- head -->
	<%@include file="../include/head.jspf" %>
	<title>Person JPA</title>
</head>
<body style="padding: 15px">
	<!-- Person 表單 -->
	<form:form class="pure-form"
				modelAttribute="person"
				method="post"
				action="${ pageContext.request.contextPath }/mvc/person/">
		<fieldset>
			<legend>Person 表單</legend>
			<form:input path="id" readonly="true" /><p />
			<form:input path="name" placeholder="請輸入使用者名稱" /><p />
			<form:input path="password" placeholder="請輸入密碼" /><p />
			<form:input path="birth" type="date" /><p />
			<input type="hidden" id="_method" name="_method" value="${ _method }">
			<button type="submit" class="pure-button pure-button-primary">Submit</button>
			<button type="reset" class="pure-button pure-button-primary">Reset</button>
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
					<th>Update</th>
					<th>Delete</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="p" items="${ persons }">
					<tr>
						<td>${ p.id }</td>
						<td>${ p.name }</td>
						<td>${ p.password }</td>
						<td>${ p.birth }</td>
						<td>
							<a href="${ pageContext.request.contextPath }/mvc/person/${ p.id }">
								Edit
							</a>
						</td>
						<td>
							<a href="${ pageContext.request.contextPath }/mvc/person/delete/${ p.id }">
								Delete
							</a>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</form>
	
</body>
</html>