package com.chatApp.sp.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "groupMessage")
public class GroupMessage {

	
	@Id
	private String id;
	
	@Indexed
	private String groupid;
	
	private String sender;
	
	private String timeStamp;
	
	private String message;
	
	private Type type;
	
	
	public GroupMessage() {
		
	}
	public GroupMessage(String groupId, String sender, String message) {
		this.groupid = groupId;
		this.sender =sender;
		this.message = message;
		this.timeStamp = System.currentTimeMillis() + "";
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getGroupid() {
		return groupid;
	}
	public void setGroupid(String groupid) {
		this.groupid = groupid;
	}
	public String getSender() {
		return sender;
	}
	public void setSender(String sender) {
		this.sender = sender;
	}
	public String getTimeStamp() {
		return timeStamp;
	}
	public void setTimeStamp(String timeStamp) {
		this.timeStamp = timeStamp;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Type getType() {
		return type;
	}
	public void setType(Type type) {
		this.type = type;
	}
	
	
	
}
