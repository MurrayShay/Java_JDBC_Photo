// 將圖片檔案存入資料庫,成為員工頭像
import java.sql.*;
import java.io.*;

public class PhotoWrite {
	public static void main(String argv[]) {
       Connection con = null;
	   PreparedStatement pstmt = null;
	   String picName = "漂亮嗎.jpg";  //圖檔
	   //String url = 
       //"jdbc:sqlserver://localhost:1433;databaseName=XE;user=hr;password=hr;";
	   String url = "jdbc:mysql://localhost:3306/XE";
	   try {
		   con = DriverManager.getConnection(url,"root","kent1011");
		   File pic = new File("C:\\Users\\Murray\\workspace_luna\\JDBC_Photo\\picFrom", picName);
		   long flen = pic.length();
		   String fileName = pic.getName();
		   int dotPos = fileName.indexOf('.');
		   String fno = fileName.substring(0, dotPos);
		   String format = fileName.substring(dotPos + 1);
		   InputStream fin = new FileInputStream(pic);  //輸出資料流

      	   System.out.println("\n\n將圖片資料存入資料表中... ");
		   pstmt =	con.prepareStatement(
		   "insert into emp_photo (employee_id, photo_format, picture)  values(?, ?, ?)");
		   pstmt.setString(1, fno);
		   pstmt.setString(2, format);
		   pstmt.setBinaryStream(3, fin, (int)flen);  
		   //void pstmt.setBinaryStream(int parameterIndex, InputStream x, int length) throws SQLException
		   int rowsUpdated = pstmt.executeUpdate();
			
		   System.out.print("已存入 " + rowsUpdated);

		   if (1 == rowsUpdated)
			   System.out.println(" row.");
		   else
			   System.out.println(" rows.");

		   pstmt.close();

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