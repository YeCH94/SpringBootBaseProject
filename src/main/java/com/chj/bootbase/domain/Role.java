package com.chj.bootbase.domain;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name = "role")
public class Role {

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
}
