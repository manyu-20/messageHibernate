package com.as.dao;

import java.util.List;
import com.as.model.MessagesOld;

public interface MessagesReadDao {
	
	List<MessagesOld> inbox(String username, int limit);
	List<MessagesOld> outbox(String username, int limit);
	
}
