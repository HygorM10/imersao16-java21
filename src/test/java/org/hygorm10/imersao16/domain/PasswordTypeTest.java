package org.hygorm10.imersao16.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PasswordTypeTest {

    @Test
    public void deveriaCriarPlainTextPassword() {
        final var expectedPassword = "123456";
        final var actualPassword = PasswordType.PLAIN.create(expectedPassword);

        assertEquals(expectedPassword, actualPassword.value());
    }

}