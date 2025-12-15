package com.project.websocket.repositories;

import com.project.websocket.chatroom.ChatRoom;
import com.project.websocket.user.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface ChatRoomRepository extends MongoRepository<ChatRoom, String> {
    Optional<ChatRoom> findBySenderIdAndRecipientId(String senderId, String recipientId);
}
