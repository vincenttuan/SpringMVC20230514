<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
	<head>
		<link rel="stylesheet"
			href="https://unpkg.com/purecss@2.0.6/build/pure-min.css">
		<meta charset="UTF-8">
		<title>Product & Master</title>
	</head>
	<body style="padding: 15px">
		<form method="post" 
			  class="pure-form" 
			  action="${ pageContext.request.contextPath }/mvc/session17/product_master/">
			  <fieldset>
			  	<legend>Product & Master</legend>
			  	品名：<input id="a.name" name="a.name" /><p />
			  	價格：<input id="a.price" name="a.price" /><p />
			  	師傅：<input id="b.name" name="b.name" /><p />
			  	價格：<input id="b.price" name="b.price" /><p />
			  	<button type="submit" class="pure-button pure-button-primary">新增</button>
			  </fieldset>
		</form>
		Products: ${ products } <p />
		Masters: ${ masters } <p />
	</body>
</html>



