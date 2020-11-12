package org.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

@Component
public class UserService {
    @Autowired
    JdbcTemplate jdbcTemplate;

    public User getUserById(long id){
        return jdbcTemplate.execute((Connection conn) ->{
           try(PreparedStatement ps = conn.prepareStatement("SELECT * FROM users WHERE id = ?")){
               ps.setObject(1,id);
               try(ResultSet rs = ps.executeQuery()){
                   if(rs.next()){
                       return new User(
                               rs.getLong("id"),
                               rs.getString("email"),
                               rs.getString("password"),
                               rs.getString("name")
                       );
                   }
                   throw new RuntimeException("user not found by id.");
               }
           }
        });
    }

    public User getUserByEmail(String email){
        return jdbcTemplate.queryForObject("SELECT * FROM users WHERE email = ?",new Object[]{email},
                (ResultSet rs, int a) ->{
            return new User(rs.getLong("id"),rs.getString("email"),rs.getString("password"),rs.getString("name"));
//              return new BeanPropertyRowMapper<User>(0);
                }
        );
    }
}
