package com.chatApp.sp.model;

import java.util.HashMap;
import java.util.Map;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "groupMessage")
public class GroupMessage extends Message {

	
	@Id
	private String id;
	
	@Indexed
	private String messageId;
	
	private String timeStamp;
	
	private String groupId;
	
	private String sender;
	
	private String message;
	
	private Map<String, Boolean> isRemove;
	
	private MessageType mesType;
	
	private Type type;
	
	
	public GroupMessage() {
		
	}
	public GroupMessage(String groupId, String sender, String message, Map<String, String> members, MessageType mesType) {
		this.groupId = groupId;
		this.mesType = mesType;
		this.timeStamp = System.currentTimeMillis() + "";
		this.messageId = "group_"+this.groupId+"_"+timeStamp;
		this.sender =sender;
		this.message = message;
		this.type = Type.GroupMessage;
		
		this.isRemove = new HashMap<String, Boolean>();
		for(Map.Entry<String, String> m: members.entrySet()) {
			this.isRemove.put(m.getKey(), false);
		}
	}
	
	public MessageType getMesType() {
		return mesType;
	}
	public void setMesType(MessageType mesType) {
		this.mesType = mesType;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getGroupId() {
		return groupId;
	}
	public void setGroupId(String groupId) {
		this.groupId = groupId;
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
	public Map<String, Boolean> getIsRemove() {
		return isRemove;
	}
	public void setIsRemove(Map<String, Boolean> isRemove) {
		this.isRemove = isRemove;
	}
	public String getMessageId() {
		return messageId;
	}
	public void setMessageId(String messageId) {
		this.messageId = messageId;
	}
	
	
	
	
}
