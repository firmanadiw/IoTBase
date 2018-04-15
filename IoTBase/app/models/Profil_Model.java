package models;

import java.sql.*;
import java.util.*;

import play.db.*;

public class Profil_Model {
	public String id, username, nama, password;
	public int row_count;
	private Connection Conn;
	private PreparedStatement Stat;
	private ResultSet executeQuery;
	
	public Profil_Model(){
		id = null;
		username = null;
		nama = null;
		password = null;
	}
	
	public void renderList(List<TabelProfil> tabel, String SQL) {
		try {
			tabel.clear();
			Conn = DB.getConnection();
			Stat = Conn.prepareStatement(SQL);
			executeQuery = Stat.executeQuery();
			try {
				executeQuery.last();
				row_count = executeQuery.getRow();
				executeQuery.first();
				if(row_count > 0)
				{
					do{
						TabelProfil data_tabel = new TabelProfil();
						data_tabel.id = executeQuery.getString("id");
						data_tabel.username = executeQuery.getString("username");
						data_tabel.nama = executeQuery.getString("nama");
						data_tabel.password = executeQuery.getString("password");
						tabel.add(data_tabel);
					}while(executeQuery.next());
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
	
	public void update() {
		try {
			Conn = DB.getConnection();
			Stat = Conn.prepareStatement("UPDATE profil SET id=?, username=?, nama=?, password=? WHERE id= BINARY ?");
			Stat.setString(1, id);
			Stat.setString(2, username);
			Stat.setString(3, nama);
			Stat.setString(4, password);
			Stat.setString(5, id);
			Stat.executeUpdate();
			try {
			}finally{
				Conn.close();
				Stat.close();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void insert() {
		try {
			Conn = DB.getConnection();
			Stat = Conn.prepareStatement("INSERT INTO profil VALUES(?, ?, ?, ?)");
			Stat.setString(1, id);
			Stat.setString(2, username);
			Stat.setString(3, nama);
			Stat.setString(4, password);
			Stat.executeUpdate();
			try {
			}finally{
				Conn.close();
				Stat.close();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void delete() {
		
	}
	
	public boolean existsByUsername() {
		try {
			Conn = DB.getConnection();
			Stat = Conn.prepareStatement("SELECT * FROM profil WHERE username= BINARY ?");
			Stat.setString(1, username);
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
			}finally{
				Conn.close();
				Stat.close();
				executeQuery.close();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return false;
	}
}
