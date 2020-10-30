package com.chatApp.sp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
	
	@GetMapping("/login")
	public String loginPage(Model model) {
		model.addAttribute("type", "login");
		return "login";
	}
	@GetMapping("/")
	public String wellcome(Model model) {
		model.addAttribute("type", "");
		return "login";
	}
	@GetMapping("/signup")
	public String signup(Model model) {
		model.addAttribute("type", "signup");
		return "login";
	}
	@GetMapping("/error")
	public String error() {
			return "login";
	}
	
	
}
