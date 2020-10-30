package com.chatApp.sp.controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Controller;

import com.chatApp.sp.model.ChatMessage;

@Controller
public class WebSocketController {
	
	@MessageMapping("/chat.sendMessage")
	public ChatMessage sendMessage(@Payload ChatMessage chatMessage) {
		return chatMessage;
	}
	
	
}
