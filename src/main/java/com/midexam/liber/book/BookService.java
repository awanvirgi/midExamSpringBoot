package com.midexam.liber.book;

import com.midexam.liber.book.dto.RequestBook;
import com.midexam.liber.book.dto.ResponseBook;
import com.midexam.liber.error.exception.ConflictException;
import com.midexam.liber.error.exception.ResourceNotFoundException;
import com.midexam.liber.hightlight.HighlightRepository;
import com.midexam.liber.review.ReviewRepository;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BookService {
    private final BookRepository bookRepository;
    private final HighlightRepository highlightRepository;
    private final ReviewRepository reviewRepository;
    private final BookMapper bookMapper;

    public BookService(BookRepository bookRepository, HighlightRepository highlightRepository, ReviewRepository reviewRepository, BookMapper bookMapper) {
        this.bookRepository = bookRepository;
        this.highlightRepository = highlightRepository;
        this.reviewRepository = reviewRepository;
        this.bookMapper = bookMapper;
    }


    public Page<ResponseBook> findAll(Pageable pageable, String name){
        return bookRepository.findAll(pageable,name.toLowerCase()).map(
                bookMapper::toResponse
        );
    }

    public ResponseBook findById(Integer id){
        Optional<Book> optionalBook = bookRepository.findByIdAndDeletedFalse(id);
        if (optionalBook.isEmpty()) {
            throw new ResourceNotFoundException("Buku dengan id %s tidak ditemukan".formatted(id));
        }
        return bookMapper.toResponse(optionalBook.get());
    }

    public ResponseBook insert(RequestBook request){
        Book book = bookMapper.toBook(request);
        book = bookRepository.save(book);
        return bookMapper.toResponse(book);
    }

    public ResponseBook update(Integer id, RequestBook request){
        if (!bookRepository.existsByIdAndDeletedFalse(id)){
            throw new ResourceNotFoundException("Buku dengan id %s tidak ditemukan".formatted(id));
        }
        Book book = bookMapper.toBook(request);
        book.setId(id);
        book.setDeleted(false);
        book = bookRepository.save(book);
        return bookMapper.toResponse(book);
    }

    @Transactional
    public void softDelete(Integer id){
        Optional<Book> optionalBook = bookRepository.findByIdAndDeletedFalse(id);
        if (optionalBook.isEmpty()) {
            throw new ResourceNotFoundException("Buku dengan id %s tidak ditemukan".formatted(id));
        }
        if (highlightRepository.existsByBookId(id)){
            throw new ConflictException("Buku sedang di highlight,hapus highlight pada buku ini sebelum melakukan hapus pada buku");
        }
        reviewRepository.deactiveAllReviewWithBookId(id);
        optionalBook.get().setDeleted(true);
        bookRepository.save(optionalBook.get());
    }
}
