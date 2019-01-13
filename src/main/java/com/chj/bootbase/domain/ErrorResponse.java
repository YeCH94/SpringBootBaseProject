package com.chj.bootbase.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@NoArgsConstructor
public class ErrorResponse {
    private String errMsg;
    private HttpStatus status;

    @Builder
    public ErrorResponse(String errMsg, HttpStatus status){
        setErrMsg(errMsg);
        setStatus(status);
    }
}
