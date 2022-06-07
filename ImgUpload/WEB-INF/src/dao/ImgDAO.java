package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.ImgBeans;

public class ImgDAO {
	private static final String jdbc_url = "jdbc:postgresql:imguproad";
	private static final String db_user = "postgres";
	private static final String db_pass = "postgres";

	// ドライバの有無確認（一回だけ）
	static {
		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.out.println("Driverないです");
		}
	}

	public static boolean upload(ImgBeans imgbeans) {
		try (Connection con = DriverManager.getConnection(jdbc_url, db_user, db_pass);
				PreparedStatement pst = con.prepareStatement("INSERT INTO postimg VALUES(?,?,?,?)");) {

			// ？（プレースホルダ）にJavaBeansのデータをセット
			pst.setString(1, imgbeans.getFilename());
			pst.setString(2, imgbeans.getName());
			pst.setString(3, imgbeans.getComment());
			pst.setBytes(4, imgbeans.getImgdata());

			// SQLを実行し、結果を受け取る
			int rec = pst.executeUpdate();
			// 件数が1以上のときはtrue,それ以外はfalse
			if (rec == 1) {
				return true;
			} else {
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	public static List<ImgBeans> selectAll() {
		// 画像データ以外を取得するメソッド
		List<ImgBeans> imagesList = new ArrayList<>();

		try (Connection con = DriverManager.getConnection(jdbc_url, db_user, db_pass);
				PreparedStatement pst = con.prepareStatement("SELECT * FROM postimg ");
				ResultSet rs = pst.executeQuery();) {
			// 全権取得するまで繰り返しリストに格納
			while (rs.next()) {
				String filename = rs.getString(1);
				String name = rs.getString(2);
				String comment = rs.getString(3);
				ImgBeans img = new ImgBeans(filename, name, comment);
				imagesList.add(img);
			}

		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return imagesList;
	}

	public static byte[] selectImg(String filename) {
		// 画像を取得するためのメソッド。byte[]を戻り値にする
		// selectAll()で取得したファイル名を条件にして画像データを取得
		try (Connection con = DriverManager.getConnection(jdbc_url, db_user, db_pass);
				//PreparedStatementで、ResultSetのスクロールタイプを変更するとfirstが使用できる
				PreparedStatement pst = 
						con.prepareStatement("SELECT imgdata FROM postimg WHERE filename = ? ",
								ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);) {

			pst.setString(1, filename);
			ResultSet rs = pst.executeQuery();
			// byte[]へデータを格納。ResultSetには1行か0行が格納されているため、最初の行のみ読み込み
			if (rs.first()) {
				byte[] imgdata = rs.getBytes("imgdata");
				return imgdata;
			}else {
				//その名前のファイル名がなければnullをかえす
				return null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
}
