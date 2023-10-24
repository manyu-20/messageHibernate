package com.as.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.as.model.UserOld;
import com.as.jdbc.DbConnection;

public class UserDaoImpl implements UserDao {

	@Override
	public UserOld findByUsername(String username) {
		String sql = "select * from servlet where username=?";
		try {
			PreparedStatement pst = DbConnection.getConnection().prepareStatement(sql);
			pst.setString(1, username);
			ResultSet resultSet = pst.executeQuery();
			if(resultSet.next()) {
				String pass = resultSet.getString("password");
				UserOld user = new UserOld(username, pass);
				return user;
			}
			else return null;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

}
