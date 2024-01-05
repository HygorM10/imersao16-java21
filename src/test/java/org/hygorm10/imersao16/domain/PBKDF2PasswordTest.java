package org.hygorm10.imersao16.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PBKDF2PasswordTest {

    @Test
    public void deveriaHashearASenha() {
        var password = PBKDF2Password.create("123456");

        assertNotEquals("123456", password.value());
        assertTrue(password.value().contains("$$".concat(password.salt())));
    }

    @Test
    public void deveriaDarMatchNasSenhas() {
        var password = PBKDF2Password.create("123456");

        assertTrue(password.validate("123456"));
    }

}
