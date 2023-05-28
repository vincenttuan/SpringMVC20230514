<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>     
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<link rel="stylesheet" href="https://unpkg.com/purecss@2.0.6/build/pure-min.css">
		<title>Lotto</title>
	</head>
	<body style="padding: 15px">
		<!-- 電腦選號 -->
		<form class="pure-form" 
			  method="get" 
			  action="${pageContext.request.contextPath}/mvc/lotto/add">
			<fieldset>
				<legend>Lotto 539 電腦選號</legend>
				<button type="submit" class="pure-button pure-button-primary">
					取得最新電腦選號
				</button>
			</fieldset>
		</form>
		<!-- 最新電腦選號 -->
		<div>
			最新電腦選號：${ param.lotto }
		</div>
		<!-- 歷史電腦選號 -->
		<div>
			歷史電腦選號：${ lottos }
		</div>
	</body>
</html>