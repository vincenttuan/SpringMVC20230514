<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spform" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
	<head>
		<link rel="stylesheet" href="https://unpkg.com/purecss@2.0.6/build/pure-min.css">
		<meta charset="UTF-8">
		<title>Stock Form (Valid)</title>
			<style type="text/css">
				.error {
					color: #FF0000
				}
			</style>
		</head>
	<body style="padding: 15px">
		<spform:form class="pure-form"
					 method="post"
					 modelAttribute="stock"
					 action="${ pageContext.request.contextPath }/mvc/session14/stock/">
		
			<fieldset>
				<legend>Stock Form (Valid)</legend>
				股號：<spform:input path="symbol"/>
					 <spform:errors path="symbol" cssClass="error" />
					 <p />
				價格：<spform:input path="price"/>
					 <spform:errors path="price" cssClass="error" />
					 <p />
				數量：<spform:input path="amount"/>
					 <spform:errors path="amount" cssClass="error" />
					 <p />
				<button type="sumbot" class="pure-button pure-button-primary">新增</button>
				<p />
				<spform:errors path="*" cssClass="error" />
			</fieldset>
		</spform:form>
		
		平均每股買進價格：
		${ avgPrice }
		<p />
		<table class="pure-table pure-table-bordered">
			<thead>
				<tr>
					<th>index</th>
					<th>股號</th>
					<th>價格</th>
					<th>數量</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach varStatus="status" var="stock" items="${ stocks }">
					<tr>
						<td>${ status.index }</td>
						<td>${ stock.symbol }</td>
						<td>${ stock.price }</td>
						<td>${ stock.amount }</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		
	</body>
</html>