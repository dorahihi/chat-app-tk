package com.chatApp.sp.utils;

import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.chatApp.sp.model.DBGroup;
import com.chatApp.sp.model.DBUser;
import com.chatApp.sp.repository.GroupMessageRepository;
import com.chatApp.sp.repository.GroupRepository;
import com.chatApp.sp.repository.UserRepository;


@Component
public class GroupUtils {
	
	@Autowired
	GroupMessageRepository groupMessRepo;
	
	@Autowired
	GroupRepository groupRepo;
	
	@Autowired
	UserRepository userRepo;
	
	@Autowired
	CookieUtils cookieUtils;
	
	public boolean createGroup(String members[], String groupName, String manager) {
		
		List<String> mems = Arrays.asList(members);
		
		DBGroup group = new DBGroup(mems, manager, groupName);
		
		if(groupRepo.findByGroupId(groupName+manager) == null) {
			
			groupRepo.insert(group);
			return true;
			
		}		
		return false;
	}
	
	public DBGroup getGroupInfo(String groupId) {
		return groupRepo.findByGroupId(groupId);
	}
	
	public String deleteGroup(String groupId, HttpServletRequest req) {
		
		String email = cookieUtils.getEmail(req);
		
		DBGroup group = groupRepo.findByGroupId(groupId);
		
		if(group != null) {
			if(group.getManager().equals(email)) {
				groupRepo.delete(group);
				return "SUCCEED";
			}
			return "Permission denied";
		}
		
		return null;
	}
	
	public String leaveGroup(String groupId, HttpServletRequest req) {
		
		String email = cookieUtils.getEmail(req);
		
		DBGroup group = groupRepo.findByGroupId(groupId);
		
		if(group != null) {
			if(group.getMembers().contains(email)) {
				List<String> members = group.getMembers();
				members.remove(email);
				group.setMembers(members);
				groupRepo.save(group);
				return "SUCCEED";
			}
			return "You are not a members of that group";
		}
		return null;
	}
	
	
	public String deleteMember(String groupId, String members[], HttpServletRequest req) {
		
		String manager = cookieUtils.getEmail(req);
		
		DBGroup group = groupRepo.findByGroupId(groupId);
		
		if(group != null) {
			if(group.getManager().equals(manager)) {
				List<String> dMembers = Arrays.asList(members);
				List<String> mems = group.getMembers();
				mems.removeAll(dMembers);
				group.setMembers(mems);
				groupRepo.save(group);
				return "SUCCEED";
			}
			return "Permission denied";
		}
		return null;
	}
	
	public List<String> getMembers(String groupId){
		
		DBGroup group = groupRepo.findByGroupId(groupId);
		
		if(group != null) {
			return group.getMembers();
		}
		
		return null;
	}
	
	public String addGroupMember(String friendEmail, String groupId, HttpServletRequest req) {
		DBGroup group = groupRepo.findByGroupId(groupId);
		
		DBUser user = userRepo.findByEmail(friendEmail);
		
		String email = cookieUtils.getEmail(req);
		
		List<String> members = group.getMembers();
		
		if(email != null && members.contains(email)) {
			if(user != null && !group.getMembers().contains(friendEmail)) {
				List<String> userGroups = user.getGroup();
				
				userGroups.add(groupId);
				members.add(friendEmail);
				
				user.setGroup(userGroups);
				group.setMembers(members);
				
				userRepo.save(user);
				groupRepo.save(group);
				
				return "SUCCESS";
			}
		}
		return null;
	}
	
}
