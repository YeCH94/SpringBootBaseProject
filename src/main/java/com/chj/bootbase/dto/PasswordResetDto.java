package com.chj.bootbase.dto;

import com.chj.bootbase.constraint.FieldMatch;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@FieldMatch.List({
        @FieldMatch(first="password", second="confirm_password", message="The password fields must match")
})
public class PasswordResetDto {

    @NotNull
    @NotEmpty(message = "Please provide your password")
    @Length(min = 7, message = "Your password must have at least 7 characters")
    private String password;

    @NotNull
    @NotEmpty(message = "Please check your password")
    @Length(min = 7, message = "Your password must have at least 7 characters")
    private  String confirm_password;

    @NotEmpty
    private String token;

    @Builder
    public PasswordResetDto(String password, String confirm_password, String token){
        setPassword(password);
        setConfirm_password(confirm_password);
        setToken(token);
    }
}
