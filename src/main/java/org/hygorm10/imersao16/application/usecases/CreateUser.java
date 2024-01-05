package org.hygorm10.imersao16.application.usecases;

import org.hygorm10.imersao16.application.repositorires.UserRepository;
import org.hygorm10.imersao16.domain.PasswordType;
import org.hygorm10.imersao16.domain.User;

import java.util.Objects;

public class CreateUser {

    private final UserRepository userRepository;

    public CreateUser(final UserRepository userRepository) {
        this.userRepository = Objects.requireNonNull(userRepository);
    }

    public Output execute(final Input input) {
        final var aUser = this.userRepository.save(User.create(input.email(), input.password(), input.passwordType()));
        return new Output(aUser.id());
    }

    public record Input(String email, PasswordType passwordType, String password) {

    }

    public record Output(String userId) {

    }
}
