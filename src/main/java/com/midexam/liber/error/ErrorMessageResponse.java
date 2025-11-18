package com.midexam.liber.error;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.time.ZoneId;
import java.time.ZonedDateTime;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class ErrorMessageResponse<T> {
    private final HttpStatus status;
    private final String message;
    private final T errors;
    private final ZonedDateTime timestamp = ZonedDateTime.now(ZoneId.of("Z"));
}
