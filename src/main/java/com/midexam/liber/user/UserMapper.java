package com.midexam.liber.user;

import com.midexam.liber.user.dto.ResponseUser;
import com.midexam.liber.user.dto.ResponseUserSummary;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    public ResponseUserSummary toSummaryResponse(User user){
        ResponseUserSummary response = new ResponseUserSummary();
        response.setUsername(user.getUserName());
        response.setEmail(user.getEmail());
        response.setFirstName(user.getFirstName());
        response.setLastName(user.getLastName());
        response.setDeactivated(user.getDeactivated());
        return response;
    }
    public ResponseUser toResponse(User user){
        ResponseUser response = new ResponseUser();
        response.setUsername(user.getUserName());
        response.setEmail(user.getEmail());
        response.setFirstName(user.getFirstName());
        response.setLastName(user.getLastName());
        response.setDeactivated(user.getDeactivated());
        response.setGender(user.getGender());
        response.setBirthDate(user.getBirthDate());
        response.setRole(user.getRole());
        return response;
    }
}
