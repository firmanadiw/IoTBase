package controllers;

import models.TabelChannel;
import play.*;
import play.mvc.*;
import java.util.*;

import models.Otentikasi_Model;
import models.Channel_Model;
import models.ChannelData_Model;
import models.TabelChannel;
import helpers.Server;

import views.html.*;

import play.data.DynamicForm;
import play.data.Form;

public class Channel extends Controller{
	 public static Result index() { 
		Otentikasi_Model otentikasi = new Otentikasi_Model();
		if(otentikasi.cek(session("signin_username"), session("signin_password")) == true) {
			DynamicForm dynamicForm = Form.form().bindFromRequest();
			if(dynamicForm.get("data") != null) {
				if(dynamicForm.get("data").indexOf("tambah") == 0){
					return ok(views.html.tambah_channel.render());
				}else{
					return ok("");
				}
			}else
			if(dynamicForm.get("tambah_channel") != null) {
				if(dynamicForm.get("deskripsi_channel") != null) {
					Server server = new Server();
					Channel_Model channel = new Channel_Model();

					channel.id = server.randString("CH", 15);
					channel.id_profil = otentikasi.id;
					channel.nama = dynamicForm.get("tambah_channel");
					channel.deskripsi = dynamicForm.get("deskripsi_channel");
					channel.read_access_key = server.randString("KR", 15);
					channel.write_access_key = server.randString("KW", 15);
					channel.read_write_access_key = server.randString("KRW", 15);
					channel.insert();
					return ok(views.html.row_tabel_channel.render(channel.id, dynamicForm.get("tambah_channel"), "0"));
				}else{
					return ok("");
				}
			}else
			if(dynamicForm.get("deskripsi_channel") != null) {
				Channel_Model channel = new Channel_Model();
				channel.id = dynamicForm.get("deskripsi_channel");
				if(channel.existsById() == true){
					if(channel.deskripsi != "") {
						return ok(channel.deskripsi);
					}else{
						return ok("Tidak Ada Deskripsi.");
					}
				}else {
					return ok("");
				}
			}else
			if(dynamicForm.get("access_key_channel") != null) {
				Channel_Model channel = new Channel_Model();
				channel.id = dynamicForm.get("access_key_channel");
				if(channel.existsById() == true){
					return ok(views.html.access_key_channel.render(channel.read_access_key, channel.write_access_key, channel.read_write_access_key));
				}else {
					return ok("");
				}
			}else{
				Channel_Model channel = new Channel_Model();

				List<TabelChannel> tabel = new ArrayList<TabelChannel>();
				channel.renderList(tabel, String.format("SELECT * FROM channel WHERE id_profil= BINARY '%s' ORDER BY nama ASC", otentikasi.id));
				return ok(views.html.channel.render(tabel));
			}
		}else{
			return ok(views.html.access_denied.render());
		}
	 }
	 
	 public static Result data() {
		 Otentikasi_Model otentikasi = new Otentikasi_Model();
		 if(otentikasi.cek(session("signin_username"), session("signin_password")) == true) {
			 DynamicForm dynamicForm = Form.form().bindFromRequest();
			 if(dynamicForm.get("id") != "") {
				 ChannelData_Model data;
				 data = new ChannelData_Model();
				 data.id_channel = dynamicForm.get("id");
				 return ok(views.html.channel_data.render(data));
			 }else{
				 return ok("Channel Tidak Ditemukan.");
			 }
		 }else{
			 return ok(views.html.access_denied.render());
		 }
	 }
}
