package com.midexam.liber.review;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;


public interface ReviewRepository extends JpaRepository<Review,Integer> {
    @Query("""
           SELECT r from Review r where r.user.userName = :username and r.deactivated = false
           """)
    Page<Review> findAll(Pageable pageable,String username);

    @Query("""
           SELECT r from Review r where r.user.userName = :username and r.book.id = :bookId and r.deactivated = false
           """)
    Optional<Review> findByUserNameAndBookIdAndDeactivatedFalse(String username, Integer bookId);

    @Modifying
    @Query("""
           update Review set deactivated = true where book.id = :bookId
           """)
    void deactiveAllReviewWithBookId(Integer bookId);

    boolean existsByUserUserNameAndBookId(String username, Integer bookId);
    @Modifying
    void deleteByUserUserNameAndBookId(String username, Integer bookId);
    Boolean existsByUserUserName(String username);

}
