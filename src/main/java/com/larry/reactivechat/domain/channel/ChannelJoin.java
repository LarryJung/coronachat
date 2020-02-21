package com.larry.reactivechat.domain.channel;

import com.larry.reactivechat.domain.user.User;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class ChannelJoin {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "channel_id")
    private Channel channel;

    @ManyToOne
    @JoinColumn(name = "participant_id")
    private User participant;

    public ChannelJoin(Channel channel, User participant) {
        this.channel = channel;
        this.participant = participant;
        participant.addChannelJoin(this);
    }

    public ChannelJoin out() {
        channel.out();
        return this;
    }
}
