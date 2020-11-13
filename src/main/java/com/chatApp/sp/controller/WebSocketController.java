package com.chatApp.sp.controller;

import java.util.Set;
import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.chatApp.sp.model.ChatMessage;
import com.chatApp.sp.model.MessageTemplate;
import com.chatApp.sp.utils.MessageUtils;

@Controller
@CrossOrigin
public class WebSocketController {
  private final SimpMessagingTemplate simpMessagingTemplate;  
  private final Set<String> connectedUsers;    
  
  @Autowired
  private MessageUtils mesUtils;
  
  public WebSocketController(SimpMessagingTemplate simpMessagingTemplate){ 
    this.simpMessagingTemplate = simpMessagingTemplate; 
    connectedUsers = new HashSet<>();  
  }
  
  
  
  // add an user to connected user list
  @MessageMapping("/register")  
  @SendToUser("/queue/newMember")
  public Set<String> registerUser(String webChatUsername){
    if(!connectedUsers.contains(webChatUsername)) {
      connectedUsers.add(webChatUsername);
      simpMessagingTemplate.convertAndSend("/topic/newMember", webChatUsername); 
      return connectedUsers;
    } else {
      return new HashSet<>();
    }
  }
  
  
  // remove an user from connected user list
  @MessageMapping("/unregister") 
  @SendTo("/topic/disconnectedUser")
  public String unregisterUser(String webChatUsername){
    connectedUsers.remove(webChatUsername);
    return webChatUsername;
  }

  
  // send message to a specific user 
  @MessageMapping("/message") 
  public void greeting(MessageTemplate message){
	  mesUtils.messageProcess(message);
  }
}
