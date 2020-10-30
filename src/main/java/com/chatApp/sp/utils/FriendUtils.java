package com.chatApp.sp.utils;

import java.util.List;

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
	
	public List<String> viewFriendlist(HttpServletRequest req){
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
			
			List<String> friendRequest = user.getFriendRequest();
			
			if(!friendRequest.contains(friendEmail)) {
				friendRequest.add(friendEmail);
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
			List<String> acceptFriendRequest = user.getAcceptFriendRequest();
			
			if(acceptFriendRequest.contains(friendEmail)) {
				List<String> friends = user.getFriend();
				friends.add(friendEmail);
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
		
		List<String> friends = user.getFriend();
		
		if(friends.contains(friendEmail)) {
			friends.remove(friendEmail);
			return "SUCCEED";
		}
		
		return null;
	}
	
	public List<String> viewFriendRequest(HttpServletRequest req){
		String email = cookieUtils.getEmail(req);
		
		DBUser user = userRepo.findByEmail(email);
		
		return user.getFriendRequest();
	}
	
	
	

}
