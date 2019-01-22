package com.chj.bootbase.domain;

import lombok.*;

import java.util.Map;

@Getter
@Setter
@ToString
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
