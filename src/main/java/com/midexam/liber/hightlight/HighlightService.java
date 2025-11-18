package com.midexam.liber.hightlight;

import com.midexam.liber.book.Book;
import com.midexam.liber.book.BookRepository;
import com.midexam.liber.error.exception.ConflictException;
import com.midexam.liber.error.exception.MinimumDataRequirementException;
import com.midexam.liber.error.exception.ResourceNotFoundException;
import com.midexam.liber.hightlight.dto.RequestHighlight;
import com.midexam.liber.hightlight.dto.ResponseHighlight;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class HighlightService {
    private final BookRepository bookRepository;
    private final HighlightRepository highlightRepository;
    private final HighlightMapper highlightMapper;

    public HighlightService(BookRepository bookRepository, HighlightRepository highlightRepository, HighlightMapper highlightMapper) {
        this.bookRepository = bookRepository;
        this.highlightRepository = highlightRepository;
        this.highlightMapper = highlightMapper;
    }

    public Page<ResponseHighlight> findAll(Pageable pageable){
        return highlightRepository.findAll(pageable).map(
                highlightMapper::toResponse
        );
    }

    public ResponseHighlight findByOrderNumber(Integer id){
        Optional<Highlight> optionalHighlight = highlightRepository.findByOrderNumber(id);
        if (optionalHighlight.isEmpty()) {
            throw new ResourceNotFoundException("Highlight dengan order number %s tidak ditemukan".formatted(id));
        }
        return highlightMapper.toResponse(optionalHighlight.get());
    }

    public ResponseHighlight insert(RequestHighlight request){
        Highlight highlight = new Highlight();
        Optional<Book> optionalBook = bookRepository.findByIdAndDeletedFalse(request.getBookId());
        if (optionalBook.isEmpty()){
            throw new ResourceNotFoundException("buku dengan id %s tidak ditemukan".formatted(request.getBookId()));
        }
        if (highlightRepository.existsByOrderNumber(request.getOrderNumber())){
            throw new ConflictException("Order number %s sudah dipakai,gunakan nomor urutan yang lain".formatted(request.getOrderNumber()));
        }
        if (highlightRepository.existsByBookId(request.getBookId())){
            throw new ConflictException("Buku sudah berada diHighlight, tidak dapat duplikasi");
        }
        highlight.setBook(optionalBook.get());
        highlight.setOrderNumber(request.getOrderNumber());
        return highlightMapper.toResponse(highlightRepository.save(highlight));
    }

    @Transactional
    public void delete(Integer id){
        if (!highlightRepository.existsByOrderNumber(id)){
            throw new ResourceNotFoundException("Highlight dengan order number %s tidak ditemukan".formatted(id));
        }
        highlightRepository.deleteByOrderNumber(id);
    }
}
