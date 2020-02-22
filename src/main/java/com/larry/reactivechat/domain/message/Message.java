package com.larry.reactivechat.domain.message;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;
import java.time.LocalDateTime;

@Data
@Document("messages")
@NoArgsConstructor
public class Message {

    @Id
    private String id;
    private String stuff;
    private Long senderId;
    private String senderName;
    private Long channelId;
    private LocalDateTime createdAt = LocalDateTime.now();

    public Message(String stuff, Long senderId, String senderName, Long channelId) {
        this.stuff = stuff;
        this.senderId = senderId;
        this.senderName = senderName;
        this.channelId = channelId;
    }

}
