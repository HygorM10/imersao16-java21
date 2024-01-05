package org.hygorm10.imersao16.domain;

import com.google.common.hash.Hashing;

import java.nio.charset.StandardCharsets;

//Não é seguro, mas é só para fins didáticos
public record SHA1Password(String value) implements Password {
    public static SHA1Password create(String plainPassword) {
        return new SHA1Password(hash(plainPassword));
    }

    public static SHA1Password restore(String password) {
        return new SHA1Password(password);
    }

    private static String hash(String plainText) {
        return Hashing.sha1().hashString(plainText, StandardCharsets.UTF_8).toString();
    }

    @Override
    public boolean validate(String password) {
        return value().equals(hash(password));
    }
}
