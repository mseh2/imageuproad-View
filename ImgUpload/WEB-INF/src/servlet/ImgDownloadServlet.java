package servlet;

import java.io.BufferedOutputStream;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ImgDAO;

@WebServlet("/imgdownload")
@MultipartConfig
public class ImgDownloadServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// imgタグで画像を呼ぶためのサーブレッド
		// リクエストパラメータ（filename）からファイル名を取得
		String filename = req.getParameter("filename");

		// ImgDAO.selectImgの引数にファイル名を渡してbyte[]を取得
		byte[] imgdata = ImgDAO.selectImg(filename);
		
		// ServletOutputStream(バイナリデータをクライアントにレスポンス）を使って書き込む
		try (ServletOutputStream sos = resp.getOutputStream();
				BufferedOutputStream bos = new BufferedOutputStream(sos);) {
			sos.write(imgdata);
			//バッファリングしているときは必ずflushする
			bos.flush();
			//出力ストリームを閉じるとレスポンス完了
			bos.close();
		}
	}

}
