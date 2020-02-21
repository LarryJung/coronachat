package com.larry.reactivechat.domain.channel;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.larry.reactivechat.domain.user.User;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
public class Channel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    @JsonIgnore
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "maker_id")
    private User maker;

    private int numberOfParticipants;

    @JsonIgnore
    @OneToMany(mappedBy = "channel")
    private List<ChannelJoin> channelJoins;

    private LocalDateTime createdAt = LocalDateTime.now();

    public Channel() {
    }

    public Channel(User maker) {
        this.maker = maker;
        this.name = defaultName();
        this.numberOfParticipants = 1;
    }

    private String defaultName() {
        return maker.getName() + "님의 채널";
    }

    public void join() {
        this.numberOfParticipants++; // it is not thread safe
    }

    public void out() {
        this.numberOfParticipants--;
    }
}
