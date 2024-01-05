package org.hygorm10.imersao16.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class PlainTextPasswordTest {

    @Test
    public void deveriaCriarUmPlainPassword() {
        final var expectedPassword = "123456";
        var password = new PlainTextPassword("123456");
        assertTrue(password.validate(expectedPassword));
    }

}
