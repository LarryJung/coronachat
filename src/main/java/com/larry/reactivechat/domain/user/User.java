package com.larry.reactivechat.domain.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.larry.reactivechat.domain.channel.ChannelJoin;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank
    private String name;

    @Email
    private String email;

    @NotBlank
    private String password;

    @JsonIgnore
    @OneToMany(mappedBy = "participant")
    private List<ChannelJoin> channelJoins = new ArrayList<>();

    public boolean isPassword(String password) {
        return this.password.equals(password);
    }

    public void addChannelJoin(ChannelJoin channelJoin) {
//        if (channelJoin == null) {
//            this.channelJoins = new ArrayList<>();
//        }
        this.channelJoins.add(channelJoin);
    }
}
