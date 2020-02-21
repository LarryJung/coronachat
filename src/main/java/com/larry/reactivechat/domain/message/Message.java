package com.larry.reactivechat.domain.message;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;

@Data
@Document("messages")
public class Message {

    @Id
    private String id;
    private String message;
    private String sender;
    private String sendee;
    private String channelId;

    public Message( String message, String sender, String sendee, String channelId) {
        this.message = message;
        this.sender = sender;
        this.sendee = sendee;
        this.channelId = channelId;
    }

}
