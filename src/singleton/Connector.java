package singleton;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connector {
	
	public static Connection conn;
	
	/** @throws new AssertionError();
	 */
	private Connector() {
		throw new AssertionError();
	}

	public static Connection getConnection() {
		if(conn == null) {
			//다리 만들기
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");

				String url = "jdbc:mysql://localhost:3306/instagram";
				String user = "root";
				String password = "1234";

				conn = DriverManager.getConnection(url, user, password);
			} catch (ClassNotFoundException e) {
				System.out.println("Connector.java | 드라이버 로딩 실패 : "+e);
			} catch(SQLException sqle) {
				System.out.println("Connector.java | DB 연결 실패 : "+sqle);
			}
		}
		return conn;
	}
}
