package org.hygorm10.imersao16.application.usecases;

import org.hygorm10.imersao16.application.repositorires.UserRepository;

import java.util.Objects;

public class GetUserOfId {

    private final UserRepository userRepository;

    public GetUserOfId(final UserRepository userRepository) {
        this.userRepository = Objects.requireNonNull(userRepository);
    }

    public Output execute(final Input input) {
        return this.userRepository.userOfId(input.id())
                .map(user -> new Output(user.id(), user.email()))
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    public record Input(String id) {
    }

    public record Output(String id, String email) {
    }

}
