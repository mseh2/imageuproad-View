package junitTest;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import dao.ImgDAO;

class SelectImgTest {

	@Test
	void test() {
		byte[] imgdata = ImgDAO.selectImg("2022-06-07-14-06-14.jpg");
		System.out.println("画像の容量は158112B");
		System.out.println("byte[]の要素数は" + imgdata.length + "B");
		
		assertEquals(158112, imgdata.length);
		
	}

}
