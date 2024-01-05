package org.hygorm10.imersao16.infra.dtos;

import org.hygorm10.imersao16.domain.PasswordType;

public record CreateUserInput(
        String email,
        String password,
        PasswordType passwordType
) {
}
