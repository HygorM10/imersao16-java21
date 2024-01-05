package org.hygorm10.imersao16.domain;

import com.google.common.io.BaseEncoding;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;

public record PBKDF2Password(String value, String salt) implements Password {

    public static PBKDF2Password create(final String plainPassword) {
        var salt = generateSalt();
        return new PBKDF2Password(hash(plainPassword, salt), salt);
    }

    private static String generateSalt() {
        var bytes = new byte[16];

        var random = new SecureRandom();
        random.nextBytes(bytes);

        return BaseEncoding.base16().encode(bytes);
    }

    public static PBKDF2Password restore(final String password) {
        var allParts = password.split("\\$\\$");
        var pass = allParts[0];
        var salt = allParts[1];
        return new PBKDF2Password(pass, salt);
    }

    private static String hash(final String plainText, final String salt) {
        try {
            var spec = new PBEKeySpec(plainText.toCharArray(), salt.getBytes(StandardCharsets.UTF_8), 100, 64);
            var pass = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA512")
                    .generateSecret(spec)
                    .getEncoded();
            return BaseEncoding.base16().encode(pass);
        } catch (Throwable t) {
            t.printStackTrace();
            throw new RuntimeException(t);
        }
    }

    @Override
    public String value() {
        return STR."\{value}$$\{salt}";
    }

    @Override
    public boolean validate(String password) {
        return value().equals(hash(password, salt));
    }
}
