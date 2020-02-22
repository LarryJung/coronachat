package com.larry.reactivechat.controller;

import com.larry.reactivechat.domain.channel.*;
import com.larry.reactivechat.util.LoginUser;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;

import java.util.List;
import java.util.Map;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/channels")
public class ChannelController {

    private final ChannelService channelService;

    @PostMapping
    public Channel makeChannel(@LoginUser Principal principal, @RequestBody Map<String, String> createChannelDto) {
        log.debug("create channel");
        return channelService.create(principal.getId(), createChannelDto.get("name")); // 필드가 한개밖에 없으면 dto json 을 못 읽고 map 으로만 읽어야함.
    }

    @GetMapping("/{channelId}")
    public Channel getChannel(@LoginUser Principal principal, @PathVariable Long channelId) {
        return channelService.findByChannelId(channelId);
    }

    @PostMapping("/{channelId}/join")
    public ChannelJoin join(@LoginUser Principal principal, @PathVariable Long channelId) {
        return channelService.join(principal.getId(), channelId);
    }

    @GetMapping
    public List<Channel> getChannelList(@LoginUser Principal principal) {
        return channelService.findAll();
    }

    @GetMapping("/{channelId}/temp-out")
    public List<Channel> tempGoOut(@LoginUser Principal principal, @PathVariable Long channelId) {
        return channelService.tempGoOut(principal.getId(), channelId);
    }

    @GetMapping(value = "/unreads", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<UnReadDto> streamMessages(@LoginUser Principal principal) {
        return channelService.getUnReads(principal.getId());
    }

    @GetMapping("/me")
    public List<Channel> getJoinChannelList(@LoginUser Principal principal) {
        return channelService.findAllJoinChannel(principal.getId());
    }

    @PostMapping("/{channelId}/out")
    public List<Channel> getOutChannel(@LoginUser Principal principal, @PathVariable Long channelId) {
        channelService.getOutChannel(principal.getId(), channelId);
        return channelService.findAllJoinChannel(principal.getId());
    }

}

/**
 *
 * // TODO
 * 테스트 케이스 정리
 * 1.
 * 뷰 만들기
 * js로 간단 테스트
 *
 *
  */
