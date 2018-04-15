package controllers;

import models.Channel_Model;
import models.TabelChannel;
import play.*;
import play.mvc.*;

import models.Otentikasi_Model;

import views.html.*;

import java.util.*;

public class Application extends Controller {
    public static Result index() {
    	Otentikasi_Model otentikasi = new Otentikasi_Model();
	    if(otentikasi.cek(session("signin_username"), session("signin_password")) == true) {
			Channel_Model channel = new Channel_Model();

			List<TabelChannel> tabel = new ArrayList<TabelChannel>();
			channel.renderList(tabel, String.format("SELECT * FROM channel WHERE id_profil= BINARY '%s' ORDER BY nama ASC", otentikasi.id));

	    	return ok(views.html.index.render(otentikasi, tabel));
	    }else {
	    	session("redirect_link", "/");
	    	return redirect("/signin");
	    }
    }

}
