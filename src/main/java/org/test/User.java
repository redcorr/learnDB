package org.test;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

@Component
public class User {
    private long id;
    private String email;
    private String password;
    private String name;

    public User(long id, String email, String name, String password){
        this.id = id;
        this.email = email;
        this.password = password;
        this.name = name;
    }

    public String getEmail(){return email;}
    public String getPassword(){return password;}
    public String getName(){return name;}

}
