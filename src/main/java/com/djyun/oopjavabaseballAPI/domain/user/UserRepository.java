package com.djyun.oopjavabaseballAPI.domain.user;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class UserRepository {
    private static Map<Integer,User> userStore = new HashMap<>();
    private static int userId = 0;

    public User save(){
        User user = new User();
        user.setRoomId(++userId);
        userStore.put(user.getRoomId(), user);
        return user;
    }
    
    public User findUserById(int roomId){
        return userStore.get(roomId);
    }

}
