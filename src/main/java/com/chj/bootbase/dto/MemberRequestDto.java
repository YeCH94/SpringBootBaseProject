package com.chj.bootbase.dto;

import com.chj.bootbase.constraint.FieldMatch;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@FieldMatch.List({
        @FieldMatch(first="password", second="confirm_password", message="The password fields must match")
})
public class MemberRequestDto {

    @NotNull
    @NotEmpty
    private String name;

    @NotNull
    @NotEmpty
    private String email;

    @NotNull
    @NotEmpty
    private String password;

    @NotNull
    @NotEmpty
    private  String confirm_password;

    @Builder
    public MemberRequestDto(String name, String email, String password, String confirm_password){
        setName(name);
        setEmail(email);
        setPassword(password);
        setConfirm_password(confirm_password);
    }

}
