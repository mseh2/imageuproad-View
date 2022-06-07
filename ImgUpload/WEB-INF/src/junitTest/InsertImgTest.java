package junitTest;

import static org.junit.Assert.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import dao.ImgDAO;
import model.ImgBeans;

class InsertImgTest {

	@Test
	void test() {
		String filename = "9999-99-99-99-99-99.jpg";
		String name = "テスト";
		String comment = "コメントのテスト";
		byte[] imgdata = null;
		Path p1 = Paths.get("C:\\Windows\\Web\\Wallpaper\\Theme1\\img2.jpg");
		try {
			imgdata = Files.readAllBytes(p1);
			System.out.println("画像のファイルサイズ：" + imgdata.length);
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			
		}
		
		ImgBeans imgbeans = new ImgBeans(filename,name,comment,imgdata);
		boolean b = ImgDAO.upload(imgbeans);
		
		assertTrue(b);
	}

}
