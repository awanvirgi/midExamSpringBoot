package com.midexam.liber.user;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Integer> {
    @Query("""
           SELECT u from User u where 
           (:fullName is null or lower(concat(u.firstName,' ',u.lastName)) like %:fullName%)
           """)
    Page<User> findAll(Pageable pageable,String fullName);
    Optional<User> findByUserName(String userName);
    Boolean existsByUserName(String userName);
}
