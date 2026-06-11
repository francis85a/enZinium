package edu.teamrocket.enZinium;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class InsufficientTokensExceptionTest {

    private Address rick = null;
    private TokenContract contract = null;

    @BeforeEach
    public void setupContract() {
        rick = new Address();
        rick.generateKeyPair();
        contract = new TokenContract(rick);
    }
    
    @Test
    public void InsufficientTokensExceptionThrowTest() {
        assertThrows(InsufficientTokensException.class, () -> {
            contract.require(false);
        });
        assertEquals(0d, contract.getTokenPrice(), 0d);
    }



    @Test
    public void InsufficientTokensExceptionMessageTest() {
        InsufficientTokensException e = assertThrows(InsufficientTokensException.class, () -> {
            contract.require(false);
        });
        assertEquals("No hay enziniums suficientes para realizar esta accion", e.getMessage());
    }
}