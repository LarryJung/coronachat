package com.larry.reactivechat.controller;

import com.larry.reactivechat.domain.channel.JoinService;
import com.larry.reactivechat.domain.message.ChatMessageRepo;
import com.larry.reactivechat.domain.message.Message;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@Service
@Transactional
public class ChatMessageService {

    private final ChatMessageRepo chatMessageRepo;
    private final JoinService joinService;

    public Mono<Message> create(CreateChatDto createChatDto, Long senderId, String senderName) {
        Mono<Message> messageMono = chatMessageRepo.save(new Message(createChatDto.getStuff(), senderId, senderName, createChatDto.getChannelId()));
        triggerUnReadCount(createChatDto.getChannelId());
        return messageMono;
    }

    @Async
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void triggerUnReadCount(Long channelId) {
        joinService.addUnReadCount(channelId);
    }

    public Flux<Message> getMessages(Long userId, Long channelId) {
        joinService.initializeUnReadCount(userId, channelId);
        return chatMessageRepo.findWithTailableCursorByChannelId(channelId);
    }
}
