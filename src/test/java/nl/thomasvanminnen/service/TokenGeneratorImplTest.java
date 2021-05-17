package nl.thomasvanminnen.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class TokenGeneratorImplTest {
    private TokenGeneratorImpl sut;

    @BeforeEach
    void setUp() {
        this.sut = new TokenGeneratorImpl();
    }

    @Test
    void returnsValidUUID() {
        String actualResult = sut.GenerateToken();
        assertEquals(36, actualResult.length());
    }
}
