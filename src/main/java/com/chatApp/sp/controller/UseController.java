package com.chatApp.sp.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.chatApp.sp.model.DBUser;
import com.chatApp.sp.utils.UserUtils;

@RestController
public class UseController {
	
	@Autowired
	UserUtils userUtils;
	
	@PostMapping("/signup")
	public String createAccount(@RequestParam("email") String email, @RequestParam("password") String password, 
								@RequestParam("userName") String userName,
								@RequestParam("age") String age,
								@RequestParam("gender") String gender) {
		return  userUtils.createUser(email, userName, password, age, gender);
	}
	
	@GetMapping("/users/{email}")
	public DBUser viewUserProfile(@PathVariable("email") String email) {
		System.out.println("get user "+ email);
		return userUtils.viewUserProfile(email);
	}
	
	@PostMapping("/users/edit")
	public String editUserProfile(@RequestParam("password") String password,
								  @RequestParam("userName") String userName,
								  @RequestParam("age") String age,
								  HttpServletRequest req) {
		return userUtils.updateUserProfile(password, userName, age, req);
	}
	
	@GetMapping("/users/delete")
	public String deleteAccount(HttpServletRequest req) {
		return userUtils.deleteAccount(req);
	}	
}
