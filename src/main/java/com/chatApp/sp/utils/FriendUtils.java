package com.chatApp.sp.utils;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.chatApp.sp.model.DBUser;
import com.chatApp.sp.repository.UserRepository;

@Component
public class FriendUtils {
	
	@Autowired
	UserRepository userRepo;
	
	@Autowired
	CookieUtils cookieUtils;
	
	public Map<String, String> viewFriendlist(HttpServletRequest req){
		String email = cookieUtils.getEmail(req);
		
		DBUser user = userRepo.findByEmail(email);
		
		if(user != null)
			return user.getFriend();
		else return null;
	}
	
	public String sendFriendRequest(String friendEmail, HttpServletRequest req) {
		
		String email = cookieUtils.getEmail(req);
		
		DBUser user = userRepo.findByEmail(email);
		
		if(userRepo.findByEmail(friendEmail) != null) {
			
			Map<String, String> friendRequest = user.getFriendRequest();
			if(!friendRequest.containsKey(friendEmail)) {
				friendRequest.put(friendEmail, userRepo.findByEmail(friendEmail).getUserName());
				user.setFriendRequest(friendRequest);
				userRepo.save(user);
				return "SUCCEED";
			}
			return "Already sent friend request";
		}
		return null;
	}
	
	public String acceptFriendRequest(String friendEmail, HttpServletRequest req) {
		String email = cookieUtils.getEmail(req);
		
		DBUser user = userRepo.findByEmail(email);
		
		if(userRepo.findByEmail(friendEmail) != null) {
			Map<String, String> acceptFriendRequest = user.getAcceptFriendRequest();
			
			if(acceptFriendRequest.containsKey(friendEmail)) {
				Map<String, String> friends = user.getFriend();
				friends.put(friendEmail, userRepo.findByEmail(friendEmail).getUserName());
				acceptFriendRequest.remove(friendEmail);
				user.setAcceptFriendRequest(acceptFriendRequest);
				user.setFriend(friends);
				userRepo.save(user);
				return "SUCCEED";
			}
			return "User does not exist";
		}
		
		return null;
	}
	public String removeFriend(String friendEmail, HttpServletRequest req) {
		String email = cookieUtils.getEmail(req);
		
		DBUser user = userRepo.findByEmail(email);
		
		Map<String, String> friends = user.getFriend();
		
		if(friends.containsKey(friendEmail)) {
			friends.remove(friendEmail);
			return "SUCCEED";
		}
		
		return null;
	}
	
	public Map<String, String> viewFriendRequest(HttpServletRequest req){
		String email = cookieUtils.getEmail(req);
		
		DBUser user = userRepo.findByEmail(email);
		
		return user.getFriendRequest();
	}
	
	
	

}
