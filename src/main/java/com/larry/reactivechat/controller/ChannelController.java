package com.larry.reactivechat.controller;

import com.larry.reactivechat.domain.channel.*;
import com.larry.reactivechat.domain.user.User;
import com.larry.reactivechat.util.LoginUser;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/channels")
public class ChannelController {

    private final ChannelService channelService;

    @PostMapping
    public Channel makeChannel(@LoginUser User user) {
        return channelService.create(user.getId());
    }

    @GetMapping("/{channelId}")
    public ChannelJoin join(@LoginUser User user, @PathVariable Long channelId) {
        return channelService.join(user, channelId);
    }

    @GetMapping
    public List<Channel> getChannelList(@LoginUser User user) {
        return channelService.findAll();
    }

    @GetMapping("/me")
    public List<Channel> getJoinChannelList(@LoginUser User user) {
        return channelService.findAllJoinChannel(user.getId());
    }

    @PostMapping("/{channelId}/out")
    public List<Channel> getOutChannel(@LoginUser User user, @PathVariable Long channelId) {
        channelService.getOutChannel(user.getId(), channelId);
        return channelService.findAllJoinChannel(user.getId());
    }


}