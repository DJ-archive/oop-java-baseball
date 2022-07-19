package com.djyun.oopjavabaseballAPI.domain.user;

import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.Map;

@Service
public class UserDaoService {
    private static Map<Integer,User> userRepository = new HashMap<>();
    private static int userId = 0;

    public User save(){
        User user = new User(++userId);
        userRepository.put(user.getRoomId(), user);
        return user;
    }
    
    public User findUserById(int roomId){
        return userRepository.get(roomId);
    }

    public void updateUser(int roomId, User user){
        userRepository.put(roomId, user);
    }

}
