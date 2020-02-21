package com.larry.reactivechat.domain.channel;

import com.larry.reactivechat.domain.user.AuthService;
import com.larry.reactivechat.domain.user.User;
import com.larry.reactivechat.domain.user.UserService;
import lombok.RequiredArgsConstructor;
import org.hibernate.Hibernate;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
@Transactional
public class ChannelService {

    private final JoinService joinService;
    private final UserService userService;
    private final ChannelRepository channelRepository;

    public Channel create(Long userId) {
        User user = userService.findById(userId);
        Channel channel = channelRepository.save(new Channel(user));
        joinService.join(user, channel);
        return channel;
    }

    public List<Channel> findAll() {
        return channelRepository.findAll();
    }

    public List<Channel> findAllJoinChannel(Long userId) {
        return joinService.findAllByUserId(userId).stream().map(channelJoin -> channelJoin.getChannel()).collect(Collectors.toList());
    }

    public void getOutChannel(Long userId, Long channelId) {
        joinService.out(userId, channelId);
    }

    public ChannelJoin join(User user, Long channelId) {
        Channel channel = findByChannelId(channelId);
        return joinService.join(user, channel);
    }

    private Channel findByChannelId(Long channelId) {
        return channelRepository.findById(channelId).orElseThrow(() -> new RuntimeException("there is no channel id :" + channelId));
    }
}
