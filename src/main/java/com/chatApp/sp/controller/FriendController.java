package com.chatApp.sp.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.chatApp.sp.repository.UserRepository;
import com.chatApp.sp.service.CookieServices;
import com.chatApp.sp.service.FriendServices;

@RestController
@CrossOrigin //for testing
public class FriendController {

	@Autowired
	UserRepository userRepos;
	
	@Autowired
	CookieServices cookieServices;
	
	@Autowired
	FriendServices friendServices;

	/*
	 * {email} là biến thay đổi theo từng người
	 * vd: /friend/accept/khang -> email: khang, có nghĩa là tài khoản người gửi request đã chấp nhận kết bạn với tài khoản có email là khang
	 */
	//chấp nhận kết bạn +
	@PostMapping("/friends/accept")
	public String accepyFriendRequest(@RequestParam("friendEmail") String friendEmail, @RequestParam("email") String email, HttpServletRequest req) throws Exception {
		
		return friendServices.acceptFriendRequest(friendEmail,email, req);
	}
	
	
	//gửi lời mời kết bạn +
	@PostMapping("/friends/add")
	public String sendFriendRequest(@RequestParam("friendEmail") String friendEmail, @RequestParam("email") String email,HttpServletRequest req) throws Exception {
		return friendServices.sendFriendRequest(friendEmail,email, req);
	}
	
	
	//xem danh sách bạn +
	@GetMapping("/friends")
	public Map<String, String> viewFriendList(@RequestParam("email") String email,HttpServletRequest req) {
		return friendServices.viewFriendlist(email,req);
	}
	
	//xoá bạn (như cái chấp nhận lời mời kết bạn) +
	@DeleteMapping("/friends/remove")
	public String removeFriend(@RequestParam("friendEmail") String friendEmail, @RequestParam("email") String email, HttpServletRequest req) throws Exception {
		return friendServices.removeFriend(friendEmail, email, req);
	}
	
	//xem danh sách friend request +
	@GetMapping("/friends/add/requests")
	public Map<String, String> viewFriendRequestList(@RequestParam("email") String email, HttpServletRequest req){
		return friendServices.viewFriendRequest(email,req);
	}
	
	
	//xem lời mời kết bạn nhận được +
	@GetMapping("/friends/received")
	public Map<String, String> viewReceivedFriendRequestList(@RequestParam("email") String email, HttpServletRequest req){
		return friendServices.viewReceivedFriendRequest(email,req);
	}
	
	
}
