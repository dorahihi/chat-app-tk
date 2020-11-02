package com.chatApp.sp.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.chatApp.sp.repository.UserRepository;
import com.chatApp.sp.utils.CookieUtils;
import com.chatApp.sp.utils.FriendUtils;

@RestController
public class FriendController {

	@Autowired
	UserRepository userRepos;
	
	@Autowired
	CookieUtils cookieUtils;
	
	@Autowired
	FriendUtils friendUtils;

	
	@PostMapping("/friends/accept/{email}")
	public String accepyFriendRequest(@PathVariable("email") String email, HttpServletRequest req) {
		
		return friendUtils.acceptFriendRequest(email, req);
	}
	
	@GetMapping("/friends/add/{email}")
	public String sendFriendRequest(@PathVariable("email") String friendEmail, HttpServletRequest req) {
		return friendUtils.sendFriendRequest(friendEmail, req);
	}
	
	@GetMapping("/friends")
	public Map<String, String> viewFriendList(HttpServletRequest req) {
		return friendUtils.viewFriendlist(req);
	}
	
	@DeleteMapping("friends/remove/{email}")
	public String removeFriend(@PathVariable("email") String email, HttpServletRequest req) {
		return friendUtils.removeFriend(email, req);
	}
	
	@GetMapping("/friends/add/requests")
	public Map<String, String> viewFriendRequestList(HttpServletRequest req){
		return friendUtils.viewFriendlist(req);
	}
	
	
}
