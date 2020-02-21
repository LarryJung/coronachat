package com.larry.reactivechat.domain.channel;

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

    @OneToOne
    @JoinColumn(name = "maker_id")
    private User maker;

    @OneToMany(mappedBy = "channel")
    private List<ChannelJoin> channelJoins;

    private LocalDateTime createdAt = LocalDateTime.now();

}
