package db;

import java.sql.*;

public class Connect {
	// CREDENTIALS
	private final String USERNAME = "root";
	private final String PASSWORD = "";
	private final String DATABASE_NAME = "todo";
	private final String HOST = "localhost";
	private final String PORT = "3306";
	private final String CONNECTION = String.format("jdbc:mysql://%s:%s/%s", HOST, PORT, DATABASE_NAME);
	private Connection con;
	private Statement stmt;
	private static Connect instance = new Connect();

	private Connect() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(CONNECTION, USERNAME, PASSWORD);
			stmt = con.createStatement();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static Connect getInstance() {
		return instance;
	}

	public PreparedStatement prepareStatement(String query) {
		PreparedStatement ps = null;
		try {
			ps = con.prepareStatement(query);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ps;
	}

}