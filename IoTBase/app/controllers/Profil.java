package controllers;

import play.*;
import play.mvc.*;

import views.html.*;


public class Profil extends Controller{
    public static Result index() {
    	return ok(views.html.profil.render());
    }
}