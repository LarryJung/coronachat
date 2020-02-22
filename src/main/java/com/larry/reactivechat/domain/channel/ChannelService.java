package com.larry.reactivechat.domain.channel;

import com.larry.reactivechat.domain.user.User;
import com.larry.reactivechat.domain.user.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Service
@Transactional
public class ChannelService {

    private final JoinService joinService;
    private final UserService userService;
    private final ChannelRepository channelRepository;

    public Channel create(Long userId, String name) {
        User user = userService.findById(userId);
        Channel channel;
        try {
            channel = channelRepository.save(new Channel(user, name));
        } catch (DataIntegrityViolationException e) {
            log.error(e.getMessage());
            String message = e.getMessage().contains("CHANNEL(NAME)") ? "동일한 방 이름을 사용할 수 없습니다." : "알 수 없는 오류로 방을 개설할 수 없습니다.";
            throw new RuntimeException(message);
        }
        log.debug("channel saved - 1");
        System.out.println(channel);
        joinService.join(user, channel);
        System.out.println(channel);
        log.debug("channel saved - 2 - before");
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

    public ChannelJoin join(Long userId, Long channelId) {
        User user = userService.findById(userId);
        Channel channel = findByChannelId(channelId);
        return joinService.join(user, channel);
    }

    public Channel findByChannelId(Long channelId) {
        return channelRepository.findById(channelId).orElseThrow(() -> new RuntimeException("there is no channel id :" + channelId));
    }

}
