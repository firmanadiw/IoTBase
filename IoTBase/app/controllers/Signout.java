package controllers;

import play.*;
import play.mvc.*;
import views.html.*;

import play.data.DynamicForm;
import play.data.Form;

public class Signout extends Controller{
	public static Result index() {
		DynamicForm dynamicForm = Form.form().bindFromRequest();
		if(dynamicForm.get("signout") != null)
		{
			session().clear();
			return redirect("/signin");
		}else {
			return ok("");
		}
	}
}
