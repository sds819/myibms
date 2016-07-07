package com.cesecsh.util;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DbUtil {

	Connection conn = null;
	Statement st = null;
	ResultSet rs = null;
	public static void dbClose(Connection[] conns,Statement[] sts,ResultSet[] rses ) throws SQLException{
		for(ResultSet rs:rses){
			if(rs!=null)
			rs.close();
		}
		for(Statement st:sts){
			st.close();
		}
		for(Connection conn:conns){
			conn.close();
		}
	}
	public static void dbClose(Connection conn,Statement st,ResultSet rs ) throws SQLException{
		
			if(rs!=null)
			rs.close();
			if(st!=null)
			st.close();
			if(conn!=null)	
			conn.close();
		
	}
}
