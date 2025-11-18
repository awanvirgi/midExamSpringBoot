package com.midexam.liber.author;

import com.midexam.liber.author.dto.RequestAuthor;
import com.midexam.liber.author.dto.RequestPutAuthor;
import com.midexam.liber.author.dto.ResponseAuthor;
import org.springframework.stereotype.Component;

@Component
public class AuthorMapper {
    ResponseAuthor toResponse(Author author){
        ResponseAuthor response = new ResponseAuthor();
        response.setId(author.getId());
        response.setName(author.getName());
        response.setDescription(author.getDescription());
        return response;
    }

    Author toAuthor(RequestAuthor request){
        Author author = new Author();
        author.setName(request.getName());
        author.setDescription(request.getDescription());
        return author;
    }

    Author toAuthor(RequestPutAuthor request){
        Author author = new Author();
        author.setName(request.getName());
        author.setDescription(request.getDescription());
        return author;
    }
}
