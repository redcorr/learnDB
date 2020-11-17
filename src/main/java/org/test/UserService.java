package org.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

@Component
public class UserService {
    @Autowired
    JdbcTemplate jdbcTemplate;

    public User getUserById(long id){
        return jdbcTemplate.queryForObject("SELECT * FROM users WHERE id = ?",new Object[]{id},(ResultSet rs, int RowNum) ->{
            return new User(rs.getLong("id"),
                            rs.getString("name"),
                            rs.getString("email"),
                            rs.getString("password"));
        });
    }




}
