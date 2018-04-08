package controllers;

import play.*;
import play.mvc.*;

import views.html.*;
import java.util.*;

import models.SensorType;

public class Application extends Controller {
	@play.db.ebean.Transactional
    public static Result index() {
        List daftar = SensorType.findAll();
        return ok(index.render(daftar));
    }

}
