package com.chj.bootbase.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class ErrorResponse {
    private String errMsg;
    private List<String> details;

    @Builder
    public ErrorResponse(String errMsg, List<String> details){
        setErrMsg(errMsg);
        setDetails(details);
    }
}
