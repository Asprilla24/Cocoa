package mlg.warkop.com.mypsychologist.model;

import mlg.warkop.com.mypsychologist.database.MessageObject;

public class Message {
    private String id;
    private String messageText;
    private String messageFrom;
    private String messageTo;
    private String messageTime;

    public Message(){
        this.id = "";
        this.messageText = "";
        this.messageFrom = "";
        this.messageTo = "";
        this.messageTime = "";
    }

    public Message(MessageObject messageObject){
        this.id = messageObject.getId();
        this.messageText = messageObject.getMessageText();
        this.messageFrom = messageObject.getMessageFrom();
        this.messageTo = messageObject.getMessageTo();
        this.messageTime = messageObject.getMessageTime();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMessageText() {
        return messageText;
    }

    public void setMessageText(String messageText) {
        this.messageText = messageText;
    }

    public String getMessageFrom() {
        return messageFrom;
    }

    public void setMessageFrom(String messageFrom) {
        this.messageFrom = messageFrom;
    }

    public String getMessageTo() {
        return messageTo;
    }

    public void setMessageTo(String messageTo) {
        this.messageTo = messageTo;
    }

    public String getMessageTime() {
        return messageTime;
    }

    public void setMessageTime(String messageTime) {
        this.messageTime = messageTime;
    }
}
