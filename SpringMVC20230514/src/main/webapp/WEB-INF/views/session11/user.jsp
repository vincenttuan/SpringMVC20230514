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
														onclick=""
														class="pure-button">修改</button>
											</td>
											<td nowrap>
												<button type="button"
														onclick=""
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