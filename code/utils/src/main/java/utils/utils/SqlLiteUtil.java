/**
 * Project Name:test
 * File Name:SqlLiteUtil.java
 * Package Name:test
 * Date:2014年10月20日上午9:08:46
 * Copyright (c) 2014, sid Jenkins All Rights Reserved.
 * 
 *
*/

package utils.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.slf4j.Logger;

/**
 * ClassName:SqlLiteUtil
 * Function: TODO ADD FUNCTION. 
 * Reason:	 TODO ADD REASON. 
 * Date:     2014年10月20日 上午9:08:46 
 * @author   sid
 * @see 	 
 */
public class SqlLiteUtil {

	private static Logger logger = LogUtil.getInstance().getLogger(SqlLiteUtil.class);
	
	
	/**
	 * 
	 * tableExist:(判断当前表是否存在). 
	 *
	 * @author sid
	 * @param stmt
	 * @param tablename
	 * @return
	 */
	public static boolean tableExist(Statement stmt,String tablename){
		ResultSet result = null;
		try {
			result = stmt.executeQuery("select count(*) from sqlite_master where type='table' and name = 'develop'");
			while(result.next()){
				if (result.getInt(1)==1) {
					return true;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			if (result!=null) {
				try {
					result.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return false;
	}
	
	/**
	 * 
	 * init:(初始化oauth认证需要的表). 
	 *
	 * @author sid
	 * @param stmt
	 */
	public static void initTables(Statement stmt){
		try {
			//develop,updatetime
			if(!tableExist(stmt,"develop")){
				logger.info("初始化Oauth数据库");
				//生成develop表
				stmt.execute("CREATE TABLE develop (id INTEGER PRIMARY KEY,project_name varchar(250) NOT NULL,"
						+ "redirect_uri varchar(250) NOT NULL,client_id char(11),client_secret varchar(250) ,"
						+ "project_logo varchar(250) ,project_state varchar(250) )");
				//插入数据
				stmt.execute("INSERT INTO develop VALUES ('1', '恒达时讯', 'hdsx', '68714962', null, null, '-1')");
				//生成develop表
				stmt.execute("CREATE TABLE updatetime (id INTEGER PRIMARY KEY,cxlx varchar(1) ,cxsj varchar(50) )");
				//插入数据
				stmt.execute("INSERT INTO updatetime VALUES ('1', '1', '')");
				stmt.execute("INSERT INTO updatetime VALUES ('2', '2', '')");
				stmt.execute("INSERT INTO updatetime VALUES ('3', '3', '')");
				//用SQL语句读出数据
//				ResultSet result = stmt.executeQuery("select * from develop");
//				while(result.next()){
//					System.out.println(result.getString(1));
//					System.out.println(result.getInt(2));
//				}
//				result = stmt.executeQuery("select * from updatetime");
//				while(result.next()){
//					System.out.println(result.getString(1));
//					System.out.println(result.getString(2));
//					System.out.println(result.getString(3));
//				}
			}else{
				logger.info("Oauth数据库已存在");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	

	public static void main(String[] args) {
		Connection conn = null;
		Statement stmt = null;
		try {
			//声明JDBC驱动程序
			Class.forName("org.sqlite.JDBC");
			//连接数据库
			conn = DriverManager.getConnection("jdbc:sqlite:/D:/sid/work/workspace_git/oauth2/database/oauth.db");
			stmt = conn.createStatement();
			//develop,updatetime
			if(!tableExist(stmt,"develop")){
				initTables(stmt);
			}
			ResultSet rs = stmt.executeQuery("select * from updatetime");
			while (rs.next()) {
				logger.info(rs.getString(1)+":"+rs.getString(3));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			JDBCTool.close(conn, stmt);
		}
	}
}

