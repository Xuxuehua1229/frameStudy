package com.test.spring.beans.generic.di;

import org.springframework.stereotype.Repository;

@Repository
public class UserRepository extends BaseRepository<User>{
    public void save(User user) {
    	System.out.println("UserRepository save..." + user.getName());
    }
}
