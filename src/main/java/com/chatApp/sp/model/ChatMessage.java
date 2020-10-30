package com.chatApp.sp.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "message")
public class ChatMessage {
	
	@Id
	private String id;
	
	@Indexed
	private String sender;
	private String recipient;
	private String timeStamp;
	private String message;
	private MessageState senderState;
	private MessageState recipientState;
	
	

	public MessageState getSenderState() {
		return senderState;
	}

	public void setSenderState(MessageState senderState) {
		this.senderState = senderState;
	}

	public MessageState getRecipientState() {
		return recipientState;
	}

	public void setRecipientState(MessageState recipientState) {
		this.recipientState = recipientState;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getSender() {
		return sender;
	}

	public void setSender(String sender) {
		this.sender = sender;
	}

	public String getRecipient() {
		return recipient;
	}

	public void setRecipient(String recipient) {
		this.recipient = recipient;
	}

	public String getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(String timestamp) {
		this.timeStamp = timestamp;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	public ChatMessage() {
		
	}
	
	public ChatMessage(String sender, String recipient, String message, String timestamp) {
		
		this.message = message;
		this.recipient = recipient;
		this.sender = sender;
		this.timeStamp = timestamp;
	}

	
	
}
