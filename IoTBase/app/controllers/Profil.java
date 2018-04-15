package controllers;

import play.*;
import play.data.DynamicForm;
import play.data.Form;
import play.mvc.*;
import org.mindrot.jbcrypt.BCrypt;

import java.util.*;

import models.Otentikasi_Model;
import models.Profil_Model;
import models.TabelProfil;

import views.html.*;

public class Profil extends Controller{
    public static Result index() {
        Otentikasi_Model otentikasi = new Otentikasi_Model();
        if(otentikasi.cek(session("signin_username"), session("signin_password")) == true) {
            DynamicForm dynamicForm = Form.form().bindFromRequest();
            if(dynamicForm.get("edit") != null){
                if(dynamicForm.get("edit").indexOf("nama") == 0){
                    return ok(views.html.edit_nama_profil.render(otentikasi));
                }else
                if(dynamicForm.get("edit").indexOf("username") == 0){
                    return ok(views.html.edit_username_profil.render(otentikasi));
                }else
                if(dynamicForm.get("edit").indexOf("password") == 0){
                    return ok(views.html.edit_password_profil.render(otentikasi));
                }else {
                    return ok("");
                }
            }else
            if(dynamicForm.get("edit_nama") != null) {
                Profil_Model profil = new Profil_Model();
                profil.id = otentikasi.id;
                profil.username = otentikasi.username;
                profil.nama = dynamicForm.get("edit_nama");
                profil.password = otentikasi.password;
                profil.update();
                return ok(profil.nama);
            }else
            if(dynamicForm.get("edit_username") != null) {
                Profil_Model profil = new Profil_Model();
                profil.username = dynamicForm.get("edit_username");
                if ((profil.existsByUsername() == false) || (profil.id.indexOf(otentikasi.id) == 0)){
                    profil.id = otentikasi.id;
                    profil.username = dynamicForm.get("edit_username");
                    profil.nama = otentikasi.nama;
                    profil.password = otentikasi.password;
                    profil.update();
                    session("signin_username", profil.username);
                    return ok(profil.username);
                }else{
                    return ok("");
                }
            }else
            if(dynamicForm.get("check_username") != null) {
                Profil_Model profil = new Profil_Model();
                profil.username = dynamicForm.get("check_username");
                if ((profil.existsByUsername() == false) || (profil.id.indexOf(otentikasi.id) == 0)){
                    return ok("true");
                } else {
                    return ok("false");
                }
            }
            if((dynamicForm.get("edit_password") != null) && (dynamicForm.get("password_lama") != null)){
                if(BCrypt.checkpw(dynamicForm.get("password_lama"), session("signin_password")) == true){
                    Profil_Model profil = new Profil_Model();
                    profil.id = otentikasi.id;
                    profil.username = otentikasi.username;
                    profil.nama = otentikasi.nama;
                    profil.password = BCrypt.hashpw(dynamicForm.get("edit_password"), BCrypt.gensalt());
                    profil.update();
                    session("signin_password", profil.password);
                    return ok("true");
                }else{
                    return ok("false");
                }
            }else{
                return ok(views.html.profil.render(otentikasi));
            }
        }else {
            return ok(views.html.access_denied.render());
        }
    }
}
