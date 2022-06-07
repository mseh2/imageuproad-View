package model;

import java.io.Serializable;

public class ImgBeans implements Serializable {
	private String filename;				//画像のファイル名 の形式
	private String name;					//投稿者名
	private String comment;				//コメント
	private byte[] imgdata;				//画像データ(byte[]型)
	
	public ImgBeans() {}

	public ImgBeans(String filename, String name, String comment, byte[] imgdata) {
		this.filename = filename;
		this.name = name;
		this.comment = comment;
		this.imgdata = imgdata;
	}
	
	//画像データ以外を格納できるコンストラクタ
	public ImgBeans(String filename, String name, String comment) {
		this.filename = filename;
		this.name = name;
		this.comment = comment;
	}
	
	public String getFilename() {
		return filename;
	}

	public String getName() {
		return name;
	}

	public String getComment() {
		return comment;
	}

	public byte[] getImgdata() {
		return imgdata;
	}

	

}
