// �N���u�Y���Ѹ�Ʈw���X,�t�s�����ɮ�
import java.sql.*;
import java.io.*;

public class PhotoRead {
	public static void main(String argv[]) {
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;

		String url = "jdbc:mysql://localhost:3306/XE";

		try {
			con = DriverManager.getConnection(url, "root", "kent1011");
			System.out.println("Ū���Ϥ����...");
			stmt = con.createStatement();
			rs = stmt.executeQuery("SELECT * from emp_photo");
			System.out.println("Received results:");

			while (rs.next()) {
				String employee_id = rs.getString("employee_id");
				String photo_format = rs.getString("photo_format");
				InputStream in = rs.getBinaryStream("picture");

				File file = new File(
						"C:\\Users\\Murray\\workspace_luna\\JDBC_Photo\\picReaded",
						employee_id + "." + photo_format);
				OutputStream out = new FileOutputStream(file);

				byte[] buffer = new byte[4 * 1024];
				int len = 0;
				while ((len = in.read(buffer)) != -1) {
					out.write(buffer, 0, len);
				}

				in.close();
				out.close();

				System.out.print(" Employee_id = " + employee_id);
				System.out.print(", PHOTO_FORMAT = " + photo_format);
				System.out.println(", �ɮ׿�X���| = " + file.getPath());
			}

			rs.close();
			stmt.close();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
			}
		}
	}
}