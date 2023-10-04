package com.csvtodb.utils;

import com.csvtodb.model.User;
import org.springframework.batch.item.database.ItemPreparedStatementSetter;
import org.springframework.stereotype.Component;

import java.sql.PreparedStatement;
import java.sql.SQLException;

@Component
public class UserItemPreparedStatementSetter implements ItemPreparedStatementSetter<User> {
    @Override
    public void setValues(User user, PreparedStatement ps) throws SQLException {
        ps.setString(1, user.getId());
        ps.setString(2, user.getFirstName());
        ps.setString(3, user.getLastName());
        ps.setString(4, user.getGender());
        ps.setString(5, user.getEmail());
        ps.setString(6, user.getPhone());
    }
}
