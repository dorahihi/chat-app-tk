package com.chatApp.sp.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@CrossOrigin
public class MainController {
	
	@GetMapping("/login")
	public String loginPage(Model model) {
		System.out.println("login:  ++++++++++");
		model.addAttribute("type", "login");
		return "login";
	}
	@GetMapping("/")
	public String wellcome(Model model) {
		System.out.println("home: +++++++++++++");
		model.addAttribute("type", "");
		return "login";
	}
	@GetMapping("/signup")
	public String signup(Model model) {
		System.out.println("signup: +++++++++++++++");
		model.addAttribute("type", "signup");
		return "login";
	}	
	
}
