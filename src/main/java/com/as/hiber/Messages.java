package com.as.hiber;


import javax.persistence.Entity;
import javax.persistence.Id;


import java.sql.Timestamp;
@Entity
public class Messages {
    @Id
    private int pk;
    private String fromUser;
    private String toUser;
    private String message;
    private Timestamp log;

    public String getFromUser() {
        return fromUser;
    }
    public void setFromUser(String fromUser) {
        this.fromUser = fromUser;
    }
    public String getToUser() {
        return toUser;
    }
    public void setToUser(String toUser) {
        this.toUser = toUser;
    }
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
    public Timestamp getLog() {
        return log;
    }
    public void setLog(Timestamp log) {
        this.log = log;
    }
    public int getPk() {
        return pk;
    }
    public void setPk(int pk) {
        this.pk = pk;
    }

    public Messages(String fromUser, String toUser, String message, Timestamp log) {
        super();
        this.fromUser = fromUser;
        this.toUser = toUser;
        this.message = message;
        this.log = log;
    }

    public Messages() {
        super();
        // TODO Auto-generated constructor stub
    }

    @Override
    public String toString() {
        return "Messages [pk=" + pk + ", fromUser=" + fromUser + ", toUser=" + toUser + ", message=" + message
                + ", log=" + log + "]";
    }

    public String getLogAsString() {
        return log.toString();
    }
}
