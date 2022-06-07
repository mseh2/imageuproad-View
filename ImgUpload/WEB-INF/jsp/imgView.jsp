<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
    <%@page import="model.ImgBeans"  %>
    
<!DOCTYPE html>
	<html>
		<head>
			<meta charset="UTF-8">
			<title>画像一覧</title>
		</head>
		
		<body>
			<h1>画像一覧</h1><hr>
			
			<%--リストの中身をすべて取得するまで繰り返す --%>
			<c:forEach var="img" items="${imagesList}">
				<table border="1" style="width:500px; height:200px;">
					<tr>
						<td rowspan="3"><img src ="./imgdownload?filename=${img.filename}" alt="DB画像" width="200px"></td>
						<td><c:out value="${img.name}"></c:out></td>
					</tr>
					<tr>
						<td><c:out value="${img.comment}"/></td>
					</tr>
					<tr>
						<td><c:out value="${img.filename}"/></td>
					</tr>
				</table>
			</c:forEach>
			<a href="./Imguproad" >もどる</a>
		</body>
		<%--CSS --%>
		<style type="text/css">
			td {
				width:300px;
			}
		</style>
		
	</html>