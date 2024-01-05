package org.hygorm10.imersao16.domain;


//sealed -> controla quem poderá herdar ou implementar a interface
public sealed interface Password permits PlainTextPassword, SHA1Password, PBKDF2Password {

    String value();

    boolean validate(String password);

}
