package com.midexam.liber.author;

import com.midexam.liber.author.dto.RequestAuthor;
import com.midexam.liber.author.dto.RequestPutAuthor;
import com.midexam.liber.author.dto.ResponseAuthor;
import com.midexam.liber.shared.dto.PageResponse;
import jakarta.validation.Valid;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("authors")
public class AuthorController {
    private final AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping
    public ResponseEntity<PageResponse<ResponseAuthor>> findAll(@PageableDefault(sort = "name",page = 0,size = 10) Pageable pageable){
        return ResponseEntity.status(HttpStatus.OK).body(
                new PageResponse<>(authorService.findAll(pageable))
        );
    }

    @GetMapping("{id}")
    public ResponseEntity<ResponseAuthor> findById(@PathVariable Integer id){
        return ResponseEntity.status(HttpStatus.OK).body(
                authorService.findById(id)
        );
    }

    @PostMapping
    public ResponseEntity<ResponseAuthor> insert(@Valid @RequestBody RequestAuthor request){
        return ResponseEntity.status(HttpStatus.CREATED).body(
                authorService.insert(request)
        );
    }

    @PutMapping("{id}")
    public ResponseEntity<ResponseAuthor> update(@PathVariable Integer id, @Valid @RequestBody RequestPutAuthor request){
        return ResponseEntity.status(HttpStatus.OK).body(
                authorService.update( id,request)
        );
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id){
        authorService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
