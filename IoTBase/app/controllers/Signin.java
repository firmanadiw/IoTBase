package controllers;

import play.*;
import play.mvc.*;

import views.html.*;
import play.data.DynamicForm;
import play.data.Form;
import org.mindrot.jbcrypt.BCrypt;

import models.Otentikasi_Model;
import models.Profil_Model;

public class Signin extends Controller{
	public static Result index() {
		Otentikasi_Model otentikasi = new Otentikasi_Model();
	    if(otentikasi.cek(session("signin_username"), session("signin_password")) == false) {
			DynamicForm dynamicForm = Form.form().bindFromRequest();
			String message = "";
			if((dynamicForm.get("username") != null) && 
			   (dynamicForm.get("password") != null) && 
			   (dynamicForm.get("signin") != null)) {
				Profil_Model profil = new Profil_Model();
				profil.username = dynamicForm.get("username");
				if(profil.existsByUsername() == true) {
					if(BCrypt.checkpw(dynamicForm.get("password"), profil.password) == true) {
						session("signin_username", profil.username);
						session("signin_password", profil.password);
						if(session("redirect_link") != null){
							return redirect(session("redirect_link"));
						}else {
							return redirect("/");
						}
					}else{
						message = "Username Atau Password Salah. ";
						return ok(views.html.signin.render(message));
					}
				}else{
					message = "Username Atau Password Salah. ";
					return ok(views.html.signin.render(message));
				}
			}else{
				return ok(views.html.signin.render(message));
			}
	    }else{
	    	return redirect("/");
	    }
    }
}
