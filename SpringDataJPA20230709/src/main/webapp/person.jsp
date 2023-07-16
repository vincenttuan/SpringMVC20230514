<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!doctype html>
<html lang="en">
<head>
    <!-- Head -->
    <%@include file="/WEB-INF/view/include/head.jspf" %>
    <script src="http://ajax.googleapis.com/ajax/libs/angularjs/1.2.15/angular.min.js"></script>
</head>
<body style="padding: 20px" ng-app="myApp">

<div id="layout">
    <div id="main" ng-controller="personController">
        <div class="header">
            <h1>Person</h1>
            <h2>Rest</h2>
        </div>
		
		<table>
			<td valign="top">
				<!-- Person 表單 -->
				<form class="pure-form">
					<fieldset>Person Form</fieldset>
					ID：<input type="text" ng-model="id"><p />
					Name：<input type="text" ng-model="name"><p />
					Password：<input type="text" ng-model="password"><p />
					Birth：<input type="text" ng-model="birth"><p />
					<button ng-click="create()">Create</button>
					<button ng-click="update()">Update</button>
					<button ng-click="del()">Delete</button>
				</form>
			</td>
			<td valign="top">
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
								<th>Edit</th>
							</tr>
						</thead>
						<tbody>
							<tr ng-repeat="person in persons">
								<td>{{ person.id }}</td>
								<td>{{ person.name }}</td>
								<td>{{ person.password }}</td>
								<td>{{ person.birth }}</td>
								<td>
									<button ng-click="edit(person.id)">Edit</button>
								</td>
							</tr>
						</tbody>
					</table>
				</form>
			</td>
		</table>
        
    </div>
</div>

<!-- Angular JS -->
<script>
	var app = angular.module("myApp", []); // 初始 app
	
	// 查詢
	function queryPersons($scope, $http) {
		var url = "${ pageContext.request.contextPath }/mvc/rest/person/";
		$http.get(url)
			.success(function(resp) {
				$scope.persons = resp;
				console.log(resp);
			}).error(function(e) {
				alert(e);
			});
	}
	
	// 編輯(取得指定單筆資料)
	function editPerson($scope, $http, id) {
		var url = "${ pageContext.request.contextPath }/mvc/rest/person/" + id;
		$http.get(url)
			.success(function(resp) {
				var person = resp;
				$scope.id = person.id;
				$scope.name = person.name;
				$scope.password = person.password;
				$scope.birth = person.birth;
			}).error(function(e) {
				alert(e);
			});
	}
	
	// 新增
	function createPerson($scope, $http) {
		var url = "${ pageContext.request.contextPath }/mvc/rest/person/";
		var obj = {};
		obj["id"] = $scope.id;
		obj["name"] = $scope.name;
		obj["password"] = $scope.password;
		obj["birth"] = $scope.birth;
		var json = JSON.stringify(obj); // 物件轉 json 字串
		$http({
				method : 'POST',
				url : url,
				data : json,
				headers : { 'Content-Type' : 'application/json' }
		    }).success(function(resp){
		    	alert('新增成功');
				queryPersons($scope, $http);
			}).error(function(e) {
				alert('新增失敗: ' + e);
			});
	}
	
	// 修改
	function updatePerson($scope, $http) {
		var url = "${ pageContext.request.contextPath }/mvc/rest/person/";
		var obj = {};
		obj["id"] = $scope.id;
		obj["name"] = $scope.name;
		obj["password"] = $scope.password;
		obj["birth"] = $scope.birth;
		var json = JSON.stringify(obj); // 物件轉 json 字串
		$http({
				method : 'PUT',
				url : url,
				data : json,
				headers : { 'Content-Type' : 'application/json' }
		    }).success(function(resp){
		    	alert('修改成功');
				queryPersons($scope, $http);
			}).error(function(e) {
				alert('修改失敗: ' + e);
			});
	}
	
	// 刪除
	function delPerson($scope, $http) {
		var url = "${ pageContext.request.contextPath }/mvc/rest/person/" + $scope.id;
		$http({
				method : 'DELETE',
				url : url
		    }).success(function(resp){
		    	alert('刪除成功');
				queryPersons($scope, $http);
			}).error(function(e) {
				alert('刪除失敗: ' + e);
			});
	}
	
	var func = function($scope, $http) { // 功能
		// 查詢
		queryPersons($scope, $http);
		// 編輯(取得指定單筆資料)
		$scope.edit = function(id) {
			editPerson($scope, $http, id);
		};
		// 新增
		$scope.create = function() {
			createPerson($scope, $http);
		};
		// 修改
		$scope.update = function() {
			updatePerson($scope, $http);
		};
		// 刪除
		$scope.del = function() {
			delPerson($scope, $http);
		};
	};
	
	app.controller("personController", func);
	
</script>

</body>
</html>