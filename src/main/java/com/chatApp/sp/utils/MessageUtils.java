package com.chatApp.sp.utils;

import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

import com.chatApp.sp.model.ChatMessage;
import com.chatApp.sp.model.DBGroup;
import com.chatApp.sp.model.GroupMessage;
import com.chatApp.sp.model.Message;
import com.chatApp.sp.model.MessageState;
import com.chatApp.sp.model.MessageTemplate;
import com.chatApp.sp.model.MessageType;
import com.chatApp.sp.model.Notification;
import com.chatApp.sp.model.Type;
import com.chatApp.sp.repository.GroupMessageRepository;
import com.chatApp.sp.repository.GroupRepository;
import com.chatApp.sp.repository.MessageRepository;
import com.chatApp.sp.repository.NotiRepository;

@Component
public class MessageUtils {

	@Autowired
	SimpMessagingTemplate messageTemplate;
	
	@Autowired
	GroupRepository groupRepo;
	
	@Autowired
	GroupMessageRepository groupMesRepo;
	
	@Autowired
	NotiRepository notiRepo;
	
	@Autowired
	MessageRepository mesRepo;
	
	public static Set<String> activeUser;
	
	public void sendMessage(MessageTemplate mes) {
		Type type = getType(mes.getType());
		MessageType mesType = getMessageType(mes.getMessageType());
		
		
		if(type.equals(Type.GroupMessage)) {
			DBGroup group = groupRepo.findByGroupId(mes.getRecipient());
			GroupMessage message = new GroupMessage(mes.getRecipient(), mes.getSender(), mes.getMessage(), group.getMembers());
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
	
	public void deleteMessage(String messageId, String email, HttpServletRequest req) {
		String type = messageId.substring(0, messageId.indexOf("_"));
		if(type.equals("group")) {
			GroupMessage groupMes = groupMesRepo.findByMessageId(messageId);
			deleteGroupMessage(groupMes, email);
		}else {
			ChatMessage mes = mesRepo.findByMessageId(messageId);
			deleteMessage(mes, email);
		}
	}
	
	/*
	 * Need to limit the result
	 */
	public List<GroupMessage> viewGroupMessages(String groupId, String email) throws Exception{
		if(groupRepo.findByGroupId(groupId).getMembers().containsKey(email))
			return groupMesRepo.findByGroupid(groupId);
		throw new Exception("You are not a group member!");
	}
	
	public List<ChatMessage> viewPrivateMessage(String chatId, String email) throws Exception{
		if(chatId.contains(email))
			return mesRepo.findByChatId(chatId);
		throw new Exception("Something wrong!");
	}
	
	public List<Notification> viewNotification(String email){
		return notiRepo.findByRecipient(email);
	}
	
	
	private void sendGroupMessage(GroupMessage mes) {	
		Map<String, Boolean> members = mes.getIsRemove();
		
		groupMesRepo.save(mes);
		/*
		 * check if user is active?
		 */
		for(Map.Entry<String, Boolean> mem: members.entrySet()) {
			messageTemplate.convertAndSendToUser(mem.getKey(), "/msg", mes);
		}
	}
	
	private void sendPrivateMessage(ChatMessage mes) {
		mesRepo.save(mes);
		/*
		 * check if user is active?
		 */
		messageTemplate.convertAndSendToUser(mes.getRecipient(), "/msg", mes);
	}
	
	private void sendNotification(Notification noti) {
		notiRepo.save(noti);
		/*
		 * check if user is active?
		 */
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
		if(MessageType.Image.name().equals(mesType))
			return MessageType.Image;
		return MessageType.Text;
	}
	
	private void deleteGroupMessage(GroupMessage groupMes, String email) {
		Map<String, Boolean> isRemove = groupMes.getIsRemove();
		isRemove.replace(email, true);
		groupMes.setIsRemove(isRemove);
		
		groupMesRepo.save(groupMes);
	}
	
	private void deleteMessage(ChatMessage mes, String email) {
		if(mes.getSender().equals(email)) 
			mes.setSenderState(MessageState.REMOVED);
		else 
			mes.setRecipientState(MessageState.REMOVED);
		
		mesRepo.save(mes);
		
	}
	
}
