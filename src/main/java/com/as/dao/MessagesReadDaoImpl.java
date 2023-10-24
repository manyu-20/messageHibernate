package com.as.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.as.jdbc.DbConnection;
import com.as.model.MessagesOld;

public class MessagesReadDaoImpl implements MessagesReadDao {

	@Override
	public List<MessagesOld> inbox(String username, int limit) {
		List<MessagesOld> list = new ArrayList<>();
		Connection conn = DbConnection.getConnection();
		
		try {
			Statement statement = conn.createStatement();
			ResultSet resultSet = statement.executeQuery("select * from messages where touser = '" + username + "' limit " + limit + ", 5");
			if (resultSet.next()) {
				do {
					MessagesOld outbox = new MessagesOld();
					outbox.setPk(resultSet.getInt("pk"));
					outbox.setFromUser(resultSet.getString("fromuser"));
					outbox.setToUser(username);
					outbox.setMessage(resultSet.getString("message"));
					outbox.setLog(resultSet.getTimestamp("log"));
					list.add(outbox);
				} while (resultSet.next());
				return list;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public List<MessagesOld> outbox(String username, int limit) {
		List<MessagesOld> list = new ArrayList<>();
		Connection conn = DbConnection.getConnection();
		
		try {
			Statement statement = conn.createStatement();
			ResultSet resultSet = statement.executeQuery("select * from messages where fromuser = '" + username + "' limit " + limit + ", 5");
			if (resultSet.next()) {
				do {
					MessagesOld outbox = new MessagesOld();
					outbox.setPk(resultSet.getInt("pk"));
					outbox.setFromUser(username);
					outbox.setToUser(resultSet.getString("touser"));
					outbox.setMessage(resultSet.getString("message"));
					outbox.setLog(resultSet.getTimestamp("log"));
					list.add(outbox);
				} while (resultSet.next());
				return list;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}
}
