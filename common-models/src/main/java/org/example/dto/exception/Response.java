package org.example.dto.exception;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Response {
    private String message;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String debugMessage;
}
