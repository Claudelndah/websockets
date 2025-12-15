package com.project.websocket.chat;

import com.project.websocket.chatroom.ChatRoomService;
import com.project.websocket.repositories.ChatMessageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ChatMessageService {

    private final ChatMessageRepository repository;
    private final ChatRoomService chatRoomService;

    public ChatMessage save(ChatMessage chatMessages){
        var chatId = chatRoomService.getChatRoomId(chatMessages.getSenderId(), chatMessages.getRecipientId(), true)
                .orElseThrow();// You can create your own exception
        chatMessages.setChatId(chatId);
        repository.save(chatMessages);
        return chatMessages;
    }

    public List<ChatMessage> findChatMessages(String senderId, String recipientId){
        var chatId = chatRoomService.getChatRoomId(senderId, recipientId, false);
        return chatId.map(repository::findByChatId).orElse(new ArrayList<>());
    }
}
