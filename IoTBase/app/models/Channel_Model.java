package models;

import java.sql.*;
import java.util.*;
import play.db.*;

public class Channel_Model {
	public String id, id_profil, nama, deskripsi, read_access_key,  write_access_key, read_write_access_key;
	public int row_count;
	private Connection Conn;
	private PreparedStatement Stat;
	private ResultSet executeQuery;
	
	public Channel_Model() {
		id = null;
		id_profil = null;
		nama = null;
		deskripsi = null;
		read_access_key = null;
		write_access_key = null;
		read_write_access_key = null;
	}

	public void renderList(List<TabelChannel> tabel, String SQL) {
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
						TabelChannel data_tabel = new TabelChannel();
						data_tabel.id = executeQuery.getString("id");
						data_tabel.id_profil = executeQuery.getString("id_profil");
						data_tabel.nama = executeQuery.getString("nama");
						data_tabel.deskripsi = executeQuery.getString("deskripsi");
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
		
	}
	
	public void insert() {
		try {
			Conn = DB.getConnection();
			Stat = Conn.prepareStatement("INSERT INTO channel VALUES(?, ?, ?, ?, ?, ?, ?)");
			Stat.setString(1, id);
			Stat.setString(2, id_profil);
			Stat.setString(3, nama);
			Stat.setString(4, deskripsi);
			Stat.setString(5, read_access_key);
			Stat.setString(6, write_access_key);
			Stat.setString(7, read_write_access_key);
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
	
	public boolean existsById() {
		try {
			Conn = DB.getConnection();
			Stat = Conn.prepareStatement("SELECT * FROM channel WHERE id= BINARY ?");
			Stat.setString(1, id);
			executeQuery = Stat.executeQuery();
			try {
				executeQuery.last();
				row_count = executeQuery.getRow();
				executeQuery.first();
				if(row_count == 1) {
					id = executeQuery.getString("id");
					id_profil = executeQuery.getString("id_profil");
					nama = executeQuery.getString("nama");
					deskripsi = executeQuery.getString("deskripsi");
					read_access_key = executeQuery.getString("read_access_key");
					write_access_key = executeQuery.getString("write_access_key");
					read_write_access_key = executeQuery.getString("read_write_access_key");
					return true;
				}else {
					id = null;
					id_profil = null;
					nama = null;
					deskripsi = null;
					read_access_key = null;
					write_access_key = null;
					read_write_access_key = null;
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
