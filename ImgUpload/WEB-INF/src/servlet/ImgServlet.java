/* http://localhost:8080/ImgUpload/Imguproad */
package servlet;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import dao.ImgDAO;
import model.ImgBeans;

@WebServlet("/Imguproad")
// 1Bの1024倍で1KB。1KBの1024倍で1MB
//テキストデータと画像データの混合が可能
@MultipartConfig(location = "D:\\images", maxFileSize = 1024 * 1024 * 10)
public class ImgServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 入力フォームにフォワード
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/jsp/imgUp.jsp");
		dispatcher.forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		// アップロードされた瞬間の時間をファイル名として付ける
		LocalDateTime now = LocalDateTime.now();// 現在時刻をナノ秒まで取得可能
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd-HH-mm-ss");// Timeクラスのフォーマット
		String fileName = now.format(dtf) + ".jpg";

		// 投稿者名とコメントを取得
		String name = req.getParameter("name");
		String comment = req.getParameter("comment"); // System.out.println(name + comment);
														// //@MultipartConfigをいれないとNULLが返される

		// Partインスタンスで画像データを取得
		Part imgdata = req.getPart("imgdata");
		// テスト：指定したファイル名で仮保存先（location="D:\\images"）へ保存
		// imgdata.write(fileName);→保存されたことが確認できればOK

		// 【Part → byte[] に変換しDBに保存】
		// Partインスタンスの入力ストリームから１バイトずつデータを読み込み→
		// ByteArrayOutputStream→画像データがなくなるまで繰り返し
		try (InputStream is = imgdata.getInputStream();
				BufferedInputStream bis = new BufferedInputStream(is);
				//バイト型に書き込まれる出力ストリーム
				ByteArrayOutputStream baos = new ByteArrayOutputStream();) {
			int b; // 1Bぶんのデータを仮に保存する変数
			while ((b = bis.read()) != -1) {// 画像データがなくなるまで繰り返す（なくなると－１が返される）
				baos.write(b);// baosに１文字ずつ書き込み
			}
			// 繰り返しが終了したらbyte[]に保存
			byte[] bytedata = baos.toByteArray();
			// ImgBeansインスタンスに保存してDAOからINSERTする
			ImgBeans imgBeans = new ImgBeans(fileName, name, comment, bytedata);
			ImgDAO.upload(imgBeans);
			// IOExceptionはdoPostでthrowsされているため、catchを記載せずにTomcatに投げられる
		}
		//一覧表示用のservletにリダイレクトする
		resp.sendRedirect("./ImgView");
	}
}
