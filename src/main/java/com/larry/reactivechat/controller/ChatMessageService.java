package com.larry.reactivechat.controller;

import com.larry.reactivechat.domain.message.ChatMessageRepo;
import com.larry.reactivechat.domain.message.Message;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@Service
@Transactional
public class ChatMessageService {

    private final ChatMessageRepo chatMessageRepo;

    public Mono<Message> create(CreateChatDto createChatDto, Long senderId, String senderName) {
        return chatMessageRepo.save(new Message(createChatDto.getStuff(), senderId, senderName, createChatDto.getChannelId()));
    }

    public Flux<Message> getMessages(Long channelId) {
        return chatMessageRepo.findWithTailableCursorByChannelId(channelId);
    }
}
