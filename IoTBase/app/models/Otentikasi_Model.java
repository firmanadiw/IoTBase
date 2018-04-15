package models;

import java.sql.*;
import play.db.*;

public class Otentikasi_Model {
	public String id, username, nama, password;
	public int row_count;
	private Connection Conn;
	private PreparedStatement Stat;
	private ResultSet executeQuery;
	
	public Otentikasi_Model(){
		id = null;
		username = null;
		nama = null;
		password = null;
	}
	
	public boolean cek(String session_username, String session_password) {
		if((session_username != null) && (session_password != null)) {
			try {
				Conn = DB.getConnection();
				Stat = Conn.prepareStatement("SELECT * FROM profil WHERE username= BINARY ? AND password= BINARY ?");
				Stat.setString(1, session_username);
				Stat.setString(2, session_password);
				executeQuery = Stat.executeQuery();
				try {
					executeQuery.last();
					row_count = executeQuery.getRow();
					executeQuery.first();
					if(row_count == 1) {
						id = executeQuery.getString("id");
						username = executeQuery.getString("username");
						nama = executeQuery.getString("nama");
						password = executeQuery.getString("password");
						return true;
					}else {
						id = null;
						username = null;
						nama = null;
						password = null;
					}
				}finally {
					Conn.close();
					Stat.close();
					executeQuery.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return false;
	}
}
