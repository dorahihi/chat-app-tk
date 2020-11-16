package com.chatApp.sp.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
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
@CrossOrigin
public class GroupController {
	
	
	@Autowired
	GroupUtils groupUtils;
	
	@Autowired
	UserRepository userRepo;
	
	@Autowired
	CookieUtils cookieU;
	
	
	//tạo nhóm mới +
	@PostMapping("/group/create")
	public String createGroup(@RequestParam("groupName") String groupName,@RequestParam("email") String email, HttpServletRequest req) {
		
		String manager = email;//cookieU.getEmail(req);
		
		String a = groupUtils.createGroup(groupName, manager);
		
		return a;
	}
	
	//lấy danh sách nhóm của một user +
	@GetMapping("/groups")
	public Map<String, String> getAllGroups(HttpServletRequest req){		
		String email = cookieU.getEmail(req);
		System.out.println("+++++++email: "+ email+" +++++++++");
		return userRepo.findByEmail(email).getGroup();	
	}
	
	
	//xoá members khỏi nhóm (nếu là manager thì hiện cái này lên, còn không phải thì ẩn đi) +
	@DeleteMapping("/group/delete/members")
	public String deleteMembers(@RequestParam("groupId") String groupId, @RequestParam("member") String member,@RequestParam("email") String email, HttpServletRequest req) throws Exception {
		return groupUtils.deleteMember(groupId,email, member, req);
	}
	
	
	//xem thông tin nhóm +
	@GetMapping("/group/profile/{groupid}") 
	public DBGroup getGroup(@PathVariable("groupid") String groupId) {
		return groupUtils.getGroupInfo(groupId);
	}
	
	
	//xem danh sách members +
	@GetMapping("group/members/{groupId}")
	public Map<String, String> viewGroupMember(@PathVariable("groupId") String groupId){
		return groupUtils.getMembers(groupId);
	}
	
	
	//thêm members (1 lần thêm 1 người) +
	@GetMapping("/group/add")
	public String addMember(@RequestParam("friendEmail") String friendEmail, @RequestParam("groupId") String groupId,@RequestParam("email") String email,  HttpServletRequest req) throws Exception {
		return groupUtils.addGroupMember(friendEmail,email, groupId, req);
	}
	
	
	//rời nhóm
	@PostMapping("/group/leave")
	public String leaveGroup(@RequestParam("groupId") String groupId, @RequestParam("email") String email, HttpServletRequest req) throws Exception {
		return groupUtils.leaveGroup(groupId,email, req);
	}
	
	
	//xoá nhóm(nếu là manager thì hiện cái này lên, còn không phải thì ẩn đi)
	@DeleteMapping("/group/delete")
	public String deleteGroup(@RequestParam("groupId") String groupId,@RequestParam("email") String email, HttpServletRequest req) throws Exception {
		return groupUtils.deleteGroup(groupId,email, req);
	}
}
