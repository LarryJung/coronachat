package com.larry.reactivechat.domain.channel;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.larry.reactivechat.domain.user.User;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
public class Channel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
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

    public Channel(User maker, String name) {
        this.maker = maker;
        this.name = name == null ? defaultName() : name;
        this.numberOfParticipants = 0;
    }

    private String defaultName() {
        return maker.getName() + "님의 채널 - " + id; // id가 이미 들어와 있다고 가정
    }

    public void join() {
        this.numberOfParticipants++; // it is not thread safe
    }

    public void out() {
        this.numberOfParticipants--;
    }

    @Override
    public String toString() {
        return "Channel{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", numberOfParticipants=" + numberOfParticipants +
                ", createdAt=" + createdAt +
                '}';
    }
}
