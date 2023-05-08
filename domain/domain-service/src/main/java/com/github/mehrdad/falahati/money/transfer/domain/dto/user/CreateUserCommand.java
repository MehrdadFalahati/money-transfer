package com.github.mehrdad.falahati.money.transfer.domain.dto.user;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class CreateUserCommand {
    @NotNull private String username;
    @NotNull private String password;
    @NotNull private String firstName;
    @NotNull private String lastName;
    @NotNull private String phoneNumber;

    public void setPassword(String password) {
        this.password = password;
    }
}
