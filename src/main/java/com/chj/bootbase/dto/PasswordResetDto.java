package com.chj.bootbase.dto;

import com.chj.bootbase.constraint.FieldMatch;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@FieldMatch(first = "password", second = "confirm_password", message="The password fields must match")
public class PasswordResetDto {

    @NotEmpty
    private String password;

    @NotEmpty
    private String confirmPassword;

    @NotEmpty
    private String token;
}
