package com.larry.reactivechat.domain.channel;

import com.larry.reactivechat.domain.user.User;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
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

    private int unReadMessageCount;

    private boolean isLooking = true;

    public ChannelJoin(Channel channel, User participant) {
        this.channel = channel;
        this.participant = participant;
        participant.addChannelJoin(this);
    }

    public void look() {
        this.isLooking = true;
        this.unReadMessageCount = 0;
    }

    public void notLook() {
        this.isLooking = false;
    }

    public ChannelJoin out() {
        channel.out();
        return this;
    }

    public void addUnReadCount() {
        this.unReadMessageCount++;
    }
}
