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
		<p>
		歷史電腦選號紀錄（${ fn:length(lottos) }）：
		<!-- 歷史電腦選號 -->
		<table class="pure-table pure-table-bordered">
			<thead>
				<tr>
					<th>index</th>
					<th>號碼 1</th>
					<th>號碼 2</th>
					<th>號碼 3</th>
					<th>號碼 4</th>
					<th>號碼 5</th>
					<th>更新</th>
					<th>刪除</th>
				</tr>
			</thead>
			<tbody>
				<!-- 分析 List 集合 -->
				<c:forEach varStatus="status" var="lotto" items="${ lottos }">
					<tr>
						<td>${ status.index }</td>
						<!-- 分析 Set 集合 -->
						<c:forEach varStatus="num_status" var="num" items="${ lotto }">
							<td>
								<a href="${pageContext.request.contextPath}/mvc/lotto/update/${ status.index }/${ num_status.index }">
									${ num }
								</a>
							</td>
						</c:forEach>
						<td>
							<button type="button" 
									onclick="window.location.href='${pageContext.request.contextPath}/mvc/lotto/update/${ status.index }';" 
									class="pure-button">
								更新
							</button>
						</td>
						<td>
							<button type="button" 
									onclick="window.location.href='${pageContext.request.contextPath}/mvc/lotto/delete/${ status.index }';" 
									class="pure-button">
								刪除
							</button>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</body>
</html>