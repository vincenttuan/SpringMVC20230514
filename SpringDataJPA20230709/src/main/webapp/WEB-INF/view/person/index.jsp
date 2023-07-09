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
	
	${ persons }
	
</body>
</html>