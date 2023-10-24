package com.as.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;

import com.as.jdbc.DbConnection;

public class MessagesDeleteDaoImpl implements MessagesDeleteDao {

	@Override
	public boolean deleteMessage(int pk) {
		Connection conn = DbConnection.getConnection();
		String sql = "delete from messages where pk = ?";
		
		try {
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setInt(1, pk);
			
			int rowsUpdated = pst.executeUpdate();
			if (rowsUpdated == 1)
				return true;
			else
				throw new Exception();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return false;
	}

}
