/*http://localhost:8080/ImgUpload/ImgView*/
package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ImgDAO;
import model.ImgBeans;

@WebServlet("/ImgView")
@MultipartConfig
public class ImgViewServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		//ファイル名、投稿者、コメントのみを保持したインスタンスをスコープにセット
		List<ImgBeans> imagesList = ImgDAO.selectAll();
		req.setAttribute("imagesList", imagesList);
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("WEB-INF/jsp/imgView.jsp");
		dispatcher.forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO 自動生成されたメソッド・スタブ
		super.doPost(req, resp);
	}
	
	
	
}
