package com.midexam.liber.author;

import com.midexam.liber.author.dto.RequestAuthor;
import com.midexam.liber.author.dto.RequestPutAuthor;
import com.midexam.liber.author.dto.ResponseAuthor;
import com.midexam.liber.book.BookRepository;
import com.midexam.liber.error.exception.ConflictException;
import com.midexam.liber.error.exception.ResourceNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthorService {
    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final AuthorMapper authorMapper;
    public AuthorService(AuthorRepository authorRepository, BookRepository bookRepository, AuthorMapper authorMapper) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.authorMapper = authorMapper;
    }

    public Page<ResponseAuthor> findAll(Pageable pageable){
        return authorRepository.findAll(pageable).map(
                authorMapper::toResponse
        );
    }

    public ResponseAuthor findById(Integer id){
        Optional<Author> optionalAuthor = authorRepository.findById(id);
        if (optionalAuthor.isEmpty()) {
            throw new ResourceNotFoundException("Author dengan id %s tidak ditemukan".formatted(id));
        }
        return authorMapper.toResponse(optionalAuthor.get());
    }

    public ResponseAuthor insert(RequestAuthor request){
        Author author = authorMapper.toAuthor(request);
        author = authorRepository.save(author);
        return authorMapper.toResponse(author);
    }

    public ResponseAuthor update(Integer id, RequestPutAuthor request){
        if (!authorRepository.existsById(id)){
            throw new ResourceNotFoundException("Author dengan Id %s tidak ditemukan".formatted(id));
        }
        if (authorRepository.existsByName(request.getName(),id)){
            throw new ConflictException("Nama Author duplikat dengan yang lain,Update gagal");
        }
        Author author = authorMapper.toAuthor(request);
        author.setId(id);
        author = authorRepository.save(author);
        return authorMapper.toResponse(author);
    }

    @Transactional
    public void delete(Integer id){
        if (!authorRepository.existsById(id)){
            throw new ResourceNotFoundException("Author dengan Id %s tidak ditemukan".formatted(id));
        }
        if (bookRepository.existsByAuthorId(id)){
            throw new ConflictException("Author tidak dapat dihapus karena bukunya sudah terdaftar");
        }
        authorRepository.deleteById(id);
    }
}
