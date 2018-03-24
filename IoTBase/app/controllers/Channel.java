package controllers;

import play.*;
import play.mvc.*;

import models.Channel_Model;
import models.ChannelData_Model;
import views.html.*;

import play.data.DynamicForm;
import play.data.Form;

public class Channel extends Controller{
	 public static Result index() { 
	    return ok(views.html.channel.render());
	 }
	 
	 public static Result data() {
		 DynamicForm dynamicForm = Form.form().bindFromRequest();
		 if(dynamicForm.get("id") != "") {
			 ChannelData_Model data;
			 data = new ChannelData_Model();
			 data.id_channel = dynamicForm.get("id");
			 return ok(views.html.channel_data.render(data));
		 }else {
			 return ok("Channel Tidak Ditemukan.");
		 }
	 }
}
