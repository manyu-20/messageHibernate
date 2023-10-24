package com.as.dao2;

import com.as.hiber.Messages;

import java.util.List;

public interface MessagesDao {
    public void deleteMessage(int pk);

    public List<Messages> OutboxMessages(String username,int pageNo);

    public List<Messages> InboxMessages(String username,int pageNo);

    public void writeMessages(Messages message);

}
