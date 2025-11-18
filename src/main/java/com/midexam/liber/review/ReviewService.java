package com.midexam.liber.review;

import com.midexam.liber.author.AuthorRepository;
import com.midexam.liber.book.Book;
import com.midexam.liber.book.BookMapper;
import com.midexam.liber.book.BookRepository;
import com.midexam.liber.error.exception.ConflictException;
import com.midexam.liber.error.exception.ResourceNotFoundException;
import com.midexam.liber.review.dto.*;
import com.midexam.liber.user.User;
import com.midexam.liber.user.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ReviewService {
    private final ReviewRepository reviewRepository;
    private final BookRepository bookRepository;
    private final UserRepository userRepository;
    private final AuthorRepository authorRepository;
    private final ReviewMapper reviewMapper;
    private final BookMapper bookMapper;

    public ReviewService(ReviewRepository reviewRepository, BookRepository bookRepository, UserRepository userRepository, AuthorRepository authorRepository, ReviewMapper reviewMapper, BookMapper bookMapper) {
        this.reviewRepository = reviewRepository;
        this.bookRepository = bookRepository;
        this.userRepository = userRepository;
        this.authorRepository = authorRepository;
        this.reviewMapper = reviewMapper;
        this.bookMapper = bookMapper;
    }

    public Page<ResponseReview> findAll(String username, Pageable pageable){
        if(!reviewRepository.existsByUserUserName(username)){
            throw new ResourceNotFoundException("Username tidak ditemukan");
        }
        return reviewRepository.findAll(pageable,username).map(
                reviewMapper::toResponse
        );
    }

    public ResponseReview findByBookId(String username, Integer bookId){
        Optional<User> optionalUser = userRepository.findByUserName(username);
        if (optionalUser.isEmpty()) {
            throw new ResourceNotFoundException("Username tidak ditemukan");
        }
        Optional<Book> optionalBook = bookRepository.findByIdAndDeletedFalse(bookId);
        if (optionalBook.isEmpty()){
            throw new ResourceNotFoundException("Buku dengan id %s tidak ditemukan".formatted(bookId));
        }
        Optional<Review> optionalReview = reviewRepository.findByUserNameAndBookIdAndDeactivatedFalse(username,bookId);
        if (optionalReview.isEmpty()){
            throw new ResourceNotFoundException("Review dengan Kombinasi username %s dan id buku %s tidak ditemukan".formatted(username,bookId));
        }
        Review review = optionalReview.get();
        return reviewMapper.toResponse(review);
    }

    public ResponseReview insert(String username, Integer bookId, RequestReviewStatus request){
        Optional<User> optionalUser = userRepository.findByUserName(username);
        if (optionalUser.isEmpty()) {
            throw new ResourceNotFoundException("Username tidak ditemukan");
        }
        Optional<Book> optionalBook = bookRepository.findByIdAndDeletedFalse(bookId);
        if (optionalBook.isEmpty()){
            throw new ResourceNotFoundException("Buku dengan id %s tidak ditemukan".formatted(bookId));
        }
        if (reviewRepository.existsByUserUserNameAndBookId(username,bookId)){
            throw new ConflictException("Review dengan Kombinasi username %s dan id buku %s sudah ada".formatted(username,bookId));
        }
        Review review = reviewMapper.toReview(request,optionalBook.get(),optionalUser.get());
        return reviewMapper.toResponse(reviewRepository.save(review));
    }

    public ResponseReview insertWithRating(String username, Integer bookId, RequestReviewRating request){
        Optional<User> optionalUser = userRepository.findByUserName(username);
        if (optionalUser.isEmpty()) {
            throw new ResourceNotFoundException("Username tidak ditemukan");
        }
        Optional<Book> optionalBook = bookRepository.findByIdAndDeletedFalse(bookId);
        if (optionalBook.isEmpty()){
            throw new ResourceNotFoundException("Buku dengan id %s tidak ditemukan".formatted(bookId));
        }
        if (reviewRepository.existsByUserUserNameAndBookId(username,bookId)){
            throw new ConflictException("Review dengan Kombinasi username %s dan id buku %s sudah ada".formatted(username,bookId));
        }
        Review review = reviewMapper.toReview(request,optionalBook.get(),optionalUser.get());
        return reviewMapper.toResponse(reviewRepository.save(review));
    }

    public ResponseReview updateReview(String username, Integer bookId, RequestReview request){
        Optional<User> optionalUser = userRepository.findByUserName(username);
        if (optionalUser.isEmpty()) {
            throw new ResourceNotFoundException("Username tidak ditemukan");
        }
        Optional<Book> optionalBook = bookRepository.findByIdAndDeletedFalse(bookId);
        if (optionalBook.isEmpty()){
            throw new ResourceNotFoundException("Buku dengan id %s tidak ditemukan".formatted(bookId));
        }
        Optional<Review> optionalReview = reviewRepository.findByUserNameAndBookIdAndDeactivatedFalse(username,bookId);
        if (optionalReview.isEmpty()){
            throw new ResourceNotFoundException("Review dengan Kombinasi username %s dan id buku %s tidak ditemukan".formatted(username,bookId));
        }
        Review curReview = reviewMapper.updateReview(optionalReview.get(),request);
        return reviewMapper.toResponse(reviewRepository.save(curReview));
    }

    @Transactional
    public void deleteReview(String username, Integer bookId){
        if (!userRepository.existsByUserName(username)) {
            throw new ResourceNotFoundException("Username tidak ditemukan");
        }
        if (!bookRepository.existsByIdAndDeletedFalse(bookId)){
            throw new ResourceNotFoundException("Buku dengan id %s tidak ditemukan".formatted(bookId));
        }
        if (!reviewRepository.existsByUserUserNameAndBookId(username,bookId)){
            throw new ResourceNotFoundException("Review dengan Kombinasi username %s dan id buku %s tidak ditemukan".formatted(username,bookId));
        }
        reviewRepository.deleteByUserUserNameAndBookId(username,bookId);
    }
}
