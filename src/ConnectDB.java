import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ConnectDB {
	static Connection conn;
	static Statement stmt;

	// connect from java to sql
	public void connect() {
		try {
			// load driver
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			// start connecting EmployeeDB database
			conn = DriverManager.getConnection("jdbc:sqlserver://HP;databaseName=Company;user=sa;password=sa");
			System.out.println("Connected");

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

	}

	public int executeDB(String sql) { // insert, update, delete
		int record = 0;
		try {
			connect();
			stmt = conn.createStatement();
			record = stmt.executeUpdate(sql);

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		return record;
	}
	
	public ResultSet GetData(String jtable, String sql) throws SQLException {
		ResultSet rs = null;
		Statement st = conn.createStatement();
		rs = st.executeQuery(sql);
		return rs;
	}
}