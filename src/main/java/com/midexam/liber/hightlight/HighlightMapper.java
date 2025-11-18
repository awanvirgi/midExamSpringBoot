package com.midexam.liber.hightlight;

import com.midexam.liber.book.BookMapper;
import com.midexam.liber.hightlight.dto.ResponseHighlight;
import org.springframework.stereotype.Component;

@Component
public class HighlightMapper {

    private final BookMapper bookMapper;

    public HighlightMapper(BookMapper bookMapper) {
        this.bookMapper = bookMapper;
    }

    ResponseHighlight toResponse(Highlight highlight){
        ResponseHighlight response = new ResponseHighlight();
        response.setAddedDate(highlight.getAddedDate());
        response.setOrderNumber(highlight.getOrderNumber());

        response.setResponseBook(bookMapper.toResponse(highlight.getBook()));
        return response;
    }
}
