package utils.utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCTool {

	public static void close(Connection conn, Statement stat , ResultSet rs) {
		if (rs!=null)
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		if (stat!=null)
			try {
				stat.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		if (conn!=null)
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
	}
	
	public static void close(Connection conn) {
		close(conn, null, null);
	}
	
	public static void close(Statement stat) {
		close(null, stat, null);
	}
	
	public static void close(Statement stat, ResultSet rs) {
		close(null, stat, rs);
	}
	
	public static void close(PreparedStatement stat, ResultSet rs) {
		close(null, stat, rs);
	}
	
	public static void close(PreparedStatement stat) {
		close(null, stat, null);
	}
	
	public static void close(Connection conn, Statement stat) {
		close(conn, stat, null);
	}
	
	
	public static void close(Connection conn, PreparedStatement stat) {
		close(conn, stat, null);
	}
	
	
	public static void close(Connection conn, PreparedStatement stat , ResultSet rs) {
		if (rs!=null)
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		if (stat!=null)
			try {
				stat.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		if (conn!=null)
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
	}
	
}
