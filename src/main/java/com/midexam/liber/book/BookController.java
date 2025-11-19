package com.midexam.liber.book;

import com.midexam.liber.book.dto.RequestBook;
import com.midexam.liber.book.dto.ResponseBook;
import com.midexam.liber.hightlight.HighlightService;
import com.midexam.liber.hightlight.dto.RequestHighlight;
import com.midexam.liber.hightlight.dto.ResponseHighlight;
import com.midexam.liber.shared.dto.PageResponse;
import jakarta.validation.Valid;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("books")
public class BookController {
    private final BookService bookService;
    private final HighlightService highlightService;

    public BookController(BookService bookService, HighlightService highlightService) {
        this.bookService = bookService;
        this.highlightService = highlightService;
    }

    @GetMapping
    public ResponseEntity<PageResponse<ResponseBook>> findAll(@PageableDefault(sort = "releaseDate",size = 10,page = 0) Pageable pageable,
                                                              @RequestParam(required = false) String name){
        return ResponseEntity.status(HttpStatus.OK).body(
                new PageResponse<>(bookService.findAll(pageable,name))
        );
    }

    @GetMapping("{id}")
    public ResponseEntity<ResponseBook> findById(@PathVariable Integer id){
        return ResponseEntity.status(HttpStatus.OK).body(
                bookService.findById(id)
        );
    }

    @PostMapping
    public ResponseEntity<ResponseBook> insert(@Valid @RequestBody RequestBook request){
        return ResponseEntity.status(HttpStatus.CREATED).body(
                bookService.insert(request)
        );
    }
    @PutMapping("{id}")
    public ResponseEntity<ResponseBook> update(@PathVariable Integer id , @Valid @RequestBody RequestBook request){
        return ResponseEntity.status(HttpStatus.OK).body(
                bookService.update(id,request)
        );
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> softDelete(@PathVariable Integer id){
        bookService.softDelete(id);
        return ResponseEntity.noContent().build();
    }

    // highlight

    @GetMapping("highlighted")
    public ResponseEntity<PageResponse<ResponseHighlight>> findAllHightlight(@PageableDefault(sort = "orderNumber",size = 10,page = 0) Pageable pageable){
        return ResponseEntity.status(HttpStatus.OK).body(
                new PageResponse<>(highlightService.findAll(pageable))
        );
    }

    @GetMapping("highlighted/{id}")
    public ResponseEntity<ResponseHighlight> finHighlightByOrderNumber(@PathVariable Integer id){
        return ResponseEntity.status(HttpStatus.OK).body(
                highlightService.findByOrderNumber(id)
        );
    }

    @PostMapping("highlighted")
    public ResponseEntity<ResponseHighlight> insertHighlight(@Valid @RequestBody RequestHighlight request){
        return ResponseEntity.status(HttpStatus.CREATED).body(
                highlightService.insert(request)
        );
    }

    @DeleteMapping("highlighted/{id}")
    public ResponseEntity<Void> deleteHighlight(@PathVariable Integer id){
        highlightService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
