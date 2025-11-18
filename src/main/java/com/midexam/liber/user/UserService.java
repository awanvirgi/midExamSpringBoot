package com.midexam.liber.user;

import com.midexam.liber.error.exception.BadRequestException;
import com.midexam.liber.error.exception.ConflictException;
import com.midexam.liber.error.exception.ResourceNotFoundException;
import com.midexam.liber.user.dto.ResponseUser;
import com.midexam.liber.user.dto.ResponseUserSummary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserService(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    public Page<ResponseUserSummary> findAll(Pageable pageable, String fullName){
        return userRepository.findAll(pageable,fullName.toLowerCase()).map(
                userMapper::toSummaryResponse
        );
    }

    public ResponseUser findById(String userName){
        Optional<User> optionalUser = userRepository.findByUserName(userName);
        if (optionalUser.isEmpty()) {
            throw new ResourceNotFoundException("Username tidak ditemukan");
        }
        return userMapper.toResponse(optionalUser.get());
    }

    public ResponseUser setDeactivated(String userName){
        Optional<User> optionalUser = userRepository.findByUserName(userName);
        if (optionalUser.isEmpty()) {
            throw new ResourceNotFoundException("Username tidak ditemukan");
        }
        if (optionalUser.get().getDeactivated() == true){
            throw new ConflictException("User sudah dalam kondisi tidak aktif,Patching digagalkan");
        }
        optionalUser.get().setDeactivated(true);
        return userMapper.toResponse(userRepository.save(optionalUser.get()));
    }

    public ResponseUser setActivated(String userName){
        Optional<User> optionalUser = userRepository.findByUserName(userName);
        if (optionalUser.isEmpty()) {
            throw new ResourceNotFoundException("Username tidak ditemukan");
        }
        if (optionalUser.get().getDeactivated() == false){
            throw new ConflictException("User sudah dalam kondisi aktif,Patching digagalkan");
        }
        optionalUser.get().setDeactivated(false);
        return userMapper.toResponse(userRepository.save(optionalUser.get()));
    }
}
