package com.chatApp.sp.controller;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
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
	@GetMapping("/accessDenied")
	public String error(Model model) {
		System.out.println("error: +++++++++++++++");
		model.addAttribute("type", "login");
			return "login";
	}
	
	@RequestMapping("/error")
	public String handleError(HttpServletRequest request, Model model) {
	    Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
	    
	    if (status != null) {
	        Integer statusCode = Integer.valueOf(status.toString());
	    
	        if(statusCode == HttpStatus.NOT_FOUND.value()) {
	        	model.addAttribute("type", "404");
	            return "error";
	        }
	        else if(statusCode == HttpStatus.INTERNAL_SERVER_ERROR.value()) {
	        	model.addAttribute("type", "500");
	            return "error-500";
	        }
	    }
	    return "error";
	}
	
	
}
