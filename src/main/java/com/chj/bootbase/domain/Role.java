package com.chj.bootbase.domain;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Entity
@NoArgsConstructor
@Getter
@Table(name = "role")
public class Role implements GrantedAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    @NotEmpty
    private String username;

    @Builder
    public Role(String username){
        this.username = username;
    }
    @Override
    public String toString() {
        return "Role{" +
                "id = " + id +
                ", name = '" + username + '\'' +
                '}';
    }

    @Override
    public String getAuthority() {
        return this.username;
    }
}
