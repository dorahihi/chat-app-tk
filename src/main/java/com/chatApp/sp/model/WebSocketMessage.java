package com.chatApp.sp.model;

public class WebSocketMessage {
	  public String toWhom;
	  public String fromWho;
	  public String message;
	  
	  public WebSocketMessage() {
		  
	  }
	  
	  public WebSocketMessage(final String toWhom, final String fromWho, final String message){
	    this.toWhom  = toWhom;
	    this.fromWho = fromWho;
	    this.message = message;
	  }
	}