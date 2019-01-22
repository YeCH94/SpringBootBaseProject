package com.chj.bootbase.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Calendar;
import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "token")
public class PasswordResetToken {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @Column(name = "token", nullable = false, unique = true)
    private String token;

    @OneToOne(targetEntity = Member.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "Id")
    private Member member;

    @Column(nullable = false)
    private Date expireDate;

    public void setExpireDate(int minutes){
        Calendar now = Calendar.getInstance();
        now.add(Calendar.MINUTE, minutes);
        this.expireDate = now.getTime();
    }

    public boolean isExpired() {
        return new Date().after(this.expireDate);
    }

    @Builder
    public PasswordResetToken(String token, Member member){
        setToken(token);
        setMember(member);
    }
}
