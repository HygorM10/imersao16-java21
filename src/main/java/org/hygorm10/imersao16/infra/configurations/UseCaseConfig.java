package org.hygorm10.imersao16.infra.configurations;

import org.hygorm10.imersao16.application.repositorires.UserRepository;
import org.hygorm10.imersao16.application.usecases.CreateUser;
import org.hygorm10.imersao16.application.usecases.GetUserOfId;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UseCaseConfig {
    private final UserRepository userRepository;

    public UseCaseConfig(final UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Bean
    public CreateUser createUser() {
        return new CreateUser(this.userRepository);
    }

    @Bean
    public GetUserOfId getUserOfId() {
        return new GetUserOfId(this.userRepository);
    }
}
