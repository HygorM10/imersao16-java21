package org.hygorm10.imersao16.domain;

public record User(
        String id,
        String email,

        Password password
) {

    public static User create(String email, String password, PasswordType passwordType) {
        return new User(
                IdGenerator.nextId(),
                email,
                passwordType.create(password)
        );
    }

    public static User restore(String id, String email, String password, PasswordType passwordType) {
        return new User(
                id,
                email,
                passwordType.restore(password)
        );
    }

}
