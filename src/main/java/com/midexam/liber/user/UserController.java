package com.midexam.liber.user;

import com.midexam.liber.review.ReviewService;
import com.midexam.liber.review.dto.*;
import com.midexam.liber.shared.dto.PageResponse;
import com.midexam.liber.user.dto.ResponseUser;
import com.midexam.liber.user.dto.ResponseUserSummary;
import jakarta.validation.Valid;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("users")
public class UserController {
    private final UserService userService;
    private final ReviewService reviewService;

    public UserController(UserService userService, ReviewService reviewService) {
        this.userService = userService;
        this.reviewService = reviewService;
    }

    @GetMapping
    public ResponseEntity<PageResponse<ResponseUserSummary>> findAll(@PageableDefault(sort = "email", size = 10, page = 0) Pageable pageable,
                                                                     @RequestParam(required = false) String fullName) {
        return ResponseEntity.status(HttpStatus.OK).body(
                new PageResponse<>(userService.findAll(pageable, fullName))
        );
    }

    @GetMapping("{username}")
    public ResponseEntity<ResponseUser> findById(@PathVariable String username) {
        return ResponseEntity.status(HttpStatus.OK).body(
                userService.findById(username)
        );
    }

    @PatchMapping("{username}/activate")
    public ResponseEntity<ResponseUser> setActivated(@PathVariable String username) {
        return ResponseEntity.status(HttpStatus.OK).body(
                userService.setActivated(username)
        );
    }

    @PatchMapping("{username}/deactivate")
    public ResponseEntity<ResponseUser> setDeactivated(@PathVariable String username) {
        return ResponseEntity.status(HttpStatus.OK).body(
                userService.setDeactivated(username)
        );
    }

    // review

    @GetMapping("{username}/reviews")
    public ResponseEntity<PageResponse<ResponseReview>> findAllReviewByUsername(@PageableDefault(sort = "book.name") Pageable pageable,
                                                                                       @PathVariable String username) {
        return ResponseEntity.status(HttpStatus.OK).body(
                new PageResponse<>(reviewService.findAll(username,pageable))
        );
    }
    @GetMapping("{username}/reviews/{bookId}")
    public ResponseEntity<ResponseReview> findReviewByUsernameAndBookId(@PathVariable String username, @PathVariable Integer bookId){
        return ResponseEntity.status(HttpStatus.OK).body(
                reviewService.findByBookId(username,bookId)
        );
    }
    @PostMapping("{username}/reviews/{bookId}/track")
    public ResponseEntity<ResponseReview> insertNewReview(@PathVariable String username,
                                                          @PathVariable Integer bookId,
                                                          @Valid @RequestBody RequestReviewStatus request){
        return ResponseEntity.status(HttpStatus.CREATED).body(
                reviewService.insert(username,bookId,request)
        );
    }
    @PostMapping("{username}/reviews/{bookId}/rate")
    public ResponseEntity<ResponseReview> insertNewReviewWithRating(@PathVariable String username,
                                                                    @PathVariable Integer bookId,
                                                                    @Valid @RequestBody RequestReviewRating request){
        return ResponseEntity.status(HttpStatus.CREATED).body(
                reviewService.insertWithRating(username,bookId,request)
        );
    }
    @PutMapping("{username}/reviews/{bookId}")
    public ResponseEntity<ResponseReview> updateReview(@PathVariable String username,
                                                       @PathVariable Integer bookId,
                                                       @Valid @RequestBody RequestReview request){
        return ResponseEntity.status(HttpStatus.OK).body(
                reviewService.updateReview(username,bookId,request)
        );
    }

    @DeleteMapping("{username}/reviews/{bookId}")
    public ResponseEntity<Void> deleteReview(@PathVariable String username,
                                             @PathVariable Integer bookId){
        reviewService.deleteReview(username,bookId);
        return ResponseEntity.noContent().build();
    }
}
