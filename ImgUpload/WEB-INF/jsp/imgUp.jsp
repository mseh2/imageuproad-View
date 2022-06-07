<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!DOCTYPE html>
	<html>
		<head>
			<meta charset="UTF-8">
			<title>画像アップローダー</title>
		</head>
		
		<body>
			<h1>入力フォーム</h1><hr>
			<form action="./Imguproad" method="post" enctype="multipart/form-data">
				<table>	
					<tr>
						<td>投稿者名</td>
						<td><input type="text" name="name"></td>
					</tr>
					<tr>
						<td>コメント</td>
						<td><textarea name="comment" rows="15" cols="20"></textarea></td>
					</tr>
					<tr>
						<td>画像ファイル</td>
						<td><input type="file" name="imgdata" accept="image/jpeg"></td>
					</tr>
				</table>
				<input type="submit" value="送信">
			</form>
			<a href="./ImgView">一覧へ</a>
		</body>
	</html>