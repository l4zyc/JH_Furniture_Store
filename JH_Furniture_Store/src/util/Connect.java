package util;
import java.sql.*;

public class Connect {
	
	private final String USERNAME = "root";
	private final String PASSWORD = "";
	private final String HOST = "localhost:3306";
	private final String DATABASE = "jh_furniture";
	private final String CONNECTION = String.format("jdbc:mysql://%s/%s",HOST, DATABASE);
	
	private Connection con;
	private Statement st;
	private static Connect connect;
	
	public ResultSet rs;
	public ResultSetMetaData rsm;
	
	public static  Connect getInstance()  {
		if(connect == null)
			try {
				return new Connect();
			} catch (Exception e) {
				e.printStackTrace();
			}
		return connect;
	}
	
	private Connect() throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		con = DriverManager.getConnection(CONNECTION, USERNAME, PASSWORD);
		st = con.createStatement();
	}
	
	public ResultSet execQuery(String query) {
		try {
			rs = st.executeQuery(query);
			rsm = rs.getMetaData();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return rs;
	}

}
