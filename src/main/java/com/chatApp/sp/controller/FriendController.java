package com.chatApp.sp.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.chatApp.sp.repository.UserRepository;
import com.chatApp.sp.service.CookieServices;
import com.chatApp.sp.service.FriendServices;
import com.chatApp.sp.service.MessageServices;


@RestController
@CrossOrigin //for testing
public class FriendController {

	@Autowired
	UserRepository userRepos;
	
	@Autowired
	CookieServices cookieServices;
	
	@Autowired
	FriendServices friendServices;
	
	@Autowired
	MessageServices messageServices;

	/*
	 * {email} là biến thay đổi theo từng người
	 * vd: /friend/accept/khang -> email: khang, có nghĩa là tài khoản người gửi request đã chấp nhận kết bạn với tài khoản có email là khang
	 */
	//chấp nhận kết bạn +
	@PostMapping("/friends/accept")
	public String acceptFriendRequest(@RequestParam("friendEmail") String friendEmail, HttpServletRequest req) throws Exception {
		
		return friendServices.acceptFriendRequest(friendEmail, req);
	}
	@PostMapping("/app/friends/accept")
	public String appAcceptFriendRequest(@RequestParam("friendEmail") String friendEmail, @RequestHeader("email") String email) throws Exception {
		
		return friendServices.acceptFriendRequest(friendEmail, email);
	}
	
	
	//gửi lời mời kết bạn +
	@PostMapping("/friends/add")
	public String sendFriendRequest(@RequestParam("friendEmail") String friendEmail, @RequestParam("email") String email,HttpServletRequest req) throws Exception {
		return friendServices.sendFriendRequest(friendEmail, req);
	}
	@PostMapping("/app/friends/add")
	public String appSendFriendRequest(@RequestParam("friendEmail") String friendEmail, @RequestHeader("email") String email) throws Exception {
		return friendServices.sendFriendRequest(friendEmail, email);
	}
	
	
	//xem danh sách bạn +
	@GetMapping("/friends")
	public Map<String, String> viewFriendList(@RequestParam("email") String email,HttpServletRequest req) {
		return friendServices.viewFriendlist(req);
	}
	@GetMapping("/app/friends")
	public Map<String, String> appViewFriendList(@RequestHeader("email") String email) {
		return friendServices.viewFriendlist(email);
	}
	
	//xoá bạn (như cái chấp nhận lời mời kết bạn) +
	@DeleteMapping("/friends/remove")
	public String removeFriend(@RequestParam("friendEmail") String friendEmail, @RequestParam("email") String email, HttpServletRequest req) throws Exception {
		return friendServices.removeFriend(friendEmail, req);
	}
	@DeleteMapping("/app/friends/remove")
	public String appRemoveFriend(@RequestParam("friendEmail") String friendEmail, @RequestHeader("email") String email) throws Exception {
		return friendServices.removeFriend(friendEmail, email);
	}
	
	//xem danh sách friend request +
	@GetMapping("/friends/add/requests")
	public Map<String, String> viewFriendRequestList(@RequestParam("email") String email, HttpServletRequest req){
		return friendServices.viewFriendRequest(req);
	}
	@GetMapping("/app/friends/add/requests")
	public Map<String, String> appViewFriendRequestList(@RequestHeader("email") String email){
		return friendServices.viewFriendRequest(email);
	}
	
	
	//xem lời mời kết bạn nhận được +
	@GetMapping("/friends/received")
	public Map<String, String> viewReceivedFriendRequestList(@RequestParam("email") String email, HttpServletRequest req){
		return friendServices.viewReceivedFriendRequest(req);
	}
	@GetMapping("/app/friends/received")
	public Map<String, String> appViewReceivedFriendRequestList(@RequestHeader("email") String email){
		return friendServices.viewReceivedFriendRequest(email);
	}
	
	
	
}
