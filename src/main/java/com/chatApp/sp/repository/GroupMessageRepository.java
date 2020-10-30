package com.chatApp.sp.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.chatApp.sp.model.GroupMessage;

@Repository
public interface GroupMessageRepository extends MongoRepository<GroupMessage, String>{

}
