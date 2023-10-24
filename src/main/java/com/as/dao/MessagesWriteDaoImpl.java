package com.as.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;

import com.as.jdbc.DbConnection;
import com.as.model.MessagesOld;

public class MessagesWriteDaoImpl implements MessagesWriteDao {
	@Override
	public MessagesOld insert(MessagesOld message) {
		Connection conn = DbConnection.getConnection();
		String sql = "insert into messages(fromuser, touser, message, log) values(?, ?, ?, ?)";
		
		try {
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setString(1, message.getFromUser());
			pst.setString(2, message.getToUser());
			pst.setString(3, message.getMessage());
			pst.setTimestamp(4, message.getLog());
			
			int rowsUpdated = pst.executeUpdate();
			if (rowsUpdated == 1)
				return message;
			else
				throw new Exception();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
}
