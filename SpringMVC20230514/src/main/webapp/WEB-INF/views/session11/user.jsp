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
		<title>User Form</title>
	</head>
	<body style="padding: 15px">
		
		<table>
			<tr>
				<td valign="top" style="padding: 5px">
					<!-- User form -->
					<spform:form class="pure-form"
								 method="post"
								 modelAttribute="user"
								 action="${pageContext.request.contextPath}/mvc/session11/user/${ index }">
						<fieldset>
							<legend>User Form</legend>
							<!-- User 表單元素 -->
							姓名：<spform:input path="name" /><p />
							年齡：<spform:input path="age" type="number" /><p />
							生日：<spform:input path="birth" type="date" /><p />
							學歷：<spform:select path="education">
									<spform:option value="">請選擇</spform:option>
									<spform:options items="${ educationList }"/>
								 </spform:select><p />
							性別：<spform:radiobuttons path="sex" items="${ sexList }" />
								 <p />
							興趣：<spform:checkbox path="interest" value="爬山" />爬山
								 <spform:checkbox path="interest" value="看書" />看書
								 <spform:checkbox path="interest" value="打球" />打球
								 <spform:checkbox path="interest" value="飛控" />飛控<p />
							履歷：<spform:textarea path="resume" /><p />
							<!-- 自訂表單元素 -->
							<input type="hidden" name="_method" id="_method" value="${ _method }">
							<!-- 操作按鈕 -->
							<button type="submit" class="pure-button pure-button-primary">
								${ submitButtonName }
							</button>
						</fieldset>
					</spform:form>
				</td>
				<td valign="top" style="padding: 5px">
					<!-- User list -->
					<form class="pure-form">
						<fieldset>
							<legend>User List</legend>
							
							<table class="pure-table pure-table-bordered">
								<thead>
									<tr>
										<th nowrap>index</th>
										<th nowrap>姓名</th>
										<th nowrap>年齡</th>
										<th nowrap>生日</th>
										<th nowrap>學歷</th>
										<th nowrap>性別</th>
										<th nowrap>興趣</th>
										<th nowrap>履歷</th>
										<th nowrap>修改</th>
										<th nowrap>刪除</th>
									</tr>
								</thead>
								
								<tbody>
									<c:forEach varStatus="status" var="user" items="${ users }">
										<tr>
											<td nowrap>${ status.index }</td>
											<td nowrap>${ user.name }</td>
											<td nowrap>${ user.age }</td>
											<td nowrap>
												<fmt:formatDate value="${ user.birth }" 
																pattern="yyyy-MM-dd" />
											</td>
											<td nowrap>${ user.education }</td>
											<td nowrap>${ user.sex }</td>
											<td nowrap>
												${ fn:join(user.interest, " ")}
											</td>
											<td nowrap>${ user.resume }</td>
											<td nowrap>
												<button type="button"
														onclick="window.location.href='${pageContext.request.contextPath}/mvc/session11/user/${ status.index }?action=update';"
														class="pure-button">修改</button>
											</td>
											<td nowrap>
												<button type="button"
														onclick="window.location.href='${pageContext.request.contextPath}/mvc/session11/user/${ status.index }?action=delete';"
														class="pure-button">刪除</button>
											</td>
										</tr>
									</c:forEach>
								</tbody>
							
							</table>
							
						</fieldset>
					</form>
				</td>
			</tr>
		</table>
		
	</body>
</html>