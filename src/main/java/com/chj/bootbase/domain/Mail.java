package com.chj.bootbase.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
public class Mail {
    private  String from;
    private String to;
    private String subject;
    private Map<String, Object> model;

    @Builder
    public Mail(String from, String to, String subject, Map<String, Object> model){
        this.from = from;
        this.to = to;
        this.subject = subject;
        this.model = model;
    }
}
