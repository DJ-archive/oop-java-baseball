package com.djyun.oopjavabaseballAPI.domain.user;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class UserCheck{
    private static Map<Integer,User> store = new HashMap<>();
    private static int userId = 0;

    public User save(){
        User user = new User();
        user.setRoomId(++userId);
        store.put(user.getRoomId(), user);
        return user;
    }





}
