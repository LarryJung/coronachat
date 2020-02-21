package com.larry.reactivechat.domain.channel;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ChannelJoinRepository extends JpaRepository<ChannelJoin, Long> {

    List<ChannelJoin> findAllByParticipant_Id(Long userId);

    Optional<ChannelJoin> findByParticipant_IdAndChannel_Id(Long userId, Long channelId);
}
