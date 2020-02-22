package com.larry.reactivechat.domain.channel;

import com.larry.reactivechat.domain.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@RequiredArgsConstructor
@Service
@Transactional
public class JoinService {

    private final ChannelJoinRepository joinRepository;

    public ChannelJoin join(User user, Channel channel) {
        channel.join();
        ChannelJoin channelJoin = new ChannelJoin(channel, user);
        return joinRepository.save(channelJoin);
    }

    public List<ChannelJoin> findAllByUserId(Long userId) {
        return joinRepository.findAllByParticipant_Id(userId);
    }

    public ChannelJoin findJoin(Long userId, Long channelId) {
        return joinRepository.findByParticipant_IdAndChannel_Id(userId, channelId).orElseThrow(() -> new RuntimeException("cannot find join info"));
    }

    public void out(Long userId, Long channelId) {
        joinRepository.delete(findJoin(userId, channelId).out());
    }

    public void addUnReadCount(Long channelId) {
        joinRepository.findAllByChannel_Id(channelId)
                .stream()
                .filter(channelJoin -> !channelJoin.isLooking())
                .forEach(channelJoin -> channelJoin.addUnReadCount());
    }

    public void initializeUnReadCount(Long userId, Long channelId) {
        findJoin(userId, channelId).look();
    }
}
