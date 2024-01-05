package org.hygorm10.imersao16.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SHA1PassowordTest {

    @Test
    public void deveriaHashearASenha() {
        var password = SHA1Password.create("123456");

        assertNotEquals("123456", password.value());
    }

    @Test
    public void deveriaDarMatchNasSenhas() {
        var password = SHA1Password.create("123456");

        assertTrue(password.validate("123456"));
    }

}
