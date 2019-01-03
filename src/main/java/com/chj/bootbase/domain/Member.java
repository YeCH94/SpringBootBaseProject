package com.chj.bootbase.domain;

import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.util.Collection;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "member")
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column
    @NotEmpty
    private String username;

    @Column(unique = true)
    @NotEmpty(message = "Please provide your E-mail")
    @Email(message = "Please provide an E-mail form")
    private String email;

    @Column
    @NotEmpty(message = "Please provide your password")
    @Length(min = 7, message = "Your password must have at least 7 characters")
    private String password;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
            name = "members_roles",
            joinColumns = @JoinColumn(
                    name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(
                    name = "role_id", referencedColumnName = "id"))
    private Collection< Role > roles;

    @Override
    public String toString() {
        return "Member{" +
                "id=" + id +
                ", User Name = '" + username + '\'' +
                ", E-mail = '" + email + '\'' +
                ", password = '" + "*********" + '\'' +
                ", roles = '" + roles +
                '}';
    }
    @Builder
    public Member(String username, String email, String password){
        this.username = username;
        this.email = email;
        this.password = password;
    }

    @Builder
    public Member(String username, String email, String password, Collection <Role> roles){
        this.username = username;
        this.email = email;
        this.password = password;
        this.roles = roles;
    }
}
