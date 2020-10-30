package com.chatApp.sp.controller;

import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.chatApp.sp.model.DBGroup;
import com.chatApp.sp.repository.UserRepository;
import com.chatApp.sp.utils.CookieUtils;
import com.chatApp.sp.utils.GroupUtils;


@RestController
public class GroupController {
	
	
	@Autowired
	GroupUtils groupUtils;
	
	@Autowired
	UserRepository userRepo;
	
	@Autowired
	CookieUtils cookieU;
	
	@PostMapping("/group/create")
	public boolean createGroup(@RequestParam("members") String members[], @RequestParam("groupName") String groupName, HttpServletRequest req) {
		
		String manager = cookieU.getEmail(req);
		
		boolean a = groupUtils.createGroup(members, groupName, manager);
		
		return a;
	}
	
	@GetMapping("/group")
	public List<String> getAllGroups(HttpServletResponse res, HttpServletRequest req){
		Cookie cookies[] = req.getCookies();
		
		String email = "";
		
		for(Cookie c : cookies) {
			if(c.getName().equals("email")) {
				email = c.getValue();
				break;
			}
		}
		return userRepo.findByEmail(email).getGroup();	
	}
	
	
	@DeleteMapping("/group/delete/members")
	public String deleteMembers(@RequestParam("groupId") String groupId, @RequestParam("members") String[] members, HttpServletRequest req) {
		return groupUtils.deleteMember(groupId, members, req);
	}
	
	@GetMapping("/group/profile/{groupid}")
	public DBGroup getGroup(@PathVariable("groupid") String groupId) {
		return groupUtils.getGroupInfo(groupId);
	}
	
	@GetMapping("group/members/{groupId}")
	public List<String> viewGroupMember(@PathVariable("groupId") String groupId){
		return groupUtils.getMembers(groupId);
	}
	
	@GetMapping("/group/add")
	public String addMember(@RequestParam("email") String friendEmail, @RequestParam("groupId") String groupId, HttpServletRequest req) {
		return groupUtils.addGroupMember(friendEmail, groupId, req);
	}
	
	@PostMapping("/group/leave")
	public String leaveGroup(@RequestParam("groupId") String groupId, HttpServletRequest req) {
		return groupUtils.leaveGroup(groupId, req);
	}
	
	@DeleteMapping("/group/delete")
	public String deleteGroup(@RequestParam("groupId") String groupId, HttpServletRequest req) {
		return groupUtils.deleteGroup(groupId, req);
	}
}
