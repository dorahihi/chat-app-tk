package com.chatApp.sp.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.chatApp.sp.model.ChatMessage;

@Repository
public interface MessageRepository extends MongoRepository<ChatMessage, String> {

}
