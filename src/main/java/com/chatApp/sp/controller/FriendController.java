package com.chatApp.sp.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.chatApp.sp.repository.UserRepository;
import com.chatApp.sp.utils.CookieUtils;
import com.chatApp.sp.utils.FriendUtils;

@RestController
@CrossOrigin //for testing
public class FriendController {

	@Autowired
	UserRepository userRepos;
	
	@Autowired
	CookieUtils cookieUtils;
	
	@Autowired
	FriendUtils friendUtils;

	/*
	 * {email} là biến thay đổi theo từng người
	 * vd: /friend/accept/khang -> email: khang, có nghĩa là tài khoản người gửi request đã chấp nhận kết bạn với tài khoản có email là khang
	 */
	//chấp nhận kết bạn +
	@GetMapping("/friends/accept/{email}")
	public String accepyFriendRequest(@PathVariable("email") String email, HttpServletRequest req) throws Exception {
		
		return friendUtils.acceptFriendRequest(email, req);
	}
	
	
	//gửi lời mời kết bạn +
	@GetMapping("/friends/add/{email}")
	public String sendFriendRequest(@PathVariable("email") String friendEmail, HttpServletRequest req) throws Exception {
		return friendUtils.sendFriendRequest(friendEmail, req);
	}
	
	
	//xem danh sách bạn +
	@GetMapping("/friends")
	public Map<String, String> viewFriendList(HttpServletRequest req) {
		return friendUtils.viewFriendlist(req);
	}
	
	//xoá bạn (như cái chấp nhận lời mời kết bạn) +
	@DeleteMapping("/friends/remove/{email}")
	public String removeFriend(@PathVariable("email") String email, HttpServletRequest req) throws Exception {
		return friendUtils.removeFriend(email, req);
	}
	
	//xem danh sách friend request +
	@GetMapping("/friends/add/requests")
	public Map<String, String> viewFriendRequestList(HttpServletRequest req){
		return friendUtils.viewFriendRequest(req);
	}
	
	
	//xem lời mời kết bạn nhận được +
	@GetMapping("/friends/received")
	public Map<String, String> viewReceivedFriendRequestList(HttpServletRequest req){
		return friendUtils.viewReceivedFriendRequest(req);
	}
	
	
}
