package com.chatApp.sp.utils;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

import com.chatApp.sp.model.ChatMessage;
import com.chatApp.sp.model.DBGroup;
import com.chatApp.sp.model.GroupMessage;
import com.chatApp.sp.model.MessageTemplate;
import com.chatApp.sp.model.MessageType;
import com.chatApp.sp.model.Notification;
import com.chatApp.sp.model.Type;
import com.chatApp.sp.repository.GroupRepository;

@Component
public class MessageUtils {

	@Autowired
	SimpMessagingTemplate messageTemplate;
	
	@Autowired
	GroupRepository groupRepo;
	
	public void messageProcess(MessageTemplate mes) {
		Type type = getType(mes.getType());
		MessageType mesType = getMessageType(mes.getMessageType());
		
		
		if(type.equals(Type.GroupMessage)) {
			GroupMessage message = new GroupMessage(mes.getRecipient(), mes.getSender(), mes.getMessage());
			sendGroupMessage(message);
		}
		if(type.equals(Type.Notification)) {
			Notification noti = new Notification(mes.getSender(), mes.getRecipient(), mes.getMessage(), mesType);
			sendNotification(noti);
		}
		if(type.equals(Type.PrivateMessage)) {
			ChatMessage message = new ChatMessage(mes.getSender(), mes.getRecipient(), mes.getMessage());
			sendPrivateMessage(message);
		}
	}
	
	private void sendGroupMessage(GroupMessage mes) {
		DBGroup group = groupRepo.findByGroupId(mes.getGroupid());
		Map<String, String> members = group.getMembers();
		
		for(Map.Entry<String, String> mem: members.entrySet()) {
			messageTemplate.convertAndSendToUser(mem.getKey(), "/msg", mes);
		}
	}
	
	private void sendPrivateMessage(ChatMessage mes) {
		messageTemplate.convertAndSendToUser(mes.getRecipient(), "/msg", mes);
	}
	
	private void sendNotification(Notification noti) {
		messageTemplate.convertAndSendToUser(noti.getRecipient(), "/msg", noti);
	}
	
	private Type getType(String type) {
		if(Type.GroupMessage.name().equals(type))
			return Type.GroupMessage;
		if(Type.Notification.name().equals(type))
			return Type.Notification;
		return Type.PrivateMessage;
	}
	
	private MessageType getMessageType(String mesType) {
		if(MessageType.AcceptFriendRequest.name().equals(mesType))
			return MessageType.AcceptFriendRequest;
		if(MessageType.FriendRequest.name().equals(mesType))
			return MessageType.FriendRequest;
		if(MessageType.GroupMessage.name().equals(mesType))
			return MessageType.GroupMessage;
		return MessageType.Message;
	}
	
}
