package controllers;

import play.*;
import play.mvc.*;

import views.html.*;
import play.data.DynamicForm;
import play.data.Form;
import org.mindrot.jbcrypt.BCrypt;

import models.Profil_Model;
import helpers.Server;

public class Registrasi extends Controller{
	public static Result index() {
		DynamicForm dynamicForm = Form.form().bindFromRequest();
		String message = "";
		if((dynamicForm.get("username") != null) && 
		  (dynamicForm.get("nama") != null) && 
		  (dynamicForm.get("password_baru") != null) &&
		  (dynamicForm.get("konfirmasi_password_baru") != null) &&
		  (dynamicForm.get("registrasi") != null) &&
		  (dynamicForm.get("username") != "") && 
		  (dynamicForm.get("nama") != "") && 
		  (dynamicForm.get("password_baru") != "") &&
		  (dynamicForm.get("konfirmasi_password_baru") != "")) {	
			Profil_Model profil = new Profil_Model();
			profil.username = dynamicForm.get("username");
			if(profil.existsByUsername() == false) {
				Server server =  new Server();
				profil.id = server.randString("USR", 15);
				profil.username = dynamicForm.get("username");
				profil.nama = dynamicForm.get("nama");
				profil.password = BCrypt.hashpw(dynamicForm.get("password_baru"), BCrypt.gensalt());
				profil.insert();
				return ok(views.html.registrasi_ok.render());
			}else {
				message = "Username Sudah Ada.";
				return ok(views.html.registrasi.render(message));
			}
		}else
		if(dynamicForm.get("check_username") != null){
			Profil_Model profil = new Profil_Model();
			profil.username = dynamicForm.get("check_username");
			if(profil.existsByUsername() == false) {
				return ok("true");
			}else {
				return ok("false");
			}
		}else {
			return ok(views.html.registrasi.render(message));
		}
	}
}
