package com.qixl.bookshare.dao;

import java.sql.SQLException;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.ResultSet;
import com.qixl.bookshare.model.User;
import com.qixl.bookshare.util.DBUtils;
import com.qixl.common.JDBCCallBack;
import com.qixl.common.JDBCTemplate;

public class UserDao {

    public User getUserByName(String userName){
        if(userName == null || userName.equals("")) {
            return null;
        }
        JDBCTemplate<User> jdbcTemplate = new JDBCTemplate<User>();
        return jdbcTemplate.queryOne("", new JDBCCallBack<User>() {
            
            @Override
            public void setParams(PreparedStatement pstmt) throws SQLException {
                pstmt.setString(1, userName);                
            }
            
            @Override
            public User rsToObject(ResultSet rs) throws SQLException {
                User user = new User();
                user.setId(rs.getInt("id"));
                user.setUserName(rs.getString("user_name"));
                user.setPassword(rs.getString("password"));
                return user;
            }
        });
        
    }
}
