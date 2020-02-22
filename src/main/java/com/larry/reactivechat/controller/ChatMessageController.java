package com.larry.reactivechat.controller;

import com.larry.reactivechat.domain.channel.ChannelService;
import com.larry.reactivechat.domain.message.ChatMessageRepo;
import com.larry.reactivechat.domain.message.Message;
import com.larry.reactivechat.util.LoginUser;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class ChatMessageController {

    private final ChatMessageService chatMessageService;

    @PostMapping("/chats")
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<Message> postChat(@LoginUser Principal principal, @Valid @RequestBody CreateChatDto createChatDto) {
        return chatMessageService.create(createChatDto, principal.getId(), principal.getName());
    }

    @GetMapping(value = "/chats/stream", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Message> streamMessages(@LoginUser Principal principal, @RequestParam Long channelId) {
        return chatMessageService.getMessages(principal.getId(), channelId);
    }

}
