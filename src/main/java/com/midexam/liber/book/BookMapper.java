    package com.midexam.liber.book;

    import com.midexam.liber.author.Author;
    import com.midexam.liber.author.AuthorRepository;
    import com.midexam.liber.book.dto.RequestBook;
    import com.midexam.liber.book.dto.ResponseBook;
    import com.midexam.liber.error.exception.ResourceNotFoundException;
    import org.springframework.stereotype.Component;

    import java.util.Optional;

    @Component
    public class BookMapper {
        private final AuthorRepository authorRepository;

        public BookMapper(AuthorRepository authorRepository) {
            this.authorRepository = authorRepository;
        }

        public ResponseBook toResponse(Book book) {
            ResponseBook response = new ResponseBook();
            response.setId(book.getId());
            response.setName(book.getName());
            response.setCover(book.getCover());
            response.setAuthorName(book.getAuthor().getName());
            response.setReleaseDate(book.getReleaseDate());
            return response;
        }

        public Book toBook(RequestBook request){
            Book book = new Book();
            book.setName(request.getName());
            book.setCover(request.getCover());
            book.setReleaseDate(request.getReleaseDate());
            Optional<Author> optionalAuthor = authorRepository.findById(request.getAuthorId());
            if (optionalAuthor.isEmpty()) {
                throw new ResourceNotFoundException("Author dengan id %s tidak ditemukan".formatted(request.getAuthorId()));
            }
            book.setAuthor(optionalAuthor.get());
            return book;
        }
    }
