package com.mkyong.web.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.tomcat.jni.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.aman.beans.UserClass;

@Controller
public class HelloController {
	
	List<UserClass> userList;
	
	public HelloController() {
		// TODO Auto-generated constructor stub
		userList=new ArrayList<UserClass>();
	}
	 
	
	@RequestMapping(value = { "/", "/welcome**" }, method = RequestMethod.GET)
	public ModelAndView welcomePage() {

		ModelAndView model = new ModelAndView();
		model.addObject("title", "Spring Security Custom Login Form");
		model.addObject("message", "This is welcome page!");
		model.setViewName("hello");
		return model;

	}

	@RequestMapping(value = "/admin**", method = RequestMethod.GET)
	public ModelAndView adminPage() {

		ModelAndView model = new ModelAndView();
		model.addObject("title", "Spring Security Custom Login Form");
		model.addObject("message", "This is protected page! you can add user here");
		model.setViewName("admin");

		return model;

	}
	@RequestMapping(value="/user", method =RequestMethod.GET )
	public ModelAndView userPage(){
		ModelAndView model=new ModelAndView();
		model.addObject("title","User Page");
		model.addObject("message","User Page............");
		model.setViewName("user");
		return model;
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView login(@RequestParam(value = "error", required = false) String error,
			@RequestParam(value = "logout", required = false) String logout) {

		ModelAndView model = new ModelAndView();
		if (error != null) {
			model.addObject("error", "Invalid username and password!");
		}

		if (logout != null) {
			model.addObject("msg", "You've been logged out successfully.");
		}
		model.setViewName("login");

		return model;

	}

	@RequestMapping(value = "/addUser", method = RequestMethod.POST)
	public ModelAndView addUser(@RequestParam(value = "username", required = true) String userName,
			@RequestParam(value = "logout", required = false) String logout,@RequestParam(value="password", required=true) String password) {

		ModelAndView model = new ModelAndView();
		if (userName != null&&password!=null) {

			UserClass usr=new UserClass();
			model.addObject("message", "user has been created suceessFully");
			usr.setUserName(userName);
			userList.add(usr);
		}
		else{
			model.addObject("error", "username and password can not be blank");
		}

		if (logout != null) {
			model.addObject("msg", "You've been logged out successfully.");
		}
		model.setViewName("admin");

		return model;

	}

}