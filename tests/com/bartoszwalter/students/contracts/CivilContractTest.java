package com.bartoszwalter.students.contracts;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CivilContractTest {

    @Test
    void calculateCivilContract() {
        CivilContract civilContract = new CivilContract(1000);
        assertEquals(728.24, Contract.pay);
    }
}