package com.midexam.liber.review;

import com.midexam.liber.book.Book;
import com.midexam.liber.book.BookMapper;
import com.midexam.liber.review.dto.*;
import com.midexam.liber.user.User;
import com.midexam.liber.user.UserMapper;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class ReviewMapper {
    private final BookMapper bookMapper;
    private final UserMapper userMapper;

    public ReviewMapper(BookMapper bookMapper, UserMapper userMapper) {
        this.bookMapper = bookMapper;
        this.userMapper = userMapper;
    }

    public ResponseReview toResponse(Review review){
        ResponseReview response = new ResponseReview();
        response.setTitle(review.getTitle());
        response.setDescription(review.getDescription());
        response.setRating(review.getRating());
        response.setAddedDate(review.getAddedDate());
        response.setFinishedReadingDate(review.getFinishedReadingDate());
        response.setReadStatus(review.getReadStatus());
        response.setReviewDate(review.getReviewDate());
        response.setUser(userMapper.toSummaryResponse(review.getUser()));
        response.setBook(bookMapper.toResponse(review.getBook()));
        return response;
    }

    public Review updateReview(Review review, RequestReview request){
        review.setTitle(request.getTitle());
        review.setDescription(request.getDescription());
        review.setReadStatus(request.getReadStatus());
        review.setRating(request.getRating());
        review.setFinishedReadingDate(request.getFinishedReadingDate());
        review.setReviewDate(LocalDateTime.now());
        return review;
    }

    public Review toReview(RequestReviewRating request, Book book, User user){
        Review review = new Review();
        review.setUser(user);
        review.setBook(book);
        review.setFinishedReadingDate(request.getFinishedReadingDate());
        review.setRating(request.getRating());
        review.setReadStatus(request.getReadStatus());
        review.setReviewDate(LocalDateTime.now());
        return review;
    }
    public Review toReview(RequestReviewStatus request, Book book, User user){
        Review review = new Review();
        review.setUser(user);
        review.setBook(book);
        review.setReadStatus(request.getReadStatus());
        return review;
    }
}
