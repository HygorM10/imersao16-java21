package org.hygorm10.imersao16.infra.controllers;

import org.hygorm10.imersao16.application.usecases.CreateUser;
import org.hygorm10.imersao16.application.usecases.GetUserOfId;
import org.hygorm10.imersao16.infra.dtos.CreateUserInput;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping(value = "users")
public class UserController {

    private final CreateUser createUser;

    private final GetUserOfId getUserOfId;

    public UserController(final CreateUser createUser, final GetUserOfId getUserOfId) {
        this.createUser = Objects.requireNonNull(createUser);
        this.getUserOfId = Objects.requireNonNull(getUserOfId);
    }

    @PostMapping
    public ResponseEntity<?> create(
            @RequestBody CreateUserInput input,
            @RequestParam(name = "latency", defaultValue = "10", required = false) int latency
    ) {
        try {
            Thread.sleep(latency);
            final var output = this.createUser.execute(new CreateUser.Input(input.email(), input.passwordType(), input.password()));
            return ResponseEntity.created(URI.create(STR."/users/\{output.userId()}")).body(output);
        } catch (Throwable t) {
            return ResponseEntity.internalServerError().body(Map.of("error", t.getMessage()));
        }
    }

    @GetMapping(value = "{id}")
    public ResponseEntity<?> userOfId(@PathVariable String id) {
        try {
            final var output = this.getUserOfId.execute(new GetUserOfId.Input(id));
            return ResponseEntity.ok(output);
        } catch (Throwable t) {
            return ResponseEntity.notFound().build();
        }
    }

}
