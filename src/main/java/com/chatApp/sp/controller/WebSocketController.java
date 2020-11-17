package com.chatApp.sp.controller;

import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.chatApp.sp.model.ChatMessage;
import com.chatApp.sp.model.GroupMessage;
import com.chatApp.sp.model.MessageTemplate;
import com.chatApp.sp.model.Notification;
import com.chatApp.sp.utils.DropboxUtils;
import com.chatApp.sp.utils.MessageUtils;
import com.dropbox.core.DbxException;
import com.dropbox.core.v2.files.UploadErrorException;

@Controller
@CrossOrigin
public class WebSocketController {
  private final SimpMessagingTemplate simpMessagingTemplate;
  
  @Autowired
  private MessageUtils mesUtils;
  
  public WebSocketController(SimpMessagingTemplate simpMessagingTemplate){ 
    this.simpMessagingTemplate = simpMessagingTemplate; 
    MessageUtils.activeUser = new HashSet<String>();
  }
  
  
  
  // add an user to connected user list
  @MessageMapping("/register")  
  @SendToUser("/queue/newMember")
  public Set<String> registerUser(String webChatUsername){
    if(!MessageUtils.activeUser.contains(webChatUsername)) {
    	MessageUtils.activeUser.add(webChatUsername);
      simpMessagingTemplate.convertAndSend("/topic/newMember", webChatUsername); 
      return MessageUtils.activeUser;
    } else {
      return new HashSet<>();
    }
  }
  
  
  // remove an user from connected user list
  @MessageMapping("/unregister") 
  @SendTo("/topic/disconnectedUser")
  public String unregisterUser(String webChatUsername){
	  MessageUtils.activeUser.remove(webChatUsername);
    return webChatUsername;
  }

  
  // send message to a specific user 
  @MessageMapping("/message") 
  public void greeting(MessageTemplate message){
	  mesUtils.sendMessage(message);
  }
  
  @PostMapping("/messages/img")
  @ResponseBody
  public void haha(@RequestParam("image") MultipartFile image, 
		  		   @RequestParam("sender") String sender,
		  		   @RequestParam("recipient") String recipient,
		  		   @RequestParam("type") String type,
		  		   @RequestParam("mesType") String mesType) throws UploadErrorException, DbxException, IOException {
	  
	String url = DropboxUtils.uploadFile(image.getInputStream(), image.getOriginalFilename());
	
	MessageTemplate mes = new MessageTemplate(sender, recipient, url, type, mesType);
	
	mesUtils.sendMessage(mes);;
  }
  
  @DeleteMapping("/message/delete")
  @ResponseBody
  public void deleteMessage(@RequestParam("messageId") String messageId, @RequestParam("email") String email, HttpServletRequest req) {
	  mesUtils.deleteMessage(messageId, email, req);
  }
  
  @GetMapping("/groups/messages")
  @ResponseBody
  public List<GroupMessage> viewGroupMessage(@RequestParam("groupId") String groupId, @RequestParam("email") String email, HttpServletRequest req) throws Exception{
	  return mesUtils.viewGroupMessages(groupId, email);
  }
  
  @GetMapping("users/messages")
  @ResponseBody
  public List<ChatMessage> viewPrivateMessage(@RequestParam("chatId") String chatId, @RequestParam("email") String email, HttpServletRequest req) throws Exception{
	  return mesUtils.viewPrivateMessage(chatId, email);
  }
  
  @GetMapping("users/notification")
  @ResponseBody
  public List<Notification> viewNotification(@RequestParam("email") String email, HttpServletRequest req){
	  return mesUtils.viewNotification(email);
  }
  
}
