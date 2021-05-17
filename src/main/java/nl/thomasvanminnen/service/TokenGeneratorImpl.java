package nl.thomasvanminnen.service;

import nl.thomasvanminnen.service.interfaces.TokenGenerator;

import java.util.UUID;

public class TokenGeneratorImpl implements TokenGenerator {

    @Override
    public String GenerateToken() {
        return UUID.randomUUID().toString();
    }
}
