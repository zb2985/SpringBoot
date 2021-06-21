package kopo.jjh.prj.socket;

import javax.annotation.Resource;

@Resource
public class ChatMessage {

    public static kopo.jjh.prj.redis.ChatMessage.MessageType MessageType;
    private MessageType type;
    private String content;
    private String sender;
private String message;
private String roomId;
    public MessageType getType() {
        return type;
    }

    public void setType(MessageType type) {
        this.type = type;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public void setMessage(String message) {
    }


    public String getRoomId() {return roomId;
    }

}