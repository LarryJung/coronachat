package com.larry.reactivechat.controller;

import com.larry.reactivechat.domain.message.ChatMessageRepo;
import com.larry.reactivechat.domain.message.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

@RestController
public class ChatMessageController {

    @Autowired
    ChatMessageRepo chatMessageRepo;

    @PostMapping("/chats")
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<Message> postChat(@Valid @RequestBody Message chatMessage) {
        return chatMessageRepo.save(chatMessage);
    }

    @GetMapping(value = "/chats/stream", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Message> streamMessages(@RequestParam String channelId){
        return chatMessageRepo.findWithTailableCursorByChannelId(channelId);
    }

}
